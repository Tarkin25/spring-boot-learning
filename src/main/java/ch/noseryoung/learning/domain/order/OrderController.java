package ch.noseryoung.learning.domain.order;

import ch.noseryoung.learning.domain.order.dto.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service;
    private OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService service, OrderMapper orderMapper) {
        this.service = service;
        this.orderMapper = orderMapper;
    }
}
