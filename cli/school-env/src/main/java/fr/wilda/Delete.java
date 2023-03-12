package fr.wilda;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.wilda.dto.Rule;
import fr.wilda.dto.RulesGroup;
import fr.wilda.services.GrafanaAPIService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;;


@Command(name = "delete", description = "Delete a full environment (Kube, Prometheus, Grafana config)")
public class Delete implements Runnable {

  private static final Logger _LOG = LoggerFactory.getLogger(Delete.class);

  private static final String GROUP_UID = "HnDCAp-4k";
  private static final String FOLDER_NAME = "polytech";

  @Option(names = {"-a", "--all"}, description = "Delete all environments")
  private boolean all;

  @Option(names = {"--uid"}, description = "Delete a rule by its uid")
  private String ruleUId;

  @RestClient
  private GrafanaAPIService grafanaAPIService;

  @Override
  public void run() {
    if (all) {
      RulesGroup rules = grafanaAPIService.listRules(GROUP_UID, FOLDER_NAME);
      for (Rule rule : rules.getRules()) {
        deleteARuleWithDatasource(rule);
      }
    } else if (ruleUId != null) {
      deleteARuleWithDatasource(grafanaAPIService.getRuleByUid(ruleUId));
    }
  }

  /**
   * Delete a rule with its datasource.
   * 
   * @param rule The rule to delete.
   */
  private void deleteARuleWithDatasource(Rule rule) {
    _LOG.debug("Delete rule: {} with datasource: {}", rule.getUid(), rule.getData().get(0).getDatasourceUid());
    grafanaAPIService.deleteAlerte(rule.getUid());
    grafanaAPIService.deleteDatasourceByUid(rule.getData().get(0).getDatasourceUid());
  }
}