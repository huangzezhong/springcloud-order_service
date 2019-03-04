package com.gz.controller;

import com.gz.model.ProductOrder;
import com.gz.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/api/vi/order/save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public ProductOrder saveOrder(@RequestParam int userId, @RequestParam int productId, HttpServletRequest request){
        String auth = request.getHeader("auth");
        String cookies = request.getHeader("cookies");
        System.out.println("auth="+auth);
        System.out.println("cookies="+cookies);
        return orderService.saveOrder(userId,productId);
    }

    /**
     *下单失败，发送短信或者邮件提醒
     * @param userId
     * @param productId
     * @return
     */
    public ProductOrder saveOrderFail(int userId,int productId, HttpServletRequest request) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setUserId(-9999);
        productOrder.setCreateTime(new Date());
        productOrder.setTradeNo("9999");

        String key = "findByIdFail-key";
        Object sendValue = redisTemplate.opsForValue().get(key);
        if (null == sendValue) {
            System.out.println("紧急短信，用户下单失败！");
            //调用外围接口，发送短信 TODO

            redisTemplate.opsForValue().set(key,"fail", 20,TimeUnit.SECONDS);
        } else {
            System.out.println("已经发送过短信，20秒内不能重复发送！");
        }

        return productOrder;
    }

}
