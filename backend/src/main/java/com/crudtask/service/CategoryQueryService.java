package com.crudtask.service;

import com.crudtask.domain.Category;
import com.crudtask.domain.Category_;
import com.crudtask.repository.CategoryRepository;
import com.crudtask.service.criteria.CategoryCriteria;
import com.crudtask.service.dto.CategoryDTO;
import com.crudtask.service.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryQueryService extends QueryService<Category> {

    private final Logger log = LoggerFactory.getLogger(CategoryQueryService.class);

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryQueryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findByCriteria(CategoryCriteria criteria) {
        log.debug("Find by criteria : {}", criteria);
        final Specification<Category> specification = createSpecification(criteria);
        return categoryMapper.toDto(categoryRepository.findAll(specification));
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findByCriteria(CategoryCriteria criteria, Pageable page) {
        log.debug("Find by criteria : {}, page: {}", criteria, page);
        final Specification<Category> specification = createSpecification(criteria);
        return categoryRepository.findAll(specification, page).map(categoryMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(CategoryCriteria criteria) {
        log.debug("Count by criteria : {}", criteria);
        final Specification<Category> specification = createSpecification(criteria);
        return categoryRepository.count(specification);
    }

    protected Specification<Category> createSpecification(CategoryCriteria criteria) {
        Specification<Category> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Category_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Category_.name));
            }
        }
        return specification;
    }

}
