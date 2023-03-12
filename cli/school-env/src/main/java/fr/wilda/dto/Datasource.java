package fr.wilda.dto;

import java.io.Serializable;

public class Datasource implements Serializable {
  private String uid;
  private String orgId = "3";
  private String name;
  private String type = "prometheus";
  private String typeName = "Prometheus";
  private String typeLogoUrl = "public/app/plugins/datasource/prometheus/img/prometheus_logo.svg";
  private String access = "proxy";
  private String url;

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeLogoUrl() {
    return typeLogoUrl;
  }

  public void setTypeLogoUrl(String typeLogoUrl) {
    this.typeLogoUrl = typeLogoUrl;
  }

  public String getAccess() {
    return access;
  }

  public void setAccess(String access) {
    this.access = access;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  @Override
  public String toString() {
    return "Datasource [uid=" + uid + ", orgId=" + orgId + ", name=" + name + ", type=" + type + ", typeName=" + typeName + ", typeLogoUrl="
        + typeLogoUrl + ", access=" + access + ", url=" + url + "]";
  }



}
