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
import com.vladmihalcea.hibernate.type.array.StringArrayType;

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

	@Type( type = "int-array" )
    @Column(
        name = "terminals", 
        columnDefinition = "integer[]"
    )
	private Integer[] terminals;

	private String code;

	public Airport() {
	}

	public Airport(String name, String code, Integer[] terminals) {
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

	public Integer[] getTerminals() {
		return this.terminals;
	}

	public String getCode() {
		return this.code;
	}

	public void addTerminal(Integer terminal) {
        Set<Integer> sets = new HashSet<Integer>(Arrays.asList(terminals));
        sets.add(terminal);
        terminals = sets.toArray(terminals);
	}
}
