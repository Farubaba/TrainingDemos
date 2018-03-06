package com.android.training.basefeature.validation.validators;


import com.android.training.basefeature.validation.BaseValidator;
import com.android.training.basefeature.validation.TargetValueCreator;
import com.android.training.basefeature.validation.ValidateListener;
import com.android.training.basefeature.validation.ValidateUtil;

/**
 * Created by violet on 16/3/1.
 */
public class MinLengthValidator extends BaseValidator<String> {
    private int minLength = 8;

    public MinLengthValidator(TargetValueCreator<String> targetValueCreator) {
        super(targetValueCreator);
    }

    public MinLengthValidator(ValidateListener validateListener) {
        super(validateListener);
    }

    public MinLengthValidator(TargetValueCreator<String> targetValueCreator, ValidateListener validateListener) {
        super(targetValueCreator, validateListener);
    }

    @Override
    public boolean validation() {
        return ValidateUtil.isValidMinLength(getTargetValue(), getMinLength());
    }

    public int getMinLength() {
        return minLength;
    }

    public MinLengthValidator setMinLength(int minLength) {
        this.minLength = minLength;
        return this;
    }
}
