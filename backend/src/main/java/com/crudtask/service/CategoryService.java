package com.crudtask.service;

import com.crudtask.domain.Category;
import com.crudtask.domain.User;
import com.crudtask.repository.CategoryRepository;
import com.crudtask.service.dto.CategoryDTO;
import com.crudtask.service.mapper.CategoryMapper;
import com.crudtask.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final UserService userService;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.userService = userService;
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        log.debug("Request to save Category : {}", categoryDTO);

        Category category = categoryMapper.toEntity(categoryDTO);
        User user = userService.getUserWithAuthorities()
            .orElseThrow(() -> new BadRequestAlertException("User not found", "Category", "usernotfound"));

        category.setUser(user);
        category.setCreationDate(LocalDateTime.now());
        category.setLastUpdateDate(LocalDateTime.now());
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public CategoryDTO update(CategoryDTO categoryDTO) {
        log.debug("Request to update Category : {}", categoryDTO);

        Category category = categoryRepository.findById(categoryDTO.getId())
            .orElseThrow(() -> new BadRequestAlertException("Category not found", "Category", "categorynotfound"));

        category.setLastUpdateDate(LocalDateTime.now());
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public Optional<CategoryDTO> partialUpdate(CategoryDTO categoryDTO) {
        log.debug("Request to partially update Category : {}", categoryDTO);

        return categoryRepository
            .findById(categoryDTO.getId())
            .map(existingCategory -> {
                categoryMapper.partialUpdate(existingCategory, categoryDTO);

                return existingCategory;
            })
            .map(categoryRepository::save)
            .map(categoryMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Categories");
        return categoryRepository.findAll(pageable).map(categoryMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<CategoryDTO> findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        return categoryRepository.findById(id).map(categoryMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.deleteById(id);
    }

}
