package br.com.lucolimac.model;

import javax.persistence.Embeddable;

@Embeddable
public class Person {
    private String name;
    private String cpf;

    public Person() {
    }

    public Person(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
