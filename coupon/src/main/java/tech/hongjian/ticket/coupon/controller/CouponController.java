package tech.hongjian.ticket.coupon.controller;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.hongjian.ticket.common.utils.PageUtils;
import tech.hongjian.ticket.common.utils.R;
import tech.hongjian.ticket.coupon.entity.CouponEntity;
import tech.hongjian.ticket.coupon.service.CouponService;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author xiahongjian
 * @email hongjian.xia@qq.com
 * @date 2021-06-05 21:34:46
 */
@RestController
@RequestMapping("coupon/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;


    @GetMapping("")
    public R memberCoupons(@RequestParam("memberId") Integer userId) {

        CouponEntity entity = new CouponEntity();
        entity.setName("测试优惠券");

        return R.ok().put("data", Arrays.asList(entity));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		CouponEntity coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CouponEntity coupon){
		couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CouponEntity coupon){
		couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
