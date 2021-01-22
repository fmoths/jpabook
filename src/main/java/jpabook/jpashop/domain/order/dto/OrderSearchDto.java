package jpabook.jpashop.domain.order.dto;

import jpabook.jpashop.common.OrderStatus;
import jpabook.jpashop.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderSearchDto {
    OrderStatus orderStatus;
    List<Member> members;
}
