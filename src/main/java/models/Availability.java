package models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Availability {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String flight_id;

	private Integer available_seats;
	
	private Date date_of_flight;

	public Availability() {
	}

	public Availability(String flight, Integer available_seats, Date date_of_flight) {
		this.flight_id = flight;
		this.available_seats = available_seats;
		this.date_of_flight = date_of_flight;
	}

	public Long getId() {
		return this.id;
	}

	public String getFlight_id() {
		return this.flight_id;
	}

	public Integer getAvailable_seats() {
		return this.available_seats;
	}

	public Date getDate_of_flight() {
		return this.date_of_flight;
	}
}
