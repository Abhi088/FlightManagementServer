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
	private String flight_id;
	
	@NotNull
	@NotBlank
	private String type;
	
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
	private Time duration;
	
	private Integer total_seats;
	
	private Integer adult_price;
	
	private Integer infant_price;
	
	private Integer check_in_baggage;
	
	private Integer cabin_baggage;
	
	public Flight() {}
	
	public Flight(String flight_id, String type, String airline, String source, Integer source_terminal, String destination, Integer destination_terminal, Time departure, Time duration, Integer total_seats, Integer adult_price, Integer infant_price, Integer check_in_baggage, Integer cabin_baggage) {
		this.flight_id = flight_id;
		this.type = type;
		this.airline = airline;
		this.source = source;
		this.source_terminal = source_terminal;
		this.destination = destination;
		this.destination_terminal = destination_terminal;
		this.departure = departure;
		this.duration = duration;
		this.total_seats = total_seats;
		this.adult_price = adult_price;
		this.infant_price = infant_price;
		this.check_in_baggage = check_in_baggage;
		this.cabin_baggage = cabin_baggage;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getFlight_id() {
		return this.flight_id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getAirline() {
		return this.airline;
	}
	
	public String getSource() {
		return this.source;
	}
	
	public Integer getSource_terminal() {
		return this.source_terminal;
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
	
	public Time getDuration() {
		return this.duration;
	}
	
	public Integer getTotal_seats() {
		return this.total_seats;
	}
	
	public Integer getAdult_price() {
		return this.adult_price;
	}
	
	public Integer getInfant_price() {
		return this.infant_price;
	}
	
	public Integer getCheck_in_baggage() {
		return this.check_in_baggage;
	}
	
	public Integer getCabin_baggage() {
		return this.cabin_baggage;
	}
}