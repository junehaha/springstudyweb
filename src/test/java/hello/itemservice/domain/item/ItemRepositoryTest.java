package hello.itemservice.domain.item;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }


    @Test
    void save(){
        //given
        Item item = new Item("itemA",10000,10);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(saveItem.getId());
        assertThat(findItem).isEqualTo(saveItem);

    }

    @Test
    void findAll(){
        //given
        Item item1 = new Item("item1",10000,10);
        Item item2 = new Item("item2",20000,20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> result = itemRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);
    }

    @Test
    void update(){
        //given
        Item item1 = new Item("item1",10000,10);
        Item savedItem = itemRepository.save(item1);
        //when
        Item itemParam = new Item("item2", 20000, 20);
        itemRepository.update(savedItem.getId(),itemParam);
        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem.getItemName()).isEqualTo(itemParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(itemParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(itemParam.getQuantity());
    }
}
