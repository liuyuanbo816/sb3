package org.zmz.sb3.nettychat.cache;

import org.springframework.stereotype.Service;

@Service
public class PropertiesCache implements Cache{
    @Override
    public String simpleCache() {
        return "PropertiesCache";
    }
}
