package org.zmz.sb3.security.examples.code;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.zmz.sb3.security.core.validate.code.ImageCode;
import org.zmz.sb3.security.core.validate.code.ValidateCodeGenerator;

@Component("imageValidateCodeGenerator")
public class ExampleImageCodeGenerator implements ValidateCodeGenerator<ImageCode> {

    @Override
    public ImageCode generateCode(HttpServletRequest request) {
        System.out.println(">>>>>> 自定义图形验证码生成逻辑 >>>>>>");
        return null;
    }
}
