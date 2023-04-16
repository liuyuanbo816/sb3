package org.zmz.sb3.security.examples.code;

import org.springframework.web.context.request.ServletWebRequest;
import org.zmz.sb3.security.core.validate.code.ValidateCode;
import org.zmz.sb3.security.core.validate.code.ValidateCodeGenerator;

//@Component("imageValidateCodeGenerator")
public class ExampleImageCodeGenerator implements ValidateCodeGenerator<ValidateCode> {

    public ValidateCode generateCode(ServletWebRequest request) {
        System.out.println(">>>>>> 自定义图形验证码生成逻辑 >>>>>>");
        return null;
    }

}
