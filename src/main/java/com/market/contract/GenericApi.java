package com.market.contract;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

public interface GenericApi<T, V> {
	
	ResponseEntity<Void> save(@Valid @RequestBody final T dto);
	
	ResponseEntity<?> findAll();
	
	ResponseEntity<?> find(@PathVariable final  Long id) ;
	
	ResponseEntity<?> update(@Valid @RequestBody final T dto,@PathVariable final Long id);

	ResponseEntity<?> findPaginate(@SpringQueryMap @Valid final V filters);

}
