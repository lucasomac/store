package br.com.lucolimac.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "computing")
public class Computing extends Product {
    private String mark;
    private String model;

    public Computing(String mark, String model) {
        this.mark = mark;
        this.model = model;
    }

    public Computing() {
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
