package io.spring.waterlevel.storestreamdata;

import io.spring.waterlevel.wateradvisor.StreamDataStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class StreamStateRecorder {

    private static final Log log = LogFactory.getLog(StreamStateRecorder.class);

    private DataSource dataSource;

    public  StreamStateRecorder(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @ApplicationModuleListener
    void on(StreamDataStatus event) throws InterruptedException {

        var sensorId = event.getSensorId();

        log.info("Received Stream Data Store Request " + sensorId);
        JdbcTemplate template = new JdbcTemplate(this.dataSource);
        template.update("INSERT INTO stream_status (sensor_id, status) VALUES(?, ?)",
                event.getSensorId(), event.getStatus());
        log.info("Storing Stream Data for " + sensorId);
    }

}
