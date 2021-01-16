package jpabook.jpashop.domain.item;


import jpabook.jpashop.domain.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//TODO::handlebar 사용하여 뷰 만들어야 함..
@Slf4j
@Controller("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
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
