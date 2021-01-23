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
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
    private String etc;

    @Builder
    public Album(String name, int price, int stockQuantity, String artist, String etc){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.artist = artist;
        this.etc = etc;
    }
}
