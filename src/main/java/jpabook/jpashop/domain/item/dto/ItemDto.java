package jpabook.jpashop.domain.item.dto;

import lombok.*;


public class ItemDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class ItemCreateRequest {
        private String name;
        private int price;
        private int stockQuantity;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class ItemUpdateRequest {
        private String name;
        private int price;
        private int stockQuantity;
    }
}
