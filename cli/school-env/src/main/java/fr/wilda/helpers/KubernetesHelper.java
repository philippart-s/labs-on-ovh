package fr.wilda.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

public class KubernetesHelper {

  private static final Logger _LOG = LoggerFactory.getLogger(KubernetesHelper.class);
  private static final String PROMETHEUS_POD_NAME = "kube-prometheus-stack-prometheus";
  private static final String PROMETHEUS_NAMESPACE = "prometheus";
  private static KubernetesClient kubernetesClient;

  public static KubernetesHelper kubernetesHelperFactory(String kubeConfigFileName) {
    try {
      String path =
          KubernetesHelper.class.getClassLoader().getResource(kubeConfigFileName).getFile();
      KubernetesClientBuilder kubernetesClientBuilder = new KubernetesClientBuilder();
      kubernetesClient = kubernetesClientBuilder
          .withConfig(Config.fromKubeconfig(Files.readString(Path.of(path)))).build();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return new KubernetesHelper();
  }

  private KubernetesHelper() {}

  public String getPrometheusIPService() {
    List<Service> services =
        kubernetesClient.services().inNamespace(PROMETHEUS_NAMESPACE).list().getItems();

    for (Service service : services) {
      if (service.getMetadata().getLabels().containsValue(PROMETHEUS_POD_NAME)) {
        String serviceIP = service.getStatus().getLoadBalancer().getIngress().get(0).getIp();
        _LOG.debug("{} - {} - {}",
            service.getMetadata().getLabels().containsValue(PROMETHEUS_POD_NAME),
            service.getSpec().getType(), serviceIP);
        return serviceIP;
      }
    }

    return null;
  }

  public String getClusterName() {
    return kubernetesClient.getConfiguration().getCurrentContext().getName().substring(
        kubernetesClient.getConfiguration().getCurrentContext().getName().lastIndexOf("@") + 1);
  }

}
