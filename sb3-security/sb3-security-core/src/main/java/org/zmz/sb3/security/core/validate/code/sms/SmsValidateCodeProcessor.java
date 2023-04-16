package org.zmz.sb3.security.core.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.zmz.sb3.security.core.validate.code.ValidateCode;
import org.zmz.sb3.security.core.validate.impl.AbstractValidateCodeProcessor;

@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsSender defaultSmsSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        defaultSmsSender.send(mobile, validateCode.getCode());
    }
}
