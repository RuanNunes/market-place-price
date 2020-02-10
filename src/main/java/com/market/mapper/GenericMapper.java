package com.market.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.google.common.reflect.TypeToken;
import com.market.contract.dto.PaginatedResourceDTO;

@Component
public class GenericMapper<Entity, Dto> {

	  @SuppressWarnings("serial")
	  private final TypeToken<Entity> entityType = new TypeToken<Entity>(getClass()) {};
	  @SuppressWarnings("serial")
	  private final TypeToken<Dto> dtoType = new TypeToken<Dto>(getClass()) {};

	  @Autowired
	  private ModelMapper modelMapper;

	  public Entity toEntity(final Dto dto) {
	    return modelMapper.map(dto, entityType.getType());
	  }

	  public Entity updateEntity(final Entity entity, final Dto dto) {
	    modelMapper.map(dto, entity);
	    return entity;
	  }

	  public Dto updateDto(final Dto findDto, final Dto dto) {
		    modelMapper.map(dto, findDto);
		    return findDto;
	  }
	  
	  public Dto toDto(final Entity entity) {
	    return modelMapper.map(entity, dtoType.getType());
	  }

	  public List<Dto> toDto(final List<Entity> entities) {
	    return entities.stream().map(it -> toDto(it)).collect(Collectors.toList());
	  }

	public List<Entity> toEntity(final List<Dto> Dtos) {
		return Dtos.stream().map(it -> toEntity(it)).collect(Collectors.toList());
	}

	public PaginatedResourceDTO<Dto> toDto(final Page<Entity> page) {
	    return PaginatedResourceDTO.<Dto>builder()
	        .records(toDto(page.getContent()))
	        .totalRecords(page.getTotalElements())
	        .thisPage(page.getPageable().getPageNumber())
	        .lastPage(page.getTotalPages() != 0 ? page.getTotalPages() - 1 : 0)
	        .build();
	}
}
