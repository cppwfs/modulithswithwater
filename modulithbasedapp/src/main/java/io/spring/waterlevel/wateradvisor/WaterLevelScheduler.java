package io.spring.waterlevel.wateradvisor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class WaterLevelScheduler {
    private static final Log log = LogFactory.getLog(WaterLevelService.class);
    private static final String SENSOR_IDS = "02311500,02323566,02312700";

    private WaterLevelService waterLevelService;

    public WaterLevelScheduler(WaterLevelService waterLevelService) {
        this.waterLevelService = waterLevelService;
    }

   @Scheduled(fixedRate = 15000)
    public void handleSensorEvent() {
        log.info("Requesting Sensor Data for configured Sensors");
        String result = waterLevelService.getStatusForStreamSensor(SENSOR_IDS);
        waterLevelService.sendAlertForStream(SENSOR_IDS, result);
        waterLevelService.sendDataNotification(SENSOR_IDS, result);
    }
}
