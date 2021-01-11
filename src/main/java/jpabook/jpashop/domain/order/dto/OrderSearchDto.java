package jpabook.jpashop.domain.order.dto;

import jpabook.jpashop.common.OrderStatus;
import jpabook.jpashop.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearchDto {
    OrderStatus orderStatus;
    Member member;
}
