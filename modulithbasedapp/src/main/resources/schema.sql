CREATE TABLE IF NOT EXISTS creek_status
(
    creek_measurement_key varchar(255) NOT NULL,
    sensor_id varchar(255),
    status varchar(1024)
);