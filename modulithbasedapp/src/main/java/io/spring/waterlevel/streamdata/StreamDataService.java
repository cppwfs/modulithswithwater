package io.spring.waterlevel.streamdata;

import io.spring.waterlevel.streamdata.usgsstream.USGSStreamData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StreamDataService {

    public String getStatusForSensor(String sensorId) {
        ZoneId zoneId = ZoneId.of("America/New_York");
        LocalDateTime endTime = LocalDateTime.now(zoneId);
        LocalDateTime startTime = endTime.minusHours(5);
        RestTemplate template = new RestTemplate();
        String result = "Error Obtaining results for " + sensorId;
        try {
            result = template.getForObject("https://waterservices.usgs.gov/nwis/iv/?sites=" + sensorId +
                    "&parameterCd=00065&startDT=" + startTime + "&endDT=" +
                    endTime + "&siteStatus=all&format=rdb", String.class);

        List<USGSStreamData> creekMeasurements = transformUsgsStreamData(result);
        result = getStatus(creekMeasurements);
        } catch(Exception exception) {
            System.out.println("Failed to retrieve data from USGS using sample date");;
        }
        return result;
    }
    private String getStatus(List<USGSStreamData> creekMeasurements) {
        USGSStreamData controlMeasurement = null;
        USGSStreamData previousMeasurement = null;
        String result = "";
        for (USGSStreamData measurement : creekMeasurements) {
            if (controlMeasurement == null) {
                controlMeasurement = measurement;
                continue;
            }
            if (!measurement.getSensorId().equals(controlMeasurement.getSensorId())) {
                result += getSymbol(controlMeasurement, previousMeasurement) + " " + previousMeasurement.getName() + "\n";

                controlMeasurement = measurement;
            }
            previousMeasurement = measurement;
        }
        result += getSymbol(controlMeasurement, previousMeasurement) + " " + previousMeasurement.getName() + "\n";
        return result;
    }


    private String getSymbol(USGSStreamData controlMeasurement, USGSStreamData previousMeasurement) {
        double warnPercentage = ((previousMeasurement.getStreamHeight() - controlMeasurement.getStreamHeight())
                / previousMeasurement.getStreamHeight());
        String symbol = Character.toString('\u2705');
        if (Math.abs(warnPercentage) > 5) {
            symbol = Character.toString('\u274c');
        }
        return symbol;
    }

    private List<USGSStreamData> transformUsgsStreamData(String rawData) {

        String[] results = rawData.split(System.lineSeparator());

        Map<String, String> streamMetadata = getMetaData(results);
        List<String> arrayData = Arrays.stream(results)
                .filter(result -> result.startsWith("USGS"))
                .collect(Collectors.toList());
        List<USGSStreamData> USGSStreamData = new ArrayList<>();
        arrayData.forEach(streamData -> {
            USGSStreamData UsgsStreamData = new USGSStreamData(streamData);
            UsgsStreamData.setName(streamMetadata.get(UsgsStreamData.getSensorId()));
            USGSStreamData.add(UsgsStreamData);
        });
        return USGSStreamData;
    }

    private Map<String, String> getMetaData(String[] arrayData) {
        Map<String, String> result = new HashMap<>();

        for (String row : arrayData) {
            if (row.startsWith("#    USGS")) {
                String[] tokens = row.split(" ");
                result.put(row.substring(10, 18), row.substring(19));
            }
        }
        return result;
    }
}
