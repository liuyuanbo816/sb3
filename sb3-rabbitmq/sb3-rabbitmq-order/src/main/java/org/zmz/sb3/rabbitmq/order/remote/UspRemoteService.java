package org.zmz.sb3.rabbitmq.order.remote;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UspRemoteService {
    @Autowired
    UspApi uspApi;

    public void fundList() {
        TypeReference<UspResponse<List<String>>> typeReference = new TypeReference<>() {};
        UspBaseVO uspBaseVO = new UspBaseVO();

        UspResponse<List<String>> response = uspApi.invoke(typeReference, uspBaseVO);
        List<String> data = response.getData();

    }
}
