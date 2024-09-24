package ru.ar.sprinbootproject2.Project2boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ar.sprinbootproject2.Project2boot.model.People;


@Repository
public interface peopleRepositories extends JpaRepository<People, Integer> {

}
