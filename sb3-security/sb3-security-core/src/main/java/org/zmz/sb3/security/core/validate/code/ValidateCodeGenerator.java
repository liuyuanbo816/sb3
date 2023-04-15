package org.zmz.sb3.security.core.validate.code;

import jakarta.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator<T extends ValidateCode> {
    T generateCode(HttpServletRequest request);
}
