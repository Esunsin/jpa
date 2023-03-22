package jpabook1.jpashop1.service;

import jakarta.persistence.EntityManager;
import jpabook1.jpashop1.domain.Address;
import jpabook1.jpashop1.domain.Member;
import jpabook1.jpashop1.domain.Order;
import jpabook1.jpashop1.domain.OrderStatus;
import jpabook1.jpashop1.domain.item.Book;
import jpabook1.jpashop1.domain.item.Item;
import jpabook1.jpashop1.exception.NotEnoughStockException;
import jpabook1.jpashop1.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문 () throws Exception{
        //give
        Member member = createMember();

        Book book = createBook("jpa 시골", 10000, 10);

        int orderCount = 3;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.ORDER,getOrder.getOrderStatus());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(10000 * orderCount, getOrder.getTotalPrice());
        assertEquals(7, book.getStockQuantity());

    }

    @Test
    public void 상품주문_재고초과 () throws Exception{
        //give
        Member member = createMember();
        Item book = createBook("jpa 시골", 10000, 10);
        int orderCount = 11;
        //when,then
        assertThrows(NotEnoughStockException.class, ()->
        orderService.order(member.getId(), book.getId(), orderCount));


    }
    @Test
    public void 주문취소 () throws Exception{
        //give
        Member member = createMember();
        Book item = createBook("jpa1", 10000, 10);
        int orderCount = 2;

        Long orderCancel = orderService.order(member.getId(), item.getId(), orderCount);
        assertEquals(8,item.getStockQuantity());
        //when
        orderService.cancelOrder(orderCancel);

        //then
        Order findOrder = orderRepository.findOne(orderCancel);
        assertEquals(10, item.getStockQuantity());
        assertEquals(OrderStatus.CANCEL,findOrder.getOrderStatus());


    }
    @Test
    public void 상품주문_재고수량초과 () throws Exception{
        //give

        //when

        //then

    }
    private Book createBook(String name,int price,int quantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("user1");
        member.setAddress(new Address("se", "wewe", "123-123"));
        em.persist(member);
        return member;
    }
}