package models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.array.IntArrayType;

@Entity(name = "airport")
@TypeDefs({
    @TypeDef(
        name = "int-array", 
        typeClass = IntArrayType.class
    )
})
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	
	private String city;

	@Type( type = "int-array" )
    @Column(
        name = "terminals", 
        columnDefinition = "integer[]"
    )
	private Integer[] terminals;

	private String code;

	public Airport() {
	}

	public Airport(String name, String city, String code, Integer[] terminals) {
		this.name = name;
		this.terminals = terminals;
		this.code = code;
		this.city = city;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Integer[] getTerminals() {
		return this.terminals;
	}

	public String getCode() {
		return this.code;
	}
	
	public String getCity() {
		return this.city;
	}

	public void addTerminal(Integer terminal) {
        Set<Integer> sets = new HashSet<Integer>(Arrays.asList(terminals));
        sets.add(terminal);
        terminals = sets.toArray(terminals);
	}
}
