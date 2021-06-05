package tech.hongjian.ticket.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.hongjian.ticket.common.utils.PageUtils;
import tech.hongjian.ticket.coupon.entity.CouponEntity;

import java.util.Map;

/**
 * 
 *
 * @author xiahongjian
 * @email hongjian.xia@qq.com
 * @date 2021-06-05 21:34:46
 */
public interface CouponService extends IService<CouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

