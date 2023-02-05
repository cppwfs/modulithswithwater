package io.spring.waterlevel.logdata;

import io.spring.waterlevel.configuration.StreamDataStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
public class StreamStateLogger {

    private static final Log log = LogFactory.getLog(StreamStateLogger.class);

    @ApplicationModuleListener
    void on(StreamDataStatus event)  {
        log.info(event.getAlertResult());
    }
}