package com.miraeclimate.demo.resolver;

import java.util.Objects;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import com.miraeclimate.demo.context.TenantContext;

@Component
public class CurrentTenantIdentifierResolverImpl
    implements CurrentTenantIdentifierResolver<String> {
  @Override
  public String resolveCurrentTenantIdentifier() {
    String tenant = TenantContext.getCurrentTenant();
    return Objects.requireNonNullElse(tenant, "public");
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }
}
