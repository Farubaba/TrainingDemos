package com.android.training.basefeature.validation.validators;


import com.android.training.basefeature.validation.BaseValidator;
import com.android.training.basefeature.validation.TargetValueCreator;
import com.android.training.basefeature.validation.ValidateListener;
import com.android.training.basefeature.validation.ValidateUtil;

/**
 * Created by violet on 16/3/1.
 */
public class MaxLengthValidator extends BaseValidator<String> {
    private int maxLength = 20;

    public MaxLengthValidator(TargetValueCreator<String> targetValueCreator) {
        super(targetValueCreator);
    }

    public MaxLengthValidator(ValidateListener validateListener) {
        super(validateListener);
    }

    public MaxLengthValidator(TargetValueCreator<String> targetValueCreator, ValidateListener validateListener) {
        super(targetValueCreator, validateListener);
    }

    @Override
    public boolean validation() {
        return ValidateUtil.isValidMaxLength(getTargetValue(), getMaxLength());
    }

    public int getMaxLength() {
        return maxLength;
    }

    public MaxLengthValidator setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        return this;
    }
}
