package com.market.contract;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface GenericApi<T> {
	
	public  ResponseEntity<Void> save(@Valid @RequestBody T dto);
	
	public ResponseEntity<?> findAll();
	
	public ResponseEntity<?> find(@PathVariable String id) ;

}
