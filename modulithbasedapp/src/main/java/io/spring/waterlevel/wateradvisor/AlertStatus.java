package io.spring.waterlevel.wateradvisor;

import org.jmolecules.event.types.DomainEvent;

public class AlertStatus implements DomainEvent {
    String sensorId;

    String alertResult;

    public AlertStatus(String sensorId, String alertResult) {
        this.sensorId = sensorId;
        this.alertResult = alertResult;

    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getAlertResult() {
        return alertResult;
    }
}
