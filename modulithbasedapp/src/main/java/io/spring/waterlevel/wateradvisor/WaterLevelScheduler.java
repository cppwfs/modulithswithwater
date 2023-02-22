package io.spring.waterlevel.wateradvisor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class WaterLevelScheduler {
    private static final Log log = LogFactory.getLog(WaterLevelService.class);
    private static final String SENSOR_IDS = "02311500,02323566,02312700";

}
