package com.rkd.example.metric;

public class DefaultClientEndpointMetric {

  private DefaultClientEndpointMetric(){}

  public static DefaultClientEndpointMetric INSTANCE = new DefaultClientEndpointMetric();

  private boolean added = false;

  public boolean getAdded() {
    return added;
  }

  public void setAdded(boolean added) {
    this.added = added;
  }
}
