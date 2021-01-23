package jpabook.jpashop.domain.order.service;

import jpabook.JpaBookApplication;
import jpabook.jpashop.common.Address;
import jpabook.jpashop.common.OrderStatus;
import jpabook.jpashop.common.exception.NotEnoughStockException;
import jpabook.jpashop.domain.item.entity.Book;
import jpabook.jpashop.domain.item.entity.Item;
import jpabook.jpashop.domain.member.entity.Member;
import jpabook.jpashop.domain.order.entity.Order;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpaBookApplication.class)
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("상품주문을 한 후에는 정상적인 상태로 주문이 되어야 한다.")
    public void orderItem() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA test", 10000, 10);
        int orderCount = 2;
        //when
        Order expected = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order actual = orderRepository.findById(expected.getId())
                .get();

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, actual.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, actual.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * 2, actual.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야한다.", 8, item.getStockQuantity());
    }

    @Test
    @DisplayName("상품주문을 재고보다 많이하면 Exception을 발생시킨다.")
    public void orderStockQuantity() throws NotEnoughStockException {
        //given, when
        Member member = createMember();
        Item item = createBook("JPA test", 10000, 10);
        int orderCount = 11;

        //then
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), item.getId(), orderCount));
    }

    @Test
    @DisplayName("주문을 취소 한다면 주문이 정상적인 상태로 취소되어야 한다.")
    public void orderCancel() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA test", 10000, 10); //이름, 가격, 재고
        int orderCount = 2;

        Order expected = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(expected.getId());

        //then
        Order order = orderRepository.findById(expected.getId())
                .get();

        assertEquals("주문 취소시 상태는 CANCEL이다.", OrderStatus.CANCEL, expected.getStatus());
        assertEquals("주문이 취소 된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("Seoul", "Hanam Dea Ro", "123-123"));
        em.persist(member);

        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = Book.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();

        book.addStock(stockQuantity);
        em.persist(book);

        return book;
    }
}