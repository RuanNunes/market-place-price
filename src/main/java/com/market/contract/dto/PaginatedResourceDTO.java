package com.market.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Stream;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResourceDTO<T> {
	private int thisPage;
	private int lastPage;

	private long totalRecords;

	private List<T> records;

	public Stream<T> stream() {
		return records.stream();
	}
}
