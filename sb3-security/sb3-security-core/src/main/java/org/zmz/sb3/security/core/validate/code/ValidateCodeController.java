package org.zmz.sb3.security.core.validate.code;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.zmz.sb3.security.core.validate.ValidateCodeProcessor;

import java.util.Map;

@RestController
public class ValidateCodeController {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @RequestMapping("/code/{type}")
    public void code(HttpServletRequest request,
                     HttpServletResponse response,
                     @PathVariable String type) throws Exception {
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(type + "ValidateCodeProcessor");
        validateCodeProcessor.doInvoke(new ServletWebRequest(request, response));
    }
}
