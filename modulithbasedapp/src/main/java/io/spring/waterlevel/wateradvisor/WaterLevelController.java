package io.spring.waterlevel.wateradvisor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Allows users to request the Alert Status for the specified body of water based on the sensor.
 */
@RestController
public class WaterLevelController {

    private final WaterLevelService service;

    public WaterLevelController(WaterLevelService service) {
        this.service = service;
    }

    @GetMapping("/streamdata")
    public String streamDataRequest(@RequestParam(name="sensorid", required=false, defaultValue="02335757") String sensorId) {
        return "Result for stream sensor(s) is: " + service.getStatusForStreamSensor(sensorId);
    }

    @GetMapping("/sendAlertsForStream")
    public String sendAlertsForStream(@RequestParam(name="sensorid", required=false, defaultValue="1234") String sensorId) {
        return "Alert Sent for stream sensor(s) is: " + service.sendAlertForStream(sensorId);
    }

}
