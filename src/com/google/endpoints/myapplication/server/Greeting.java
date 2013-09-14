package com.google.endpoints.myapplication.server;

public class Greeting {

  public String message;

  public Greeting() {};

  public Greeting(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}