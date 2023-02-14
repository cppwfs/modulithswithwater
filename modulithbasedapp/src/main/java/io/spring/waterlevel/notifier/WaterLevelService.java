package io.spring.waterlevel.notifier;


import io.spring.waterlevel.streamdata.StreamDataService;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Water Level Services available for Restful or scheduled requests.
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
    public String getStatusForStreamSensor(String sensorId) {
        String result = streamDataService.getStatusForSensor(sensorId);
        return result;
    }

    @Transactional
    public String sendAlertForStream(String sensorId) {
        String result = streamDataService.getStatusForSensor(sensorId);
        events.publishEvent(new AlertStatus(sensorId, result));
        return result;
    }

    @Transactional
    public void storeSensorInformationToDataStore(StreamDataStatus status) {
        events.publishEvent(status);

    }
}
