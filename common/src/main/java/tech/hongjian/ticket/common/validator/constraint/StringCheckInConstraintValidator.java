package tech.hongjian.ticket.common.validator.constraint;

import tech.hongjian.ticket.common.validator.CheckIn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiahongjian on 2021/6/7.
 */
public class StringCheckInConstraintValidator implements ConstraintValidator<CheckIn, String> {
    private Set<String> values = new HashSet<>();

    @Override
    public void initialize(CheckIn constraintAnnotation) {
        String[] vals = constraintAnnotation.values();
        if (vals != null && vals.length > 0) {
            for (String val : vals) {
                values.add(val);
            }
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        return values.contains(s);
    }
}
