package org.example.bookms.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Embeddable
public class AuthorDTO {
    private Long authorId;
    private String name;
}
