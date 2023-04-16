package org.zmz.sb3.security.core.validate.code;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zmz.sb3.security.core.validate.code.image.ImageCode;
import org.zmz.sb3.security.core.validate.code.sms.SmsSender;

import javax.imageio.ImageIO;
import java.io.IOException;

@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY_IMAGE = "SESSION_KEY_IMAGE_CODE";
    public static final String SESSION_KEY_SMS = "SESSION_KEY_IMAGE_CODE";

    @Autowired
    private ValidateCodeGenerator<ImageCode> imageValidateCodeGenerator;

    @Autowired
    private ValidateCodeGenerator<ValidateCode> smsValidateCodeGenerator;

    @Autowired
    private SmsSender defaultSmsSender;

    @RequestMapping("/code/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成验证码
        ImageCode imageCode = imageValidateCodeGenerator.generateCode(request);
        //存到session中
        request.getSession().setAttribute(SESSION_KEY_IMAGE, imageCode);
        //写到响应流中去
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    @RequestMapping("/code/sms")
    public void smsCode(HttpServletRequest request) throws ServletRequestBindingException {
        //生成验证码
        ValidateCode validateCode = smsValidateCodeGenerator.generateCode(request);
        //存到session中
        request.getSession().setAttribute(SESSION_KEY_SMS, validateCode);
        //第三方短信发送
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        defaultSmsSender.send(mobile, validateCode.getCode());
    }


}
