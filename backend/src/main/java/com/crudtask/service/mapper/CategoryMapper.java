package com.crudtask.service.mapper;

import com.crudtask.domain.Category;
import com.crudtask.service.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {
}
