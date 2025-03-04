package com.miraeclimate.demo.multiTenant;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiTenantConfig {

  @Bean
  public DataSource dataSource() {
    TenantRoutingDataSource dataSource = new TenantRoutingDataSource();

    DataSource defaultDataSource = dataSource.createDataSource("click");
    dataSource.setDefaultTargetDataSource(defaultDataSource);

    Map<Object, Object> initialDataSources = new HashMap<>();
    initialDataSources.put("default", defaultDataSource);
    dataSource.setTargetDataSources(initialDataSources);

    return dataSource;
  }
}
