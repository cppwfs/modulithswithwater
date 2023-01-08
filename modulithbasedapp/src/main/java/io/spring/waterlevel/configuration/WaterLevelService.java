package io.spring.waterlevel.configuration;


import io.spring.waterlevel.alerts.AlertStatus;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Water Level Services available for RestfulRequests./
 */
@Service
public class WaterLevelService {

    public WaterLevelService(ApplicationEventPublisher events) {
        this.events = events;
    }

    private final ApplicationEventPublisher events;


    /**
     * Retrieves information about the body of water for the sensor specified along with it's alert status.
     * @param sensorId
     * @return
     */
    @Transactional
    public String getAlertForSensor(String sensorId) {
        String result = "Good";
        events.publishEvent(new AlertStatus(sensorId, result));
        return result;
    }
}
