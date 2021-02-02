package jpabook.jpashop.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jpabook.jpashop.common.LocalDateAdapter;
import jpabook.jpashop.common.OrderStatus;
import jpabook.jpashop.domain.item.entity.Item;
import jpabook.jpashop.domain.item.service.ItemService;
import jpabook.jpashop.domain.member.entity.Member;
import jpabook.jpashop.domain.member.service.MemberService;
import jpabook.jpashop.domain.order.OrderFacade;
import jpabook.jpashop.domain.order.dto.OrderDto;
import jpabook.jpashop.domain.order.dto.OrderSearchDto;
import jpabook.jpashop.domain.order.entity.Order;
import jpabook.jpashop.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderFacade orderFacade;

    //상품주문 화면출력
    @GetMapping("/api/order")
    public String createForm(Model model){

        List<Member> members = memberService.findMembers();
        List<Item>items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    //상품 주문
    @PostMapping("/api/order")
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
    @GetMapping("/api/orders")
    public String getOrdersForm(){
        return "order/orderList";
    }

    //주문 내역 검색
    //TODO:: commonResponse 생성해야 함.
    @PostMapping("/api/orders")
    public @ResponseBody String getOrders(
            @RequestParam(value = "memberName") String memberName,
            @RequestParam(value = "orderStatus") String orderStatus
    ) {
        OrderStatus status = OrderStatus.valueOf(orderStatus);
        List<Member> members = memberService.findByName(memberName);
        List<OrderSearchDto> dtos = new ArrayList<>();
        members.forEach(
                member -> dtos.add(new OrderSearchDto(status, member.getName()))
        );

        List<Order> orders = new ArrayList<>();

        dtos.forEach( dto ->
                orders.addAll(orderService.findOrders(dto))
        );

        List<OrderDto.OrderSearchResponseDto> orderDtos
                = orders.stream().map(orderFacade::toOrderSearchResponseDto).collect(Collectors.toList());

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String response = gson.toJson(orderDtos);
        log.info(response);
        return response;
    }
}
