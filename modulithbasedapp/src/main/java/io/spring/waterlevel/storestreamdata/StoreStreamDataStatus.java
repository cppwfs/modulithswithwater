package io.spring.waterlevel.storestreamdata;

import org.jmolecules.event.types.DomainEvent;

public class StoreStreamDataStatus implements DomainEvent {
    String sensorId;

    String alertResult;

    public StoreStreamDataStatus(String sensorId, String alertResult) {
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
