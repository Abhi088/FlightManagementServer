package models;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GenerationType;

@Entity(name = "flight")
public class Flight {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	@NotNull
	@NotBlank
	private String flightId;
	
	@NotNull
	@NotBlank
	private String airline;
	
	@NotNull
	@NotBlank
	private String source;
	
	@NotNull
	@NotBlank
	private String destination;
	
	@NotNull
	private Time departure;
	
	@NotNull
	private Time arrival;
	
	public Flight() {}
	
	public Flight(String airline, String flightId, String source, String destination, Time departure, Time arrival) {
		this.airline = airline;
		this.flightId = flightId;
		this.source = source;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getFlightId() {
		return this.flightId;
	}
	
	public String getAirline() {
		return this.airline;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public String getDestination() {
		return this.destination;
	}
	
	public Time getDeparture() {
		return this.departure;
	}
	
	public Time getArrival() {
		return this.arrival;
	}
}