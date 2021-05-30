package mk.jmikan.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
