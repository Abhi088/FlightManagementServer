package models;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
	private Integer source_terminal;
	
	@NotNull
	@NotBlank
	private String destination;
	
	private Integer destination_terminal;
	
	@NotNull
	private Time departure;
	
	@NotNull
	private Time arrival;
	
	public Flight() {}
	
	public Flight(String flightId, String source, Integer source_terminal, Integer destination_terminal, String destination, String airline, Time departure, Time arrival) {
		this.flightId = flightId;
		this.source = source;
		this.source_terminal = source_terminal;
		this.airline = airline;
		this.destination = destination;
		this.destination_terminal = destination_terminal;
		this.departure = departure;
		this.arrival = arrival;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getFlightId() {
		return this.flightId;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public Integer getSource_terminal() {
		return this.source_terminal;
	}
	
	public String getAirline() {
		return this.airline;
	}
	
	public String getDestination() {
		return this.destination;
	}
	
	public Integer getDestination_terminal() {
		return this.destination_terminal;
	}
	
	public Time getDeparture() {
		return this.departure;
	}
	
	public Time getArrival() {
		return this.arrival;
	}
}