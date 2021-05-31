package mk.jmikan.library.service.impl;

import mk.jmikan.library.model.Author;
import mk.jmikan.library.model.Book;
import mk.jmikan.library.model.EBook;
import mk.jmikan.library.model.PrintCopyBook;
import mk.jmikan.library.repository.AuthorRepository;
import mk.jmikan.library.repository.BookRepository;
import mk.jmikan.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    AuthorRepository authorRepository;

    BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(bookRepository, authorRepository);
    }

    @Test
    void getAll() {
        EBook eBook = new EBook();
        PrintCopyBook printCopyBook = new PrintCopyBook();
        List<Book> books = new ArrayList<>();
        books.add(eBook);
        books.add(printCopyBook);

        when(bookRepository.findAll()).thenReturn(books);
        Set<Book> bookSet = bookService.getAll();

        assertEquals(bookSet.size(), 2);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        PrintCopyBook book = new PrintCopyBook();
        book.setId(1L);
        Optional<Book> optionalBook = Optional.of(book);

        when(bookRepository.findById(anyLong())).thenReturn(optionalBook);

        Book returnedBook = bookService.getById(1L);

        assertNotNull(returnedBook);
        assertEquals(returnedBook.getId(), 1L);
        verify(bookRepository).findById(anyLong());
        verify(bookRepository, never()).findAll();
    }

    @Test
    void save() {
        EBook eBook = new EBook();
        eBook.setId(1L);
        Author author = new Author();
        author.setId(2L);
        eBook.setAuthor(author);

        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenReturn(eBook);

        Book savedBook = bookService.save(eBook);

        assertEquals(savedBook.getId(), eBook.getId());
        assertEquals(savedBook.getAuthor().getId(), author.getId());
        verify(authorRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(1)).save(any(Book.class));

    }

    @Test
    void updateEBook() {
        EBook eBook = new EBook();
        eBook.setId(1L);
        Author a = new Author();
        a.setId(2L);
        eBook.setAuthor(a);

        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(a));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(eBook));
        when(bookRepository.save(any(Book.class))).thenReturn(eBook);

        Book savedBook = bookService.updateEBook(1L, eBook);

        assertEquals(savedBook.getId(), eBook.getId());
        assertEquals(savedBook.getAuthor().getId(), a.getId());
        verify(authorRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void updatePrintBook() {
        PrintCopyBook printCopyBook = new PrintCopyBook();
        printCopyBook.setId(1L);
        Author a = new Author();
        a.setId(2L);
        printCopyBook.setAuthor(a);

        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(a));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(printCopyBook));
        when(bookRepository.save(any(Book.class))).thenReturn(printCopyBook);

        Book savedBook = bookService.updatePrintBook(1L, printCopyBook);

        assertEquals(savedBook.getId(), printCopyBook.getId());
        assertEquals(savedBook.getAuthor().getId(), a.getId());
        verify(authorRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void delete() {
        Long id = 2L;
        bookService.delete(id);
        verify(bookRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getAllChronological() {
        EBook eBook = new EBook();
        eBook.setYearPublished(1900);
        PrintCopyBook printCopyBook = new PrintCopyBook();
        printCopyBook.setYearPublished(1990);
        List<Book> books = new ArrayList<>();
        books.add(eBook);
        books.add(printCopyBook);

        when(bookRepository.findByOrderByYearPublished()).thenReturn(books);
        List<Book> bookList = bookService.getAllChronological();

        assertEquals(bookList.size(), 2);
        verify(bookRepository, times(1)).findByOrderByYearPublished();
    }

    @Test
    void getAllByAuthorLNameStartsWith() {
        EBook eBook = new EBook();
        PrintCopyBook printCopyBook = new PrintCopyBook();
        List<Book> books = new ArrayList<>();
        books.add(eBook);
        books.add(printCopyBook);

        when(bookRepository.findAllByAuthor_LastNameStartsWithIgnoreCase(anyString())).thenReturn(books);
        Set<Book> bookSet = bookService.getAllByAuthorLNameStartsWith("s");

        assertEquals(bookSet.size(), 2);
        verify(bookRepository, times(1)).findAllByAuthor_LastNameStartsWithIgnoreCase(anyString());
    }

    @Test
    void getOldest() {
        EBook eBook = new EBook();
        eBook.setYearPublished(1900);

        when(bookRepository.findTopByOrderByYearPublished()).thenReturn(eBook);
        Book returnedBook = bookService.getOldest();

        assertEquals(returnedBook, eBook);
        verify(bookRepository, times(1)).findTopByOrderByYearPublished();
    }

    @Test
    void getNewest() {
        EBook eBook = new EBook();
        eBook.setYearPublished(1990);

        when(bookRepository.findTopByOrderByYearPublishedDesc()).thenReturn(eBook);
        Book returnedBook = bookService.getNewest();

        assertEquals(returnedBook, eBook);
        verify(bookRepository, times(1)).findTopByOrderByYearPublishedDesc();
    }
}