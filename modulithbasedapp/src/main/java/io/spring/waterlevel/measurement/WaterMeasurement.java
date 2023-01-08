package io.spring.waterlevel.measurement;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Information on the water lever for a specific body of water or well.
 */
@Component
public class WaterMeasurement {

    private String measurementKey;
    private String sensorId;

    private String name;
    private ZonedDateTime dateCaptured;
    private Float streamHeight;
    private String status;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");

    public WaterMeasurement(String stringData) {
        String[] rawData = stringData.split("\t");
        Assert.isTrue(rawData.length == 6, "There must be 6 fields in data.  There were " + rawData.length);
        this.sensorId = rawData[1];
        this.dateCaptured = ZonedDateTime.parse(rawData[2] + " " +rawData[3], formatter);
        this.streamHeight = Float.valueOf(rawData[4]);
        this.status = rawData[5];
        measurementKey = this.sensorId + this.dateCaptured;
    }

    public WaterMeasurement() {

    }

    public ZonedDateTime getDateCaptured() {
        return dateCaptured;
    }
    public void setDateCaptured(ZonedDateTime dateCaptured) {
        this.dateCaptured = dateCaptured;
    }
    public String getStatus() {
        return status;
    }
    public String getSensorId() {
        return sensorId;
    }
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return measurementKey + " " + sensorId + " " + this.dateCaptured + " " + this.streamHeight + " " + this.status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurementKey() {
        return measurementKey;
    }

    public void setMeasurementKey(String measurementKey) {
        this.measurementKey = measurementKey;
    }

    public Float getStreamHeight() {
        return streamHeight;
    }

    public void setStreamHeight(Float streamHeight) {
        this.streamHeight = streamHeight;
    }
}
