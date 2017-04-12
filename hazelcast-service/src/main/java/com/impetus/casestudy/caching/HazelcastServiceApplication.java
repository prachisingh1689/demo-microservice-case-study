package com.impetus.casestudy.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
public class HazelcastServiceApplication
{

  public static void main(String[] args)
  {
    Config config = new XmlConfigBuilder().build();
    HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
    SpringApplication.run(HazelcastServiceApplication.class, args);
  }
}