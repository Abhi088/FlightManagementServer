package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity(name = "flight")
public class Flight {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;

	public String name;
	
	public String source;
	public String destination;
	
	public Flight() {}
	
	public Flight(String name, String source, String destination) {
		this.name = name;
		this.source = source;
		this.destination = destination;
	}
}