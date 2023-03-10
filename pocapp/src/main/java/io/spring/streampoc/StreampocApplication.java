package io.spring.streampoc;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties(CreekProperties.class)
public class StreampocApplication {

	private static final Log logger = LogFactory
			.getLog(StreampocApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StreampocApplication.class, args);
	}

	@Autowired
	private CreekRepository repository;

	@Autowired
	private ConfigurableApplicationContext context;

	@Bean
	public ApplicationRunner applicationRunner(CreekProperties creekProperties, Function<String, List<CreekMeasurement>> transformCreekMeasurement, Consumer<List<CreekMeasurement>> produceReport) {
		return new ApplicationRunner() {

			@Override
			public void run(ApplicationArguments args) {

				ZoneId zoneId = ZoneId.of("America/New_York");
				LocalDateTime endTime = LocalDateTime.now(zoneId);
				LocalDateTime startTime = endTime.minusHours(5);
				RestTemplate template = new RestTemplate();
				String result = SAMPLE_DATA;
				try {
					result = template.getForObject("https://waterservices.usgs.gov/nwis/iv/?sites=" + "02336300,02335757,02312700" +
							"&parameterCd=00065&startDT=" + startTime + "&endDT=" +
							endTime + "&siteStatus=all&format=rdb", String.class);
				} catch(Exception exception) {
					System.out.println("Failed to retrieve data from USGS using sample date");;
				}
				List<CreekMeasurement> creekMeasurements = transformCreekMeasurement.apply(result);
				produceReport.accept(creekMeasurements);
				context.close();
			}
		};
	}

	@Bean
	protected Function<String, List<CreekMeasurement>> transformCreekMeasurement() {
		return new Function<String,List<CreekMeasurement>>() {

			@Override
			public List<CreekMeasurement> apply(String rawData) {
				String[] results = rawData.split(System.lineSeparator());

				Map<String, String> streamMetadata = getMetaData(results);
				List<String> arrayData = Arrays.stream(results)
						.filter(result -> result.startsWith("USGS"))
						.collect(Collectors.toList());
				List<CreekMeasurement> creekMeasurements = new ArrayList<>();
				arrayData.forEach(streamData -> {
					CreekMeasurement creekMeasurement = new CreekMeasurement(streamData);
					creekMeasurement.setName(streamMetadata.get(creekMeasurement.getSensorId()));
					creekMeasurements.add(creekMeasurement);
					repository.save(creekMeasurement);
				});
				return creekMeasurements;
			}
			
		};
	}

	@Bean
	protected Consumer<List<CreekMeasurement>> produceReport(CreekProperties properties) {
		return new Consumer<List<CreekMeasurement>>() {

			@Override
			public void accept(List<CreekMeasurement> creekMeasurements) {
				CreekMeasurement controlMeasurement = null;
				CreekMeasurement previousMeasurement = null;
				for (CreekMeasurement measurement : creekMeasurements) {
					if (controlMeasurement == null) {
						controlMeasurement = measurement;
						continue;
					}
					if (!measurement.getSensorId().equals(controlMeasurement.getSensorId())) {
						logger.info(getSymbol(controlMeasurement, previousMeasurement, properties) + " " +
								previousMeasurement.getName()) ;
		
						controlMeasurement = measurement;
					}
					previousMeasurement = measurement;
				}
				logger.info(getSymbol(controlMeasurement, previousMeasurement, properties) + " " +
						previousMeasurement.getName());
			}
			
		};
	}

	private String getSymbol(CreekMeasurement controlMeasurement, CreekMeasurement previousMeasurement,
			CreekProperties properties) {
		double warnPercentage = ((previousMeasurement.getStreamHeight() - controlMeasurement.getStreamHeight() )
				/ previousMeasurement.getStreamHeight());
		String symbol = Character.toString('\u2705');
		if (Math.abs(warnPercentage) > properties.getWarningPercent()) {
			symbol = Character.toString('\u274c');
		}
//		System.out.println("***** " + warnPercentage);

		return symbol;
	}

	private static final String SAMPLE_DATA = """
				USGS	02335757	2022-03-23 04:45	EDT	3.33	P
				USGS	02335757	2022-03-23 05:00	EDT	3.36	P
				USGS	02335757	2022-03-23 05:15	EDT	3.38	P
				USGS	02335757	2022-03-23 05:30	EDT	3.40	P
				USGS	02335757	2022-03-23 05:45	EDT	3.42	P
				USGS	02335757	2022-03-23 06:00	EDT	3.43	P
				USGS	02335757	2022-03-23 06:15	EDT	3.45	P
				USGS	02335757	2022-03-23 06:30	EDT	3.46	P
				USGS	02335757	2022-03-23 06:45	EDT	3.52	P
				USGS	02335757	2022-03-23 07:00	EDT	3.56	P
				USGS	02335757	2022-03-23 07:15	EDT	3.63	P
				USGS	02335757	2022-03-23 07:30	EDT	3.72	P
				USGS	02335757	2022-03-23 07:45	EDT	3.94	P
				USGS	02335757	2022-03-23 08:00	EDT	4.03	P
				USGS	02335757	2022-03-23 08:15	EDT	4.00	P
				USGS	02335757	2022-03-23 08:30	EDT	4.04	P
				USGS	02335757	2022-03-23 08:45	EDT	4.12	P
				USGS	02335757	2022-03-23 09:00	EDT	4.20	P
				USGS	02335757	2022-03-23 09:15	EDT	4.27	P
				USGS	02335757	2022-03-23 09:30	EDT	4.28	P
				USGS	02335757	2022-03-23 09:45	EDT	4.33	P
				USGS	02335757	2022-03-23 10:00	EDT	4.36	P
				USGS	02335757	2022-03-23 10:15	EDT	4.41	P""";


	private Map<String, String> getMetaData(String[] arrayData) {
		Map<String, String> result = new HashMap<>();

		for(String row : arrayData) {
			if(row.startsWith("#    USGS")) {
				String[] tokens = row.split(" ");
				result.put(row.substring(10, 18), row.substring(19));
			}
		}
		return result;
	}

}
