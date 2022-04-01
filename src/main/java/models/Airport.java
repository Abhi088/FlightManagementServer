package models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "airport")
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private Integer terminals;

	private String code;

	public Airport() {
	}

	public Airport(String name, String code, Integer terminals) {
		this.name = name;
		this.terminals = terminals;
		this.code = code;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Integer getTerminals() {
		return this.terminals;
	}

	public String getCode() {
		return this.code;
	}

//	public void addTerminal(Integer terminal) {
//		this.terminals.add(terminal);
//	}
}
