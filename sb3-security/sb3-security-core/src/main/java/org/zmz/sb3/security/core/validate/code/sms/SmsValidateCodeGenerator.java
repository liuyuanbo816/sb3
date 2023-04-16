package org.zmz.sb3.security.core.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.zmz.sb3.security.core.properties.SecurityProperties;
import org.zmz.sb3.security.core.validate.code.ValidateCode;
import org.zmz.sb3.security.core.validate.code.ValidateCodeGenerator;

import java.util.Random;

@Component("smsValidateCodeGenerator")
public class SmsValidateCodeGenerator implements ValidateCodeGenerator<ValidateCode> {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generateCode(ServletWebRequest request) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < securityProperties.getCode().getSms().getLength(); i++) {
            int index = random.nextInt(0, 9);
            sb.append(index);
        }
        return new ValidateCode(sb.toString(), securityProperties.getCode().getSms().getExpireIn());
    }
}
