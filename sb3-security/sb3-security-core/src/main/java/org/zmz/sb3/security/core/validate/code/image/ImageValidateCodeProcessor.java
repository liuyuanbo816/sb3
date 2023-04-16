package org.zmz.sb3.security.core.validate.code.image;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.zmz.sb3.security.core.validate.impl.AbstractValidateCodeProcessor;

import javax.imageio.ImageIO;

@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        HttpServletResponse response = request.getResponse();
        if (response != null)
            ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }
}
