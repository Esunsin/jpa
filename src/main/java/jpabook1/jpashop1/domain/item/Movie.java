package jpabook1.jpashop1.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("M")
public class Movie extends Item{
    private String director;
    private String etc;
}
