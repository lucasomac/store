package br.com.lucolimac.model;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Person person;

    public Client() {
    }

    public Client(String name, String cpf) {
        this.person = new Person(name, cpf);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return person.getName();
    }

    public String getCpf() {
        return person.getCpf();
    }
    public Person getPerson() {
        return person;
    }
}
