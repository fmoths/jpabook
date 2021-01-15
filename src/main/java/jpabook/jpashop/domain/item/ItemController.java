package jpabook.jpashop.domain.item;


import jpabook.jpashop.domain.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO::handlebar 사용하여 뷰 만들어야 함..
@RestController("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

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
