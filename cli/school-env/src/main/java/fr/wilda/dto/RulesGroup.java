package fr.wilda.dto;

import java.io.Serializable;
import java.util.List;

public class RulesGroup implements Serializable {

  private List<Rule> rules;

  public List<Rule> getRules() {
    return rules;
  }

  public void setRules(List<Rule> rules) {
    this.rules = rules;
  }
}
