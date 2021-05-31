package mk.jmikan.library.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String isbn;

    private String title;

    private Integer yearPublished;

    @JsonIgnoreProperties({"birthYear", "books"})
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
