package jpabook1.jpashop1.repository;

import jpabook1.jpashop1.domain.OrderStatus;
import jpabook1.jpashop1.service.OrderService;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
