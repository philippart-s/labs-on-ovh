package fr.wilda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.wilda.dto.CreatedDatasource;
import fr.wilda.dto.Datasource;
import fr.wilda.helpers.KubernetesHelper;
import fr.wilda.services.GrafanaAPIService;
import picocli.CommandLine.Command;

@Command(name = "create", description = "Create a full environment (Kube, Prometheus, Grafana config)")
public class Create implements Runnable {
  private static final Logger _LOG = LoggerFactory.getLogger(Create.class);

  @RestClient
  private GrafanaAPIService grafanaAPIService;

  @Override
  public void run() {
    try {
      List<String> kubeconfigs = listKubeconfig(this.getClass().getClassLoader().getResource("kubeconfigs/polytech").getFile());
      for (String kubeconfig : kubeconfigs) {
        // Get information from Kubernetes cluster
        _LOG.debug("Used kubeconfig: {}", kubeconfig);
        KubernetesHelper kubernetesServices = KubernetesHelper.kubernetesHelperFactory("kubeconfigs/polytech/" + kubeconfig);
        String prometheusIp = kubernetesServices.getPrometheusIPService();
        String clusterCtxName = kubernetesServices.getClusterName();
        _LOG.debug(" {} - {}", prometheusIp, clusterCtxName);

        // Create Datasource from Prometheus
        Datasource datasource = new Datasource();
        datasource.setUrl("http://" + prometheusIp + ":9090");
        datasource.setName(clusterCtxName);
        CreatedDatasource datasourceCReated = grafanaAPIService.createDatasource(datasource);
        _LOG.debug("Created datasource: {}", datasourceCReated.getDatasource());

        // Create an alerte from previously created datasource
        String alerteToCreateInJson;
        alerteToCreateInJson = loadAndReplaceJSONAlert(datasourceCReated.getDatasource().getUid(), clusterCtxName + "-CPU");
        _LOG.debug("JSON for created alerte: {}", alerteToCreateInJson);
        grafanaAPIService.createAlerteFromDatasource(alerteToCreateInJson);
      }
    } catch (IOException e) {
      _LOG.error("Error during alerte or datasource creation", e);
      e.printStackTrace();
    }
  }

  /**
   * Retrieve each file name in the given directory.
   * @param dir The directory where list the files.
   * @return An array of file names.
   * @throws IOException It IO error occurs
   */
  private List<String> listKubeconfig(String dir) throws IOException {
    try (Stream<Path> stream = Files.list(Paths.get(dir))) {
      return stream.filter(file -> !Files.isDirectory(file)).map(Path::getFileName).map(Path::toString).collect(Collectors.toList());
    }
  }

  /**
   * Replace values for datasource and alert in the JSON alerte definition.
   * @param datasourceUid The uid to use for the alerte.
   * @param alerteTitle The alerte title to create.
   * @return The JSON with the replaced values.
   * @throws IOException If IO error occurs.
   */
  private String loadAndReplaceJSONAlert(String datasourceUid, String alerteTitle) throws IOException {
    String filePath = this.getClass().getClassLoader().getResource("alerteToCreate.json").getFile();
    String alerteJSON = Files.readString(Path.of(filePath));
    return alerteJSON.replaceAll("DATASOURCE_ID", datasourceUid).replaceAll("TITLE", alerteTitle);
  }
}
