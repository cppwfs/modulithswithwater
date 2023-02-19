package io.spring.waterlevel.wateradvisor;

import org.jmolecules.event.types.DomainEvent;

public class StreamDataStatus implements DomainEvent {
    String sensorId;

    String status;

    public StreamDataStatus(String sensorId, String status) {
        this.sensorId = sensorId;
        this.status = status;

    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getStatus() {
        return status;
    }
}
