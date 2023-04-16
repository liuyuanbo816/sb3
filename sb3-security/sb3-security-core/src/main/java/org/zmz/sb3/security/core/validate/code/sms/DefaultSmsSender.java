package org.zmz.sb3.security.core.validate.code.sms;

public class DefaultSmsSender implements SmsSender {
    @Override
    public void send(String mobile, String code) {
        System.out.printf("向手机号 %s 发送短信验证码 %s\n", mobile, code);
    }
}
