package de.hd1.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Main {


  private final Logger logger;

  @Autowired
  public Main(Logger logger) {
    this.logger = logger;
  }

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}
