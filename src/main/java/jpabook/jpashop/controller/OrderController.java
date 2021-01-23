package jpabook.jpashop.controller;

import jpabook.jpashop.common.OrderStatus;
import jpabook.jpashop.domain.item.entity.Item;
import jpabook.jpashop.domain.item.service.ItemService;
import jpabook.jpashop.domain.member.entity.Member;
import jpabook.jpashop.domain.member.service.MemberService;
import jpabook.jpashop.domain.order.dto.OrderSearchDto;
import jpabook.jpashop.domain.order.entity.Order;
import jpabook.jpashop.domain.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    //상품주문 화면출력
    @GetMapping("/order")
    public String createForm(Model model){

        List<Member> members = memberService.findMembers();
        List<Item>items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    //상품 주문
    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count,
                        Model model) throws Exception {
        model.addAttribute("orders",
                orderService.order(memberId, itemId, count));
        return "redirect:/orders";
    }

    //주문 내역 리스트
    //TODO::세션 추가해야 함. + URI 정리
    @GetMapping("/orders")
    public String getOrdersForm(){
        return "order/orderList";
    }

    //주문 내역 검색
    //TODO:: commonResponse 생성해야 함.
    @PostMapping("/orders")
    @ResponseBody
    public ResponseEntity<List<Order>> getOrders(@RequestParam("memberName") String memberName,
                                                 @RequestParam("orderStatus") String orderStatus) {
        List<Member> members = memberService.findByName(memberName);
        List<Order> orders = orderService.findOrders(new OrderSearchDto(OrderStatus.valueOf(orderStatus), members));
        return ResponseEntity.ok(orders);
    }
}
