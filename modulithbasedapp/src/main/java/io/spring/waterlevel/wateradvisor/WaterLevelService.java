package io.spring.waterlevel.wateradvisor;


import io.spring.waterlevel.usgs.UsgsService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Water Level Services available for Restful or scheduled requests.
 */
@Service
public class WaterLevelService {

    public WaterLevelService(ApplicationEventPublisher events, UsgsService usgsService) {
        this.events = events;
        this.usgsService = usgsService;
    }

    private final ApplicationEventPublisher events;

    private final UsgsService usgsService;


    /**
     * Retrieves information about the body of water for the sensor specified along with it's alert status.
     * @param sensorIds
     * @return
     */
    @Transactional
    public String getStatusForStreamSensor(String sensorIds) {
        String result = usgsService.getStatusForSensor(sensorIds);
        return result;
    }

    @Transactional
    public void sendAlertForStream(String sensorIds, String result) {
        if(result.indexOf('\u2705') > -1) {
            events.publishEvent(new AlertStatus(sensorIds, result));
        }
    }

    @Transactional
    public void sendDataNotification(String sensorIds, String result) {
        events.publishEvent(new StreamDataStatus(sensorIds, result));

    }
}
