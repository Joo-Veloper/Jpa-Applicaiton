package com.jpabook.jpashop.api;

import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* x Two one ( One Two One , Many To One) 단계 최적화
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/v1/simple_orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); //LAZY 강제 초기화
            order.getDelivery().getAddress(); //LAZY 강제 초기화
        }
        return all;
    }
}
