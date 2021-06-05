package tech.hongjian.ticket.member.controller;

import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;
import org.checkerframework.checker.guieffect.qual.AlwaysSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import tech.hongjian.ticket.common.utils.PageUtils;
import tech.hongjian.ticket.common.utils.R;
import tech.hongjian.ticket.member.MemberApplication;
import tech.hongjian.ticket.member.entity.UserEntity;
import tech.hongjian.ticket.member.feign.CouponFeignService;
import tech.hongjian.ticket.member.service.UserService;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author xiahongjian
 * @email hongjian.xia@qq.com
 * @date 2021-06-05 19:13:46
 */
@RefreshScope
@RestController
@RequestMapping("member/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CouponFeignService  couponFeignService;


    @Value("${test.username}")
    private String username;
    @Value("${test.age}")
    private Integer age;

    @GetMapping("config")
    public R getConfig() {
        return R.ok().put("username", username).put("age", age);
    }


    @GetMapping("coupons")
    public R test() {
        UserEntity entity = new UserEntity();
        entity.setUsername("zhangsan");

        R r = couponFeignService.listMemberCoupons(1);
        return R.ok().put("user", entity).put("coupons", r.get("data"));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
