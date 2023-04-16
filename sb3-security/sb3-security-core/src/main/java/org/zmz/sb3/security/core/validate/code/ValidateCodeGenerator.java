package org.zmz.sb3.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator<T extends ValidateCode> {
    T generateCode(ServletWebRequest request);
}
