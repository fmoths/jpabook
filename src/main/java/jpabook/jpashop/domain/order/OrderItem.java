package jpabook.jpashop.domain.order;

import jpabook.jpashop.common.exception.NotEnoughStockException;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "orderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; //주문

    private int orderPrice; //주문 가격
    private int count; //주문 수량

    /*
     * 생성 메소드
     */
    public static OrderItem createOrderItem(Item item, int OrderPrice, int count) throws NotEnoughStockException {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(OrderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    public static OrderItem createOrderItems(Item item, int OrderPrice, int count) throws NotEnoughStockException {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(OrderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    /*
     * 비즈니스 로직
     */
    //주문 취소
    public void cancel() {
        getItem().addStock(count);
    }

    /*
     * 조회 로직
     */
    //주문 상품 전체 가격 조회
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
