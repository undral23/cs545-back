package edu.miu.cs545.store.controller;

import edu.miu.cs545.store.domain.OrderStatus;
import edu.miu.cs545.store.dto.OrderDTO;
import edu.miu.cs545.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDTO orderDTO) {
        Long newId = orderService.placeOrder(orderDTO);
        return ResponseEntity.created(null).body(newId);
    }

    @GetMapping("/orders")
    public ResponseEntity<Collection<OrderDTO>> getOrders() {
        var orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        var orderDTO = orderService.getOrder(id);
        return ResponseEntity.ok(orderDTO);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> changeStatus(@PathVariable Long id, @RequestParam("status") OrderStatus newStatus) {
        var orderDTO = orderService.setOrderStatus(id, newStatus);
        return ResponseEntity.ok(orderDTO);
    }
}
