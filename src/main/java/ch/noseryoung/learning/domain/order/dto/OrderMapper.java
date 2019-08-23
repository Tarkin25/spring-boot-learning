package ch.noseryoung.learning.domain.order.dto;

import ch.noseryoung.learning.domain.order.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setName(order.getName());

        return dto;
    }

}
