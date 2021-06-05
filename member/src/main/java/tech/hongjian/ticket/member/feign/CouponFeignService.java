package tech.hongjian.ticket.member.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.hongjian.ticket.common.utils.R;

/**
 * Created by xiahongjian on 2021/6/5.
 */
@Service
@FeignClient("coupon")
public interface CouponFeignService {

    @GetMapping("/coupon/coupon")
    R listMemberCoupons(@RequestParam Integer memberId);
}
