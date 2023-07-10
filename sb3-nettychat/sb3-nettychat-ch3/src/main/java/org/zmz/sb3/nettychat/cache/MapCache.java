package org.zmz.sb3.nettychat.cache;

import org.springframework.stereotype.Service;

@Service
public class MapCache implements Cache{
    @Override
    public String simpleCache() {
        return "MapCache";
    }
}
