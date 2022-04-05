package models;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;

@TypeDefs({ @TypeDef(name = "int-array", typeClass = IntArrayType.class),
		@TypeDef(name = "char-array", typeClass = StringArrayType.class) })

@MappedSuperclass
public class BaseEntity {

	@Id
	private Long id;

	@Version
	private Integer version;

	// Getters and setters omitted for brevity
}