package org.zmz.sb3.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {
    /**
     * 验证码放入 session 时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 真正执行 验证码 整个流程的方法
     */
    void doInvoke(ServletWebRequest request) throws Exception;
}
