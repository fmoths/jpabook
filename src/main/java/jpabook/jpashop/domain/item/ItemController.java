package jpabook.jpashop.domain.item;


import jpabook.jpashop.domain.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO::handlebar 사용하여 뷰 만들어야 함..
@Slf4j
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

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
    @PatchMapping("/items")
    public String updateItem(@ModelAttribute("item") Book item){
        log.info("{} - {} - {}",item.getName(), item.getPrice(), item.getStockQuantity());
        itemService.save(item);
        return "redirect:/items";
    }

    @PostMapping("/new")
    public String createItem(Item item) {
        itemService.save(item);
        return "redirect:/items";
    }

    @GetMapping("/form")
    public String createItemForm() {
        return "items/createItemForm";
    }
}
