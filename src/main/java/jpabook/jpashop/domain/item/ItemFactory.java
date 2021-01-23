package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.item.dto.ItemRequestDto;
import jpabook.jpashop.domain.item.entity.Book;

public class ItemFactory {
    public static Book of(ItemRequestDto.BookRequest request){
        return Book.builder()
                .name(request.getName())
                .author(request.getAuthor())
                .stockQuantity(request.getStockQuantity())
                .price(request.getPrice())
                .isbn(request.getIsbn())
                .build();
    }
}
