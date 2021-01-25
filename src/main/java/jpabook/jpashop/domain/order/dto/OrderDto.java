package jpabook.jpashop.domain.order.dto;

import jpabook.jpashop.common.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class OrderDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class OrderSearchDto {
        OrderStatus orderStatus;
        String memberName;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class OrderSearchResponseDto {
        String memberName;
        String itemName;
        Integer price;
        Integer stockQuantity;
        OrderStatus status;
        LocalDate date;
    }
}
