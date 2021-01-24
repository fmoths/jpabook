package jpabook.jpashop.controller;


import jpabook.jpashop.domain.item.ItemFactory;
import jpabook.jpashop.domain.item.dto.ItemDto;
import jpabook.jpashop.domain.item.entity.Item;
import jpabook.jpashop.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO::handlebar 사용하여 뷰 만들어야 함..
//TODO::request 받는 부분 전부 DTO로 변환해야 함.
@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "itemList";
    }

    //상품 수정 폼
    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "updateItemForm";
    }

    //상품 수정
    //TODO:: 상품 업데이트 기능 엔티티에 추가해야 함.
    //TODO:: pagination 기능 추가 및 인덱스 추가.
    @PatchMapping("/items/{itemId}")
    public String updateBook (
            @PathVariable("itemId") Long itemId,
            @ModelAttribute ItemDto.ItemUpdateRequest request
    ){
        Item book = itemService.findById(itemId);
        itemService.update(book, request);
        return "redirect:/items";
    }

    @PostMapping("/item")
    public String createBook(@ModelAttribute ItemDto.ItemCreateRequest request) {
        Item item = ItemFactory.of(request);
        itemService.save(item);
        return "redirect:/items";
    }

    @GetMapping("/form")
    public String createItemForm() {
        return "items/createItemForm";
    }
}
