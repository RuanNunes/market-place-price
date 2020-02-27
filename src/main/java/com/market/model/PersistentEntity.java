package com.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@MappedSuperclass
public class PersistentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@CreationTimestamp
	@CreatedDate
	@Column(name = "created_date", updatable = false, nullable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@LastModifiedDate
	@Column(name = "last_modified_date", nullable = false)
	private LocalDateTime lastModifiedDate;

	@Version
	@Column(name = "version", nullable = false)
	private Integer version;

	@Transient
	public boolean isPersisted() {
		return this.getId() != null;
	}

}
