package org.zmz.sb3.security.core.validate.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.zmz.sb3.security.core.validate.ValidateCodeProcessor;
import org.zmz.sb3.security.core.validate.code.ValidateCode;
import org.zmz.sb3.security.core.validate.code.ValidateCodeException;
import org.zmz.sb3.security.core.validate.code.ValidateCodeGenerator;
import org.zmz.sb3.security.core.validate.code.ValidateCodeType;

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
        String type = getValidateCodeType().toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator<T> validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器 " + generatorName + " 不存在");
        }
        return validateCodeGenerator.generateCode(request);
    }

    private void save(ServletWebRequest request, T validateCode) {
        HttpSession session = request.getRequest().getSession();
        session.setAttribute(getSessionKey(), validateCode);
    }

    /**
     * 构建验证码放入session时的key
     */
    private String getSessionKey() {
        return SESSION_KEY_PREFIX + getValidateCodeType().toString().toUpperCase();
    }

    protected abstract void send(ServletWebRequest request, T code) throws Exception;

    /**
     * 根据请求的url获取校验码的类型
     */
    private ValidateCodeType getValidateCodeType() {
        String className = this.getClass().getSimpleName();
        int pos = className.indexOf("ValidateCodeProcessor");
        String type = className.substring(0, pos);
        return ValidateCodeType.valueOf(type.toUpperCase());
    }


    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType processorType = getValidateCodeType();
        String sessionKey = getSessionKey();

        HttpSession session = request.getRequest().getSession();
        T codeInSession = (T) session.getAttribute(sessionKey);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    processorType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (!StringUtils.hasText(codeInRequest)) {
            throw new ValidateCodeException(processorType + " 验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(processorType + " 验证码不存在");
        }

        if (codeInSession.isExpired()) {
            session.removeAttribute(sessionKey);
            throw new ValidateCodeException(processorType + " 验证码已过期");
        }

        if (!codeInRequest.equals(codeInSession.getCode())) {
            throw new ValidateCodeException(processorType + " 验证码不匹配");
        }

        session.removeAttribute(sessionKey);
    }
}
