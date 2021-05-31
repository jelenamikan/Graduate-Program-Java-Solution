package mk.jmikan.library.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EBook extends Book {

    @Enumerated(EnumType.STRING)
    private EBookFormat format;

    private Integer mbSize;

}
