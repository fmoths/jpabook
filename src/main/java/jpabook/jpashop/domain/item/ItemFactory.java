package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.item.dto.ItemDto;
import jpabook.jpashop.domain.item.entity.Item;

public class ItemFactory {

    public static Item of(ItemDto.ItemCreateRequest request){
        return Item.builder()
                .name(request.getName())
                .stockQuantity(request.getStockQuantity())
                .price(request.getPrice())
                .build();
    }
}
