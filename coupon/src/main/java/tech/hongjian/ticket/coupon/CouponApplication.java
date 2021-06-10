package tech.hongjian.ticket.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"tech.hongjian.ticket"})
public class CouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class, args);
    }

}
