package jpabook.jpashop.domain.item.service;

import jpabook.JpaBookApplication;
import jpabook.jpashop.common.exception.NotEnoughStockException;
import jpabook.jpashop.domain.item.entity.Book;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JpaBookApplication.class)
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("호출하면 해당상품이 등록된다.")
    public void saveItem() {
        Book book = new Book();

        itemService.save(book);

        Long savedId = book.getId();
        assertEquals(book, itemService.findById(savedId));
    }

    @Test
    @DisplayName("재고 보다 많은 수량을 주문하면 예외를 발생시킨다.")
    public void orderItemMoreThenStock() {
        Book book = new Book();
        book.setStockQuantity(10);
        assertThrows(NotEnoughStockException.class, () -> book.removeStock(100));
    }

    @Test
    @DisplayName("일정 수량을 환불하면 환불된 수량만큼 재고가 증가한다.")
    public void refundStock() {
        Book book = new Book();
        book.setStockQuantity(10);
        book.addStock(10);
        assertEquals(20, book.getStockQuantity());
    }
}