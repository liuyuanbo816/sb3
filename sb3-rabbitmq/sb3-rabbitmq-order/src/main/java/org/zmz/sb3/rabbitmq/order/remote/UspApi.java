package org.zmz.sb3.rabbitmq.order.remote;

import com.fasterxml.jackson.core.type.TypeReference;

public interface UspApi {
    @CommandMapping(url = "",wealthUrl = "")
    <R extends UspBaseVO, V> UspResponse<V> invoke(TypeReference<UspResponse<V>> typeReference, R req);
}
