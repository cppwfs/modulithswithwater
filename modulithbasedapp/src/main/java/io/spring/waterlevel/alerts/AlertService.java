package io.spring.waterlevel.alerts;

import io.spring.waterlevel.welldata.WellWaterData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

//    public void alerts(USGSWellWaterData waterData) {
//        System.out.println("Did Something: " + waterData);
//    }

    public void alerts(WellWaterData waterData) {
        System.out.println("Did Something: " + waterData);
    }

    public void alertWellData(List<WellWaterData> wellWaterData) {
        System.out.println("Well Water do stuff");
    }

    void on(AlertStatus event) throws InterruptedException {

        var sensorId = event.getSensorId();

        System.out.println("Received Request " + sensorId);

        // Simulate busy work
        Thread.sleep(1000);

        System.out.println("Finished order completion for " + sensorId);
    }
}
