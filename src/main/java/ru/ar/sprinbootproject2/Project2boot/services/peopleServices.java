package ru.ar.sprinbootproject2.Project2boot.services;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ar.sprinbootproject2.Project2boot.model.Books;
import ru.ar.sprinbootproject2.Project2boot.model.People;
import ru.ar.sprinbootproject2.Project2boot.repositories.bookRepositories;
import ru.ar.sprinbootproject2.Project2boot.repositories.peopleRepositories;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class peopleServices {
    private final peopleRepositories peopleRepositories;

    @Autowired
    public peopleServices(peopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }

    public List<People> findByAll(){
        return peopleRepositories.findAll();
    }
    public People findById(int id){
        return peopleRepositories.findById(id).orElse(null);
    }
    @Transactional
    public People save(People people){
        return peopleRepositories.save(people);
    }
    @Transactional
    public void delete(int id){
        peopleRepositories.deleteById(id);
    }
    @Transactional
    public void update(int id, People people){
        people.setId(id);
        peopleRepositories.save(people);
    }

    public List<Books> getpeoplebooks(People people){
       Optional<People> peopleOpt = peopleRepositories.findById(people.getId());
        if(peopleOpt.isPresent()){
            Hibernate.initialize(peopleOpt.get().getBooksPeople());
            for(Books books : peopleOpt.get().getBooksPeople()) {
                if (books.getTime_created() != null) {
                    long diff = new Date().getTime() - books.getTime_created().getTime();
                    if (diff > 6000) {
                        books.setResult(true);
                    }
                }
            }
            return peopleOpt.get().getBooksPeople();
        }else{
            return Collections.emptyList();
        }
    }
}
