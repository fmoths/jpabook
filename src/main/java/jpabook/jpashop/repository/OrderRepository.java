package jpabook.jpashop.repository;

import jpabook.jpashop.domain.member.entity.Member;
import jpabook.jpashop.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join o.member m where o.status = ?1 and m IN ?2")
    List<Order> findByOrderStatusAndMembers(String status, List<Member> members);
}
