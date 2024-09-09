package io.spring.waterlevel.park;

import io.spring.waterlevel.wateradvisor.StreamDataStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class ParkService {

    private static final Log log = LogFactory.getLog(ParkService.class);

    @TransactionalEventListener
    void on(StreamDataStatus event)  {
        log.info(event.getStatus());
    }
}