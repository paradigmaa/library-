package ru.ar.sprinbootproject2.Project2boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ar.sprinbootproject2.Project2boot.model.People;
import ru.ar.sprinbootproject2.Project2boot.model.Books;
import ru.ar.sprinbootproject2.Project2boot.model.People;
import ru.ar.sprinbootproject2.Project2boot.services.peopleServices;
import ru.ar.sprinbootproject2.Project2boot.services.bookServices;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final peopleServices peopleServices;
    private final bookServices bookServices;


    @Autowired
    public PeopleController(peopleServices peopleServices, bookServices bookServices) {
        this.peopleServices = peopleServices;
        this.bookServices = bookServices;
    }

    @GetMapping()
    public String getallpeople (Model model) {
        model.addAttribute("allpeopleatribut", peopleServices.findByAll());
        return "people/allpeople";
    }

    @GetMapping("/new")
    public String createnewpeople(@ModelAttribute("newpeopleatributget") People people) {
        return "people/newpeople";
    }

    @PostMapping()
    public String createPeople(@ModelAttribute("newpeopleatributpost") People people) {
        peopleServices.save(people);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String editPeople(@PathVariable int id, Model model){
        People people = peopleServices.findById(id);
        model.addAttribute("finedidpeople", people);
        model.addAttribute("peoplebooks", peopleServices.getpeoplebooks(people));
        return "people/finedid";
    }

    @DeleteMapping("/{id}")
    public String deletePeople(@PathVariable int id) {
        peopleServices.delete(id);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editPeople(Model model, @PathVariable int id) {
        model.addAttribute("editpeopleatribut", peopleServices.findById(id));
        return "people/editpeople";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute People people) {
        peopleServices.update(id, people);
        return "redirect:/people/" + id;
    }
}
