package org.zmz.sb3.security.core.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zmz.sb3.security.core.properties.SecurityProperties;
import org.zmz.sb3.security.core.validate.code.ValidateCodeGenerator;
import org.zmz.sb3.security.core.validate.code.image.ImageCode;
import org.zmz.sb3.security.core.validate.code.image.ImageValidateCodeGenerator;
import org.zmz.sb3.security.core.validate.code.sms.DefaultSmsSender;
import org.zmz.sb3.security.core.validate.code.sms.SmsSender;

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

    @Bean
    @ConditionalOnMissingBean(SmsSender.class)
    public SmsSender defaultSmsSender() {
        return new DefaultSmsSender();
    }
}
