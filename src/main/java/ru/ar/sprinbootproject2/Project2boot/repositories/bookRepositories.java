package ru.ar.sprinbootproject2.Project2boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ar.sprinbootproject2.Project2boot.model.Books;

import java.util.List;


@Repository
public interface bookRepositories extends JpaRepository<Books, Integer> {
    List<Books> findByTitleStartingWith(String title);
}
