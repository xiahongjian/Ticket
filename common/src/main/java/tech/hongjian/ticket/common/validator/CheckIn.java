package tech.hongjian.ticket.common.validator;

import tech.hongjian.ticket.common.validator.constraint.IntegerCheckInConstraintValidator;
import tech.hongjian.ticket.common.validator.constraint.StringCheckInConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by xiahongjian on 2021/6/7.
 */
@Documented
@Constraint(validatedBy = {IntegerCheckInConstraintValidator.class, StringCheckInConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface CheckIn {

    String[] values() default {};

    String message() default "{tech.hongjian.ticket.common.validator.CheckIn.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
