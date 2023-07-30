package br.com.lucolimac.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @EmbeddedId
    private CategoryId id;

    public Category() {
    }

    public Category(String name) {
        this.id = new CategoryId(name, "");
    }

    public String getName() {
        return this.id.getName();
    }
}
