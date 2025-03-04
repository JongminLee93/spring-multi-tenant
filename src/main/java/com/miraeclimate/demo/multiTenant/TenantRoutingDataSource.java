package com.miraeclimate.demo.multiTenant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantRoutingDataSource extends AbstractRoutingDataSource {
  private Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();
  private final Object dataSourceCreationLock = new Object();

  @Override
  protected Object determineCurrentLookupKey() {
    return TenantContext.getCurrentTenant();
  }

  @Override
  protected DataSource determineTargetDataSource() {
    Object lookupKey = determineCurrentLookupKey();
    if (lookupKey == null) {
      lookupKey = "default";
    }

    if (!tenantDataSources.containsKey(lookupKey)) {
      synchronized (dataSourceCreationLock) {
        if (!tenantDataSources.containsKey(lookupKey)) {
          log.info("create data source");
          DataSource dataSource = createDataSource(lookupKey.toString());
          tenantDataSources.put(lookupKey, dataSource);
          setTargetDataSources(new HashMap<>(tenantDataSources));
          afterPropertiesSet();
        }
      }
    }

    return super.determineTargetDataSource();
  }

  public DataSource createDataSource(String schema) {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/click");
    dataSource.setUsername("click");
    dataSource.setPassword("click123!");
    dataSource.setConnectionInitSql("SET search_path TO " + schema);

    // 커넥션 풀 최적화 설정 추가
    dataSource.setMaximumPoolSize(5);
    dataSource.setMinimumIdle(2);
    dataSource.setIdleTimeout(60000);

    return dataSource;
  }
}
