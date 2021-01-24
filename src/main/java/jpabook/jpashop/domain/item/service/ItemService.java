package jpabook.jpashop.domain.item.service;

import jpabook.jpashop.domain.item.dto.ItemDto;
import jpabook.jpashop.domain.item.entity.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public void update(Item item, ItemDto.ItemUpdateRequest request){
        item.update(request);
        save(item);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow();
    }
}
