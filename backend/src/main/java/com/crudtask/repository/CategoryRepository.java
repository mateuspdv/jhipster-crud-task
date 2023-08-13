package com.crudtask.repository;

import com.crudtask.domain.Category;
import com.crudtask.service.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    @Query("SELECT NEW com.crudtask.service.dto.CategoryDTO(C.id," +
        "   C.name, " +
        "   C.creationDate, " +
        "   C.lastUpdateDate) " +
        " FROM " +
        "   Category C " +
        "   INNER JOIN User U ON (C.user.id = U.id) " +
        " WHERE " +
        "   U.login = :login ")
    Page<CategoryDTO> getCategoriesByLogin(Pageable pageable, @Param("login") String login);

}
