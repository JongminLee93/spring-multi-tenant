package com.miraeclimate.demo.security.converter;

import java.util.Collection;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import com.miraeclimate.demo.context.TenantContext;

public class CustomJwtGrantedAuthoritiesConverter
    implements Converter<Jwt, Collection<GrantedAuthority>> {
  private final Converter<Jwt, Collection<GrantedAuthority>> delegate;

  public CustomJwtGrantedAuthoritiesConverter(
      Converter<Jwt, Collection<GrantedAuthority>> delegate) {
    this.delegate = delegate;
  }

  @Override
  public Collection<GrantedAuthority> convert(Jwt jwt) {
    // tenant 클레임에서 테넌트 정보 추출
    String tenant = jwt.getClaimAsString("tenant");
    if (tenant != null && !tenant.isEmpty()) {
      // tenant 정보를 TenantContext에 설정
      TenantContext.setCurrentTenant(tenant);
    }

    // 원래의 권한 변환 로직 실행
    return delegate.convert(jwt);
  }
}
