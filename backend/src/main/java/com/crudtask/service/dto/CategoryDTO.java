package com.crudtask.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class CategoryDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryDTO)) {
            return false;
        }

        CategoryDTO categoryDTO = (CategoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, categoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }


    @Override
    public String toString() {
        return "CategoryDTO{" + "id=" + getId() + ", name='" + getName() + "'" + "}";
    }

}
