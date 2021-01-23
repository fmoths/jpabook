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
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;

    @Builder
    public Book(String name, int price, int stockQuantity, String author, String isbn){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }
}
