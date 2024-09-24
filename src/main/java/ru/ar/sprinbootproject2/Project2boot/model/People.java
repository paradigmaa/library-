package ru.ar.sprinbootproject2.Project2boot.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;


    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public People(){

    }
    @OneToMany(mappedBy = "owner")
    List<Books> booksPeople;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Books> getBooksPeople() {
        return booksPeople;
    }

    public void setBooksPeople(List<Books> booksPeople) {
        this.booksPeople = booksPeople;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", booksPeople=" + booksPeople +
                '}';
    }

}
