package org.zmz.sb3.security.core.validate.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;
import org.zmz.sb3.security.core.validate.ValidateCodeProcessor;
import org.zmz.sb3.security.core.validate.code.ValidateCode;
import org.zmz.sb3.security.core.validate.code.ValidateCodeGenerator;

import java.util.Map;

public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 根据验证码类型不同 调用 {@link ValidateCodeGenerator} 不同实现
     */
    @Autowired
    private Map<String, ValidateCodeGenerator<T>> validateCodeGenerators;

    @Override
    public void doInvoke(ServletWebRequest request) throws Exception {
        // 生成验证码
        T validateCode = generate(request);
        // 保存验证码
        save(request, validateCode);
        // 发送/写入到响应输出流
        send(request, validateCode);
    }

    private T generate(ServletWebRequest request) {
        String codeType = getProcessorType(request);
        ValidateCodeGenerator<T> validateCodeGenerator = validateCodeGenerators.get(codeType + "ValidateCodeGenerator");
        return validateCodeGenerator.generateCode(request);
    }

    private void save(ServletWebRequest request, ValidateCode validateCode) {
        HttpSession session = request.getRequest().getSession();
        String codeType = getProcessorType(request);
        session.setAttribute(SESSION_KEY_PREFIX + codeType.toUpperCase(), validateCode);
    }

    protected abstract void send(ServletWebRequest request, T code) throws Exception;

    private String getProcessorType(ServletWebRequest request) {
        String requestURI = request.getRequest().getRequestURI();
        int index = requestURI.indexOf("/code/");
        return requestURI.substring(index + "/code/".length());
    }
}
