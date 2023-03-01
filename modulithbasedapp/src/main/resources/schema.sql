DROP TABLE IF EXISTS stream_status ;
CREATE TABLE IF NOT EXISTS stream_status
(
    sensor_id varchar(255),
    status varchar(1024)
);