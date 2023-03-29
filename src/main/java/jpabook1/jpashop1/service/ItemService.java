package jpabook1.jpashop1.service;

import jpabook1.jpashop1.domain.item.Item;
import jpabook1.jpashop1.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    //상품 저장
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }
    //상품 전체 조회
    public List<Item> findItems(){
        return itemRepository.findAll();
    }
    //상품 단일 조회
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
    /**
     * 영속성 컨텍스트가 자동변경 (머지x)
     */
    public void updateItem(Long id,String name, int price,int stockQuantity){
        Item item = itemRepository.findOne(id);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }
}
