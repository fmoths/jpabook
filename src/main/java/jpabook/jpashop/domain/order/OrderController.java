package jpabook.jpashop.domain.order;

import jpabook.jpashop.common.exception.NotEnoughStockException;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.service.ItemService;
import jpabook.jpashop.domain.member.entity.Member;
import jpabook.jpashop.domain.member.service.MemberService;
import jpabook.jpashop.domain.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getOrdersForm(Model model){
//        List<Order> orders = orderService.findOrders();
//        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    //TODO:: commonResponse 생성해야 함.
    @GetMapping("/orders")
    @ResponseBody
    public String getOrders(@RequestParam("memberName") String memberName,
                            @RequestParam("itemStatus") String itemStatus) {
        log.info("{}, {}", memberName, itemStatus);
//        List<Order> orders = orderService.findOrders();
//        model.addAttribute("orders", orders);
        return "success";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) throws NotEnoughStockException {
        orderService.order(memberId, itemId, count);
        return "redirect:/order/list";
    }
}
