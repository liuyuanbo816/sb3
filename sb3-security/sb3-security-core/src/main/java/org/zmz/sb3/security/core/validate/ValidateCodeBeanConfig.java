package org.zmz.sb3.security.core.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zmz.sb3.security.core.properties.SecurityProperties;
import org.zmz.sb3.security.core.validate.code.ImageCode;
import org.zmz.sb3.security.core.validate.code.ImageValidateCodeGenerator;
import org.zmz.sb3.security.core.validate.code.ValidateCodeGenerator;

@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator<ImageCode> imageValidateCodeGenerator() {
        ImageValidateCodeGenerator imageValidateCodeGenerator
                = new ImageValidateCodeGenerator();
        imageValidateCodeGenerator.setSecurityProperties(securityProperties);
        return imageValidateCodeGenerator;
    }
}
