package jpabook.jpashop.domain.order;

import jpabook.jpashop.common.OrderStatus;
import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //주문 회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송정보

    private LocalDate orderDate; //주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus staus; //주문 상태

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    /*
     * 연관관계 메소드
     */
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
}
