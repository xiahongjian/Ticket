package tech.hongjian.ticket.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.hongjian.ticket.common.exception.BizErrorCodeEnum;
import tech.hongjian.ticket.common.exception.BizException;
import tech.hongjian.ticket.common.utils.R;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xiahongjian on 2021/6/7.
 */
@Slf4j
@ConditionalOnWebApplication
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleValidateException(MethodArgumentNotValidException e) {
        log.info("参数验证失败，信息[{}]。", e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> msgs = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return R.error(BizErrorCodeEnum.VALIDATION_ERROR.getCode(), BizErrorCodeEnum.VALIDATION_ERROR.getMessage()).data(msgs);
    }

    @ExceptionHandler(BizException.class)
    public R handleBizException(BizException e) {
        log.info("业务异常，信息[{}]。", e.getMessage(), e);
        return R.error(e.getCode(), e.getMessage());
    }
}
