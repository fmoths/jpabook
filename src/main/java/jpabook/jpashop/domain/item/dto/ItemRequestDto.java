package jpabook.jpashop.domain.item.dto;

import lombok.*;


public class ItemRequestDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class BookRequest {
        private Long id;
        private String name;
        private int price;
        private int stockQuantity;
        private String author;
        private String isbn;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class AlbumRequest {
        private Long id;
        private String name;
        private int price;
        private int stockQuantity;
        private String artist;
        private String etc;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class MovieRequest {
        private Long id;
        private String name;
        private int price;
        private int stockQuantity;
        private String director;
        private String actor;
    }
}
