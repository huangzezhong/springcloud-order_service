package com.gz.service;

import com.gz.model.ProductOrder;

public interface OrderService {

    public ProductOrder saveOrder(int userId,int productId);

}
