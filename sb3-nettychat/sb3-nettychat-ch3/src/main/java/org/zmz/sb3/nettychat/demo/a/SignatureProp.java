package org.zmz.sb3.nettychat.demo.a;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "signature.service.lulu.secret")
public class SignatureProp {
    private String appId;
    private String key;

    @Override
    public String toString() {
        return "SignatureProp{" +
                "appId='" + appId + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
