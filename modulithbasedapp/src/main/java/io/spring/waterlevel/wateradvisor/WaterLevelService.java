package io.spring.waterlevel.wateradvisor;


import io.spring.waterlevel.streamdata.StreamDataService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void sendAlertForStream(String sensorId, String result) {
        if(result.indexOf('\u2705') > -1) {
            events.publishEvent(new AlertStatus(sensorId, result));
        }
    }

    @Transactional
    public void sendDataNotification(String sensorId, String result) {
        events.publishEvent(new StreamDataStatus(sensorId, result));

    }
}
