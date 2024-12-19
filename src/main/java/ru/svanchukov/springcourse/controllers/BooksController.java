package ru.svanchukov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.svanchukov.springcourse.dao.BookDao;
import ru.svanchukov.springcourse.dao.PersonDAO;
import ru.svanchukov.springcourse.models.Book1;
import ru.svanchukov.springcourse.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDao bookDao;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDao bookDao, PersonDAO personDAO) {
        this.bookDao = bookDao;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Optional<Book1> bookOptional = bookDao.show(id);
        if (bookOptional.isPresent()) {
            Book1 book = bookOptional.get();
            model.addAttribute("book", book);
            model.addAttribute("people", personDAO.index());

            Optional<Person> bookOwner = bookDao.getBookOwner(id);
            bookOwner.ifPresent(owner -> model.addAttribute("owner", owner));

            return "books/show";
        } else {
            return "redirect:/books";
        }
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book1 book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book1 book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Optional<Book1> bookOptional = bookDao.show(id);
        if (bookOptional.isPresent()) {
            model.addAttribute("book", bookOptional.get());
            return "books/edit";
        } else {
            return "redirect:/books";
        }
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book1 book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDao.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDao.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookDao.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }
}
