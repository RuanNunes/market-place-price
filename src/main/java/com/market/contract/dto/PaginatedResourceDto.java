package com.market.contract.dto;

import java.util.List;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResourceDto<T> {
	private int thisPage;
	private int lastPage;

	private long totalRecords;

	private List<T> records;

	public Stream<T> stream() {
		return records.stream();
	}
}
