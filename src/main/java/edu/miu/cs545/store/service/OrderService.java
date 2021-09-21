package edu.miu.cs545.store.service;

import edu.miu.cs545.store.domain.*;
import edu.miu.cs545.store.dto.OrderDTO;
import edu.miu.cs545.store.dto.OrderItemDTO;
import edu.miu.cs545.store.exception.OrderNotFoundException;
import edu.miu.cs545.store.exception.ProductNotFoundException;
import edu.miu.cs545.store.repository.OrderRepository;
import edu.miu.cs545.store.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    public long placeOrder(OrderDTO orderDTO) {
        Order order = new Order(
                modelMapper.map(orderDTO.getPersonalInfo(), PersonalInfo.class),
                modelMapper.map(orderDTO.getPaymentInfo(), PaymentInfo.class)
        );

        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProduct().getProductNumber())
                    .orElseThrow(() -> new ProductNotFoundException(itemDTO.getProduct().getProductNumber()));

            product.reduceNumberInStock(itemDTO.getQuantity());
            order.addItem(new OrderItem(product, itemDTO.getQuantity()));
            productRepository.save(product);
        }

        order.setId(System.currentTimeMillis()); // Not good idea. But I don't have much time to fix that
        orderRepository.save(order);
        return order.getId();
    }

    public Collection<OrderDTO> getOrders() {
        var orders = orderRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }

    public OrderDTO getOrder(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO setOrderStatus(Long id, OrderStatus status) {
        var order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        order.setStatus(status);
        orderRepository.save(order);
        return modelMapper.map(order, OrderDTO.class);
    }

}
