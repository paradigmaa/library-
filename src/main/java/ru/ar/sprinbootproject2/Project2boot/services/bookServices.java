package ru.ar.sprinbootproject2.Project2boot.services;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ar.sprinbootproject2.Project2boot.model.Books;
import ru.ar.sprinbootproject2.Project2boot.model.People;
import ru.ar.sprinbootproject2.Project2boot.repositories.bookRepositories;
import ru.ar.sprinbootproject2.Project2boot.repositories.peopleRepositories;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class bookServices {
    private final bookRepositories bookRepositories;
    private final peopleRepositories peopleRepositories;;

    @Autowired
    public bookServices(bookRepositories bookRepositories, peopleRepositories peopleRepositories) {
        this.bookRepositories = bookRepositories;
        this.peopleRepositories = peopleRepositories;
    }



    public List<Books> getAllBooks() {
        return bookRepositories.findAll();
    }

    public List<Books> getAllSortBooks(String sort_by_year){
        return bookRepositories.findAll(Sort.by("year"));
    }

    public Page<Books> getPageBooks(Integer page, Integer books_per_page) {
       Pageable pageable = PageRequest.of(page, books_per_page, Sort.by("year"));
       return bookRepositories.findAll(pageable);
    }
    public Books getBookById(int id) {
        return bookRepositories.findById(id).orElse(null);
    }
    @Transactional
    public void deleteBookById(int id) {
        bookRepositories.deleteById(id);
    }
    @Transactional
    public Books saveBook(Books book) {
        return bookRepositories.save(book);
    }
    @Transactional
    public Books updateBook(int id, Books book) {
        book.setId(id);
        return bookRepositories.save(book);
    }
    public People getBooksPeople(Books books) {
        Optional<Books> booksOpt = bookRepositories.findById(books.getId());
        if (booksOpt.isPresent()) {
           Hibernate.initialize(booksOpt.get().getOwner());
           return booksOpt.get().getOwner();
        }else{
           return null;
        }
    }

    @Transactional
    public void deletedBooksPeople(Books books) {
        books.setOwner(null);
        books.setTime_created(null);
        bookRepositories.save(books);
    }

    public List<People> getPeoplefreekey(Books books) {
        List<People>allPeople = peopleRepositories.findAll();
        List<People>allFreeKeyPeople = new ArrayList<>();

        for(People people : allPeople){
            if(!people.getBooksPeople().contains(books)){
                allFreeKeyPeople.add(people);
            }
        }
        return allFreeKeyPeople;
    }

    @Transactional
    public void addkeybooks(int id, People people) {
        Books books = getBookById(id);
        books.setOwner(people);
        books.setTime_created(new Date());
        bookRepositories.save(books);
    }

    public List<Books> getBookByTittleStartWith(String title) {
        if (!(title == null || title.isEmpty())) {
            return bookRepositories.findByTitleStartingWith(title);

        } else {
            return new ArrayList<>();
        }
    }
}
