package fr.wilda;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.wilda.services.GrafanaAPIService;
import picocli.CommandLine.Command;

@Command(name = "list", description = "List the parametrized clusters")
public class List implements Runnable {
  private static final Logger _LOG = LoggerFactory.getLogger(List.class);
  private static final String GROUP_UID = "HnDCAp-4k";
  private static final String FOLDER_NAME = "polytech";

  @RestClient
  private GrafanaAPIService grafanaAPIService;
  
  @Override
  public void run() {
    grafanaAPIService.listRules(GROUP_UID, FOLDER_NAME);
  }
}
