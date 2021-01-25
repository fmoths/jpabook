package jpabook.jpashop.domain.order;

import jpabook.jpashop.domain.order.dto.OrderDto;
import jpabook.jpashop.domain.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderFacade {

    public OrderDto.OrderSearchResponseDto toOrderSearchResponseDto (Order order){
        return OrderDto.OrderSearchResponseDto.builder()
                .memberName(order.getMember().getName())
                .itemName(order.getOrderItems().get(0).getItem().getName())
                .price(order.getTotalPrice())
                .date(order.getOrderDate())
                .status(order.getStatus())
                .build();
    }
}
