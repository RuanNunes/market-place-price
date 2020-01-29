package com.market.service;

import java.util.List;

public interface GenericService<T> {
	public T save(final T dto);
	public List<T> findAll();
	public T find(final Long id);
	public T update(final Long id);
}
