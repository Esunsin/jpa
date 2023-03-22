package jpabook1.jpashop1.repository;

import jakarta.persistence.EntityManager;
import jpabook1.jpashop1.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    //상품 저장
    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        }
        else{
            em.merge(item);
        }
    }

    //상품 단일 조회
    public Item findOne(Long itemId) {
        return em.find(Item.class, itemId);
    }
    //상품 전체 조회
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
