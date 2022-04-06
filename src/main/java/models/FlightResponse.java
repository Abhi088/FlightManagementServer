package models;

import java.sql.Time;
import java.util.Date;

public class FlightResponse extends Flight {
	
	private String source_code;
	private String source_city;
	private String source_airport;
	private String destination_code;
	private String destination_city;
	private String destination_airport;
	private Date flight_departure;
	private Date flight_duration;
	
	
	public FlightResponse(Long id, String flight_id, String type, String airline, Integer source_terminal,
			Integer destination_terminal, Date flight_departure, Date flight_duration, String[] start_days, Integer total_seats, Integer adult_price,
			Integer infant_price, Integer check_in_baggage, Integer cabin_baggage, String source_code,
			String source_airport, String source_city, String destination_code, String destination_airport,
			String destination_city) {
		this.id = id;
		this.flight_id = flight_id;
		this.type = type;
		this.airline = airline;
		this.source_terminal = source_terminal;
		this.destination_terminal = destination_terminal;
		this.flight_departure = flight_departure;
		this.flight_duration = flight_duration;
		this.start_days = start_days;
		this.total_seats = total_seats;
		this.adult_price = adult_price;
		this.infant_price = infant_price;
		this.check_in_baggage = check_in_baggage;
		this.cabin_baggage = cabin_baggage;
		this.source_code = source_code;
		this.source_city = source_city;
		this.source_airport = source_airport;
		this.destination_code = destination_code;
		this.destination_city = destination_city;
		this.destination_airport = destination_airport;
	}
	
	public Date getFlight_departure() {
		return flight_departure;
	}

	public Date getFlight_duration() {
		return flight_duration;
	}

	public String getSource_code() {
		return this.source_code;
	}

	public String getSource_city() {
		return source_city;
	}

	public String getSource_airport() {
		return source_airport;
	}

	public String getDestination_code() {
		return destination_code;
	}

	public String getDestination_city() {
		return destination_city;
	}

	public String getDestination_airport() {
		return destination_airport;
	}
	
}
