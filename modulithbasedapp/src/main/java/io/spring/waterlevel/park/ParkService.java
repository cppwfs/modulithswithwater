package io.spring.waterlevel.park;

import io.spring.waterlevel.wateradvisor.StreamDataStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
public class ParkService {

    private static final Log log = LogFactory.getLog(ParkService.class);

    @ApplicationModuleListener
    void on(StreamDataStatus event)  {
        log.info(event.getStatus());
    }
}