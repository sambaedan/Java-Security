package com.rest.rest.service.impl;
import com.rest.rest.model.order.Order;
import com.rest.rest.model.product.Product;
import com.rest.rest.model.user.Users;
import com.rest.rest.pojo.OrderPojo;
import com.rest.rest.repository.OrderRepo;
import com.rest.rest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderrepo;

    private final UserServiceImpl userService;

    private final ProductServiceImpl productService;
    public Order checkIt(Long id) throws Exception {
        Optional<Order> optionalOrder = orderrepo.findById(id);
        if(optionalOrder.isPresent())
            return optionalOrder.get();
        throw new Exception("Order Not Found");
    }
    public OrderPojo createOrder(OrderPojo orderPojo) throws Exception {
        Order order;
        if(orderPojo.getId() != null) {
            order = checkIt(orderPojo.getId());
        } else
            order = new Order();
        Users user;
        user = userService.checkIt(orderPojo.getUserId());
        Product product = productService.checkIt(orderPojo.getProductId());
        order.setUser(user);
        order.setProduct(product);
        Order savedOrder = orderrepo.save(order);
        orderPojo.setId(savedOrder.getId());
        return orderPojo;

    }
    public List<Map<String, Object>> getAllOrder() {
        return orderrepo.getAllOrder();
    }
    public Map<String, Object> getOrderById(long id) {
        return orderrepo.getOrderById(id);
    }

    public void deleteAllOrder() {
        orderrepo.deleteAll();
    }

    public void deleteOrderById(long id) {
        orderrepo.deleteById(id);
    }
}
