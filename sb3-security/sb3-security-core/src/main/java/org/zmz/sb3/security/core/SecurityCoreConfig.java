package org.zmz.sb3.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.zmz.sb3.security.core.properties.SecurityProperties;

@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityCoreConfig {
}
