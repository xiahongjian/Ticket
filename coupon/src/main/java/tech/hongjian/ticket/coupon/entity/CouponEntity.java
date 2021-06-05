package tech.hongjian.ticket.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import tech.hongjian.ticket.coupon.entity.enums.CouponType;

/**
 * 
 * 
 * @author xiahongjian
 * @email hongjian.xia@qq.com
 * @date 2021-06-05 21:34:46
 */
@Data
@TableName("cpn_coupon")
public class CouponEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Date createdAt;
	/**
	 * 
	 */
	private Date updatedAt;
	/**
	 * 优惠券名称
	 */
	private String name;
	/**
	 * 优惠券描述
	 */
	private String description;
	/**
	 * 优惠券类型
	 */
	private CouponType type;
	/**
	 * 发行数量
	 */
	private String publishQuantity;
	/**
	 * 已领取数量
	 */
	private String revisedQuantity;
	/**
	 * 已使用数量
	 */
	private String usedQuantity;
	/**
	 * 没人领取数量限制
	 */
	private String perLimit;
	/**
	 * 优惠券生效开始时间
	 */
	private Date enableStartTime;
	/**
	 * 优惠券生效结束时间
	 */
	private Date enableEndTime;
	/**
	 * 是否发布
	 */
	private Boolean published;
	/**
	 * 优惠券金额（单位：分）
	 */
	private String amount;
	/**
	 * 使用门槛（单位：分）
	 */
	private String minPoint;
	/**
	 * 优惠券领取开始时间
	 */
	private Date startTime;
	/**
	 * 优惠券领取结束时间
	 */
	private Date endTime;

}
