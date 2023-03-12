package fr.wilda.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import fr.wilda.dto.CreatedDatasource;
import fr.wilda.dto.Datasource;
import fr.wilda.dto.Rule;
import fr.wilda.dto.RulesGroup;

@Path("/api")
@RegisterRestClient
@ClientHeaderParam(name = "Authorization", value = "{autorizationHeader}")
public interface GrafanaAPIService {

  @POST
  @Path("/datasources")
  @Consumes("application/json")
  @Produces("application/json")
  CreatedDatasource createDatasource(Datasource datasourceToCreate);

  @POST
  @Path("/v1/provisioning/alert-rules/")
  @Consumes("application/json")
  @Produces("application/json")
  void createAlerteFromDatasource(String alerteInJson);

  @DELETE
  @Path("/v1/provisioning/alert-rules/{alerteUid}")
  void deleteAlerte(@PathParam("alerteUid") String alerteUid);

  @GET
  @Path("/v1/provisioning/folder/{groupUid}/rule-groups/{folderName}")
  RulesGroup listRules(@PathParam("groupUid") String groupUid, @PathParam("folderName") String folderName);

  @GET
  @Path("/v1/provisioning/alert-rules/{ruleUId}")
  Rule getRuleByUid(@PathParam("ruleUId") String ruleUId);

  @DELETE
  @Path("/datasources/name/{name}")
  void deleteDatasourceByName(@PathParam("name") String name);

  @DELETE
  @Path("/datasources/uid/{uid}")
  void deleteDatasourceByUid(@PathParam("uid") String uid);

  default String autorizationHeader() {
    return "Bearer " + System.getenv("GRAFANA_TOKEN");
  }
}
