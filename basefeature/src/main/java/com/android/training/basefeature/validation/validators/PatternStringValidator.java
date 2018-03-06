package com.android.training.basefeature.validation.validators;


import com.android.training.basefeature.validation.BaseValidator;
import com.android.training.basefeature.validation.TargetValueCreator;
import com.android.training.basefeature.validation.ValidateListener;
import com.android.training.basefeature.validation.ValidateUtil;

/**
 * Created by violet on 16/3/1.
 */
public class PatternStringValidator extends BaseValidator<String> {
    private String pattern;

    public PatternStringValidator(TargetValueCreator<String> targetValueCreator) {
        super(targetValueCreator);
    }

    public PatternStringValidator(ValidateListener validateListener) {
        super(validateListener);
    }

    public PatternStringValidator(TargetValueCreator<String> targetValueCreator, ValidateListener validateListener) {
        super(targetValueCreator, validateListener);
    }

    @Override
    public boolean validation() {
        return ValidateUtil.isPattern(getTargetValue(), getPattern());
    }

    public String getPattern() {
        return pattern;
    }

    public PatternStringValidator setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }
}
