package ru.ar.sprinbootproject2.Project2boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ar.sprinbootproject2.Project2boot.model.Books;
import ru.ar.sprinbootproject2.Project2boot.model.People;
import ru.ar.sprinbootproject2.Project2boot.services.peopleServices;
import ru.ar.sprinbootproject2.Project2boot.services.bookServices;
import ru.ar.sprinbootproject2.Project2boot.model.Books;
import ru.ar.sprinbootproject2.Project2boot.model.People;

@Controller
@RequestMapping("/books")
public class BookControler {
    private final  peopleServices peopleServices;
    private final bookServices bookServices;

    @Autowired
    public BookControler(peopleServices peopleServices, bookServices bookServices) {
        this.peopleServices = peopleServices;
        this.bookServices = bookServices;
    }

    @GetMapping()
    public String books(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "books_per_page", defaultValue = "1") Integer books_per_page,
                        @RequestParam(name = "sort_by_year", defaultValue = "false") String sort_by_year) {

        if(sort_by_year.equals("true")) {
            model.addAttribute("sort_by_year", bookServices.getAllSortBooks(sort_by_year));
        }else if (page.equals(0) && books_per_page.equals(1)) {
            model.addAttribute("allbooks", bookServices.getAllBooks());
        }else{
            model.addAttribute("page", bookServices.getPageBooks(page, books_per_page));
        }
        return "books/allbooks";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(name = "search",required = false) String search) {
        model.addAttribute("searchbooks", bookServices.getBookByTittleStartWith(search));
        return "books/search";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("newbooksget") Books books) {
        return "books/newbooks";
    }
    @PostMapping()
    public String newBooks(@ModelAttribute("newbookspost") Books books){
        bookServices.saveBook(books);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String books(@PathVariable int id, Model model, @ModelAttribute("peoplemodel") People people) {
        Books books = bookServices.getBookById(id);
        model.addAttribute("bookid", books);
        model.addAttribute("bookspeople", bookServices.getBooksPeople(books));
        model.addAttribute("peoplefreekey", bookServices.getPeoplefreekey(books));
        return "books/finedtoid";
    }

    @DeleteMapping("/{id}")
    public String deleteBooks(@PathVariable int id) {
     bookServices.deleteBookById(id);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable int id, Model model){
        model.addAttribute("bookidedit", bookServices.getBookById(id));
        return "books/editbooks";
    }
    @PatchMapping("/{id}")
    public String editBookpost(@ModelAttribute("bookideditpost") Books books, @PathVariable int id) {
        bookServices.updateBook(id, books);
        return "redirect:/books/" + id;
    }
    @PostMapping("/{id}")
    public String deletekey(@PathVariable int id) {
        Books books = bookServices.getBookById(id);
       bookServices.deletedBooksPeople(books);
        return "redirect:/books/" + id;
    }
      @PostMapping("/{id}/searchkey")
      public String setkey(@PathVariable int id, @ModelAttribute People people) {
        bookServices.addkeybooks(id, people);
        return "redirect:/books/" + id;
    }
}
