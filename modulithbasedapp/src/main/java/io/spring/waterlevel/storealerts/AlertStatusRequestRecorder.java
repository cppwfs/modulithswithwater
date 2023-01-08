package io.spring.waterlevel.storealerts;

import io.spring.waterlevel.alerts.AlertStatus;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
public class AlertStatusRequestRecorder {

    @ApplicationModuleListener
    void on(AlertStatus event) throws InterruptedException {

        var sensorId = event.getSensorId();

        System.out.println("Received Request " + sensorId);

        // Simulate busy work
        Thread.sleep(1000);

        System.out.println("Finished Storing request for " + sensorId);
    }
}
