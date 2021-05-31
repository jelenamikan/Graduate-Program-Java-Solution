package mk.jmikan.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.jmikan.library.model.*;
import mk.jmikan.library.repository.AuthorRepository;
import mk.jmikan.library.service.BookService;
import mk.jmikan.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest {

    @Mock
    BookService bookService;

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    BookController bookController;

    MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void getAll() throws Exception {
        EBook book1 = new EBook();
        book1.setId(1L);

        PrintCopyBook book2 = new PrintCopyBook();
        book2.setId(2L);

        Set<Book> books = new HashSet<>();
        books.add(book1);
        books.add(book2);

        when(bookService.getAll()).thenReturn(books);

        mockMvc.perform(get("/books")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    void getById() throws Exception {
        EBook book1 = new EBook();
        book1.setId(1L);

        when(bookService.getById(anyLong())).thenReturn(book1);

        mockMvc.perform(get("/books/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void createEBook() throws Exception {
        EBook book1 = new EBook();
        book1.setId(1L);

        when(bookService.save(ArgumentMatchers.any())).thenReturn(book1);

        String json = mapper.writeValueAsString(book1);
        mockMvc.perform(post("/books/ebook")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

    }

    @Test
    void createPrintBook() throws Exception {
        PrintCopyBook book1 = new PrintCopyBook();
        book1.setId(1L);

        when(bookService.save(any(Book.class))).thenReturn(book1);

        String json = mapper.writeValueAsString(book1);
        mockMvc.perform(post("/books/printbook")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void updatePrintBook() throws Exception {
        PrintCopyBook book1 = new PrintCopyBook();
        book1.setId(1L);

        when(bookService.updatePrintBook(anyLong(), any(PrintCopyBook.class))).thenReturn(book1);

        String json = mapper.writeValueAsString(book1);
        mockMvc.perform(put("/books/printbook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void updateEBook() throws Exception {
        EBook book1 = new EBook();
        book1.setId(1L);

        when(bookService.updateEBook(anyLong(), any(EBook.class))).thenReturn(book1);

        String json = mapper.writeValueAsString(book1);
        mockMvc.perform(put("/books/ebook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void deleteBook() throws Exception {
        EBook book1 = new EBook();
        book1.setId(1L);

        mockMvc.perform(delete("/books/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookService, times(1)).delete(anyLong());
    }

    @Test
    void getAllChronological() throws Exception {
        EBook book1 = new EBook();
        book1.setId(1L);

        PrintCopyBook book2 = new PrintCopyBook();
        book2.setId(2L);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        when(bookService.getAllChronological()).thenReturn(books);

        mockMvc.perform(get("/books/chronological")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    void getAllByAuthorLNameStartsWith() {
    }

    @Test
    void getOldest() throws Exception {
        EBook book1 = new EBook();
        book1.setId(1L);

        when(bookService.getOldest()).thenReturn(book1);

        mockMvc.perform(get("/books/oldest")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void getNewest() throws Exception {
        EBook book1 = new EBook();
        book1.setId(1L);

        when(bookService.getNewest()).thenReturn(book1);

        mockMvc.perform(get("/books/newest")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }
}