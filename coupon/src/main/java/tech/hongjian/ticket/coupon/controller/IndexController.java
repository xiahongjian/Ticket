package tech.hongjian.ticket.coupon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.hongjian.ticket.common.utils.R;

/**
 * Created by xiahongjian on 2021/6/10.
 */
@RestController
@RequestMapping("/test")
public class IndexController {

    @GetMapping("/index")
    public R test() {
        return R.ok();
    }
}
