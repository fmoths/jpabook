package jpabook.jpashop.domain.item.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String actor;

    @Builder
    public Movie(String name, int price, int stockQuantity, String director, String actor){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.director = director;
        this.actor = actor;
    }
}
