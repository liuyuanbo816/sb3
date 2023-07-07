package org.zmz.sb3.rabbitmq.order.remote;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UspConfig {

    @Bean
    public UspApi uspApi() {
        return new UspApi() {
            @Override
            public <R extends UspBaseVO, V> UspResponse<V> invoke(TypeReference<UspResponse<V>> typeReference, R req) {
                return null;
            }
        };
    }

}
