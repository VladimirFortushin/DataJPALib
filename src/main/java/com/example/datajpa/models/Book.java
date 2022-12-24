package com.example.datajpa.models;


import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")

    private String name;
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "owner=" + owner +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
