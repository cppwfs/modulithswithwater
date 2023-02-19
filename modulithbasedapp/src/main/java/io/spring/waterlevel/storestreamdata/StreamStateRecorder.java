package io.spring.waterlevel.storestreamdata;

import io.spring.waterlevel.wateradvisor.StreamDataStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
public class StreamStateRecorder {

    private static final Log log = LogFactory.getLog(StreamStateRecorder.class);

    @ApplicationModuleListener
    void on(StreamDataStatus event) throws InterruptedException {

        var sensorId = event.getSensorId();

        log.info("Received Stream Data Store Request " + sensorId);

        log.info("Storing Stream Data for " + sensorId);
    }
}
