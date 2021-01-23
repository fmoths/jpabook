package jpabook.jpashop.domain.order.service;

import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.service.ItemService;
import jpabook.jpashop.domain.member.entity.Member;
import jpabook.jpashop.domain.member.service.MemberService;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderItem;
import jpabook.jpashop.domain.order.dto.OrderSearchDto;
import jpabook.jpashop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO:: 멤버 명세로 검색 기능 구현.
@Service
@Transactional
public class OrderService {

    @Autowired
    MemberService memberService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemService itemService;

    //주문
    public Order order(Long memberId, Long itemId, int count) throws Exception {

        //엔티티 조회
        Member member = memberService.findById(memberId);
        Item item = itemService.findById(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order;
    }

    //주문 취소
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow();
        //주문 취소
        order.cancel();
    }

    //주문 검색
    public List<Order> findOrders(OrderSearchDto dto) {
        return orderRepository.findByOrderStatusAndMembers(dto.getOrderStatus().name(), dto.getMembers());
    }
}
