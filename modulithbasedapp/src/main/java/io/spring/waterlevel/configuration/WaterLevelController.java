package io.spring.waterlevel.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Allows users to request the Alert Status for the specified body of water based on the sensor.
 */
@RestController
public class WaterLevelController {

    @Autowired
    private WaterLevelService service;

    @GetMapping("/streamdata")
    public String streamDataRequest(@RequestParam(name="sensorid", required=false, defaultValue="1234") String sensorId) {
        return "Result for stream sensor is: " + service.getAlertForSensor(sensorId);
    }

    @GetMapping("/welldata")
    public String wellDataRequest(@RequestParam(name="sensorid", required=false, defaultValue="1234") String sensorId) {
        return "Result for well sensor is: " + service.getAlertForSensor(sensorId);
    }

}
