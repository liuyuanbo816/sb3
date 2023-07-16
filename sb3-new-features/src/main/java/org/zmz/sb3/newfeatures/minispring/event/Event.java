package org.zmz.sb3.newfeatures.minispring.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private String requestNo;
    private EventEnum type;

    enum EventEnum {
        EMAIL, SMS;
    }
}
