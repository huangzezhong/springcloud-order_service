package com.gz.service.impl;

import com.gz.model.ProductOrder;
import com.gz.service.OrderService;
import com.gz.util.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @Override
    public ProductOrder saveOrder(int userId, int productId) {
        if (userId == 1) {
            int i = 1/0;
        }
        String result = productClient.findById(productId);
        log.info("feign --->{}",result);

        Object object = restTemplate.getForObject("http://product-service/api/vi/product/findById?id="+productId,Object.class);
        log.info("ribbon --->{}",object);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setUserId(userId);
        productOrder.setCreateTime(new Date());
        productOrder.setTradeNo(UUID.randomUUID().toString());
        return productOrder;
    }
}
