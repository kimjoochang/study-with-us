package com.studywithus.server;

public class Response {

  public static final String SUCCESS = "success";
  public static final String FAIL = "fail";

  String status;
  Object value;

  public Response() {}

  public Response(String status, Object value) {
    this.status = status;
    this.value = value;
  }

  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public Object getValue() {
    return value;
  }
  public void setValue(Object value) {
    this.value = value;
  }
}