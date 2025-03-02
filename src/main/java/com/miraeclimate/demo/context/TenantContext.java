package com.miraeclimate.demo.context;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TenantContext implements Filter {
  private static final String LOGGER_TENANT_ID = "tenant_id";
  public static final String HTTP_TENANT_HEADER = "X-Tenant";
  private static final String DEFAULT_TENANT = "public";
  private static final ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

  public static String getCurrentTenant() {
    String tenant = currentTenant.get();

    return Objects.requireNonNullElse(tenant, DEFAULT_TENANT);
  }

  public static void setCurrentTenant(String tenant) {
    MDC.put(LOGGER_TENANT_ID, tenant);
    currentTenant.set(tenant);
  }

  public static void clear() {
    MDC.clear();
    currentTenant.remove();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    try {
      chain.doFilter(request, response);
    } finally {
      clear();
    }
  }
}
