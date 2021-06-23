package tech.hongjian.ticket.coupon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.hongjian.ticket.common.utils.PageUtils;
import tech.hongjian.ticket.common.utils.R;
import tech.hongjian.ticket.common.validator.CheckIn;
import tech.hongjian.ticket.coupon.entity.CouponEntity;
import tech.hongjian.ticket.coupon.service.CouponService;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.Map;


/**
 * @author xiahongjian
 * @email hongjian.xia@qq.com
 * @date 2021-06-05 21:34:46
 */
@Slf4j
//@RestController
//@RequestMapping("coupon/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;


    //    @ReadLock("my-lock")
    @GetMapping("read")
    public R read() {
        log.info("读..." + Thread.currentThread().getId());
        return R.ok("读结束");
    }

    //    @WriteLock("my-lock")
    @GetMapping("write")
    public R write() throws InterruptedException {
        log.info("写开始..." + Thread.currentThread().getId());
        Thread.sleep(20000);
        log.info("写结束..." + Thread.currentThread().getId());
        return R.ok("写结束");
    }

    //    @ReentrantLock({"lock1", "lock2"})
    @GetMapping("lock12")
    public R lock12() throws InterruptedException {
        log.info("获取锁1,2...{}", Thread.currentThread().getId());
        Thread.sleep(20000);
        log.info("释放锁1,2...{}", Thread.currentThread().getId());
        return R.ok("释放锁");
    }

    //    @ReentrantLock("lock1")
    @GetMapping("lock1")
    public R lock1() {
        log.info("获取锁1...");

        return R.ok("获取锁1");
    }

    //    @ReentrantLock("lock2")
    @GetMapping("lock2")
    public R lock2() {
        log.info("获取锁2...");

        return R.ok("获取锁2");
    }


    @GetMapping("")
    public R memberCoupons(@RequestParam("memberId") Integer userId) {

        CouponEntity entity = new CouponEntity();
        entity.setName("测试优惠券");

        return R.ok().put("data", Arrays.asList(entity));
    }

    static class Form {
        @NotBlank
        public String notBlank;

        @CheckIn(values = {"1", "2"})
        public Integer value1;

        @CheckIn(values = {"a", "b"})
        public String value2;

        @Max(20)
        @Min(10)
        public Integer intVal;

        @Email
        public String email;

        @Pattern(regexp = "[a-z]*")
        public String pattern;

        @Override
        public String toString() {
            return "Form{" +
                    "notBlank='" + notBlank + '\'' +
                    ", value1=" + value1 +
                    ", value2='" + value2 + '\'' +
                    ", intVal=" + intVal +
                    ", email='" + email + '\'' +
                    ", pattern='" + pattern + '\'' +
                    '}';
        }
    }

    @PostMapping("/valid")
    public R testValid(@Valid @RequestBody Form form) {
        log.info(form.toString());
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        CouponEntity coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CouponEntity coupon) {
        couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CouponEntity coupon) {
        couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
