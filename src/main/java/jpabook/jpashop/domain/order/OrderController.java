package jpabook.jpashop.domain.order;

import jpabook.jpashop.common.OrderStatus;
import jpabook.jpashop.common.exception.NotEnoughStockException;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.service.ItemService;
import jpabook.jpashop.domain.member.entity.Member;
import jpabook.jpashop.domain.member.service.MemberService;
import jpabook.jpashop.domain.order.dto.OrderSearchDto;
import jpabook.jpashop.domain.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/order")
    public String createForm(Model model){

        List<Member> members = memberService.findMembers();
        List<Item>items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    //TODO::세션 추가해야 함. + URI 정리
    @GetMapping("/order/list")
    public String getOrdersForm(){
        return "order/orderList";
    }

    //TODO:: commonResponse 생성해야 함.
    @GetMapping("/orders")
    @ResponseBody
    public ResponseEntity<List<Order>> getOrders(@RequestParam("memberName") String memberName,
                            @RequestParam("orderStatus") String orderStatus) {
        List<Member> members = memberService.findByName(memberName);
        List<Order> orders = orderService.findOrders(new OrderSearchDto(OrderStatus.valueOf(orderStatus), members));
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count,
                        Model model) throws Exception {
        model.addAttribute("orders",
                orderService.order(memberId, itemId, count));
        return "redirect:/order/list";
    }
}
