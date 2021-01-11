package jpabook.jpashop.domain.order.service;

import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.dto.OrderSearchDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderService {

    @PersistenceContext
    EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    //TODO:: QueryDsl 사용해야 함.
    public List<Order> findAll(OrderSearchDto dto) {
        em.createQuery("""
                    select * from Order o where o.status = :status
                """)
                .setParameter("status", dto.getOrderStatus());
    }
}