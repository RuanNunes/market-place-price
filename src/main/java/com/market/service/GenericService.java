package com.market.service;

import com.market.contract.dto.PaginatedResourceDTO;

import java.util.List;

public interface GenericService<T, V> {
	public T save(final T dto);
	public List<T> findAll();
	public T find(final Long id);
	public T update(final T dto,final Long id);
	public PaginatedResourceDTO<T> findPaginate(final V filter);
}
