package tech.hongjian.ticket.coupon.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by xiahongjian on 2021/6/5.
 */
public enum CouponType implements IEnum<Integer> {
    CAN_REPEAT(0, "可叠加使用"),
    ONLY_ONE(1, "只可使用一张");

    private Integer value;
    private String description;

    CouponType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    @JsonValue
    @Override
    public Integer getValue() {

        return value;
    }

    public String getDescription() {
        return description;
    }


}
