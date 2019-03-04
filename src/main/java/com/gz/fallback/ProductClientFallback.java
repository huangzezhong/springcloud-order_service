package com.gz.fallback;

import com.gz.util.ProductClient;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String findById(int id) {
        System.out.println("失败咯");
        return "失败咯";
    }
}
