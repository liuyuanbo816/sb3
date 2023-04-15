package org.zmz.sb3.security.core.validate.code;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.IOException;

@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @Autowired
    private ValidateCodeGenerator<ImageCode> imageValidateCodeGenerator;

    @RequestMapping("/code/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成验证码
        ImageCode imageCode = imageValidateCodeGenerator.generateCode(request);
        //存到session中
        request.getSession().setAttribute(SESSION_KEY, imageCode);
        //写到响应流中去
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

}
