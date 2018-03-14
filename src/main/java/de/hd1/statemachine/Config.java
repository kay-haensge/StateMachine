package de.hd1.statemachine;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.logging.Logger;

@Configuration
public class Config {

  @Bean
  @Scope("prototype")
  public Logger logger(InjectionPoint injectionPoint) {
    return java.util.logging.Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
  }


}
