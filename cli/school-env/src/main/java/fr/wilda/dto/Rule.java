package fr.wilda.dto;

import java.io.Serializable;
import java.util.List;

public class Rule implements Serializable{
  private String uid;
  private List<DataRule> data;
 
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public List<DataRule> getData() {
    return data;
  }

  public void setData(List<DataRule> data) {
    this.data = data;
  }


}
