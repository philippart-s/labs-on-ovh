package fr.wilda.dto;

import java.io.Serializable;

public class DataRule implements Serializable{
  private String datasourceUid;

  public String getDatasourceUid() {
    return datasourceUid;
  }

  public void setDatasourceUid(String datasourceUid) {
    this.datasourceUid = datasourceUid;
  }
  }
