package io.spring.waterlevel.configuration;


import io.spring.waterlevel.alerts.AlertStatus;
import io.spring.waterlevel.streamdata.StreamDataService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Water Level Services available for RestfulRequests./
 */
@Service
public class WaterLevelService {

    public WaterLevelService(ApplicationEventPublisher events, StreamDataService streamDataService) {
        this.events = events;
        this.streamDataService = streamDataService;
    }

    private final ApplicationEventPublisher events;

    private final StreamDataService streamDataService;


    /**
     * Retrieves information about the body of water for the sensor specified along with it's alert status.
     * @param sensorId
     * @return
     */
    @Transactional
    public String getAlertForStreamSensor(String sensorId) {
        String result = streamDataService.getStatusForSensor(sensorId);
        events.publishEvent(new AlertStatus(sensorId, result));
        return result;
    }
}
