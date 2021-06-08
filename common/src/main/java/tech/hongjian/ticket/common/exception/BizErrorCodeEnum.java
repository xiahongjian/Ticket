package tech.hongjian.ticket.common.exception;

import lombok.Getter;

/**
 * Created by xiahongjian on 2021/6/7.
 */
@Getter
public enum BizErrorCodeEnum {
    // 10xxx 系统错误
    UNKNOWN_ERROR(10_000, "未知错误"),
    SERVER_ERROR(10_001, "系统错误"),
    VALIDATION_ERROR(10_002, "验证错误"),
    // 各个模块错误

    ;

    private int code;
    private String message;

    BizErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
