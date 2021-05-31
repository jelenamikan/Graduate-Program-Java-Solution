package mk.jmikan.library.controller;

import mk.jmikan.library.model.Book;
import mk.jmikan.library.model.EBook;
import mk.jmikan.library.model.PrintCopyBook;
import mk.jmikan.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // get all books and their info - zadacha 3g
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Book> getAll(){
        return bookService.getAll();
    }

    // get a book by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getById(@PathVariable Long id){
        return bookService.getById(id);
    }

    // create a new print book - zadacha 3a
    @PostMapping("/printbook")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody PrintCopyBook book){
        return bookService.save(book);
    }

    // create a new ebook - zadacha 3a
    @PostMapping("/ebook")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody EBook book){
        return bookService.save(book);
    }

    // update a print book - zadacha 3b
    @PutMapping("/printbook/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updatePrintBook(@PathVariable Long id, @RequestBody PrintCopyBook book){
        return bookService.updatePrintBook(id, book);
    }

    // update an ebook - zadacha 3b
    @PutMapping("/ebook/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateEBook(@PathVariable Long id, @RequestBody EBook book){
        return bookService.updateEBook(id, book);
    }

    // delete a book by id - zadacha 3v
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long id){
        bookService.delete(id);
    }

    // find all books in chronological order by year published - zadacha 2a
    @GetMapping("/chronological")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllChronological(){
        return bookService.getAllChronological();
    }

    // find all books by authors who's last name starts with string s - zadacha 2b
    @GetMapping("/author/{s}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Book> getAllByAuthorLNameStartsWith(@PathVariable String s){
        return bookService.getAllByAuthorLNameStartsWith(s);
    }

    // find the oldest book - zadacha 2d
    @GetMapping("/oldest")
    @ResponseStatus(HttpStatus.OK)
    public Book getOldest(){
        return bookService.getOldest();
    }

    // find the newest book - zadacha 2d
    @GetMapping("/newest")
    @ResponseStatus(HttpStatus.OK)
    public Book getNewest(){
        return bookService.getNewest();
    }

}
