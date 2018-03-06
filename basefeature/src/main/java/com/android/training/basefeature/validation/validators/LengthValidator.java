package com.android.training.basefeature.validation.validators;


import com.android.training.basefeature.validation.BaseValidator;
import com.android.training.basefeature.validation.TargetValueCreator;
import com.android.training.basefeature.validation.ValidateListener;
import com.android.training.basefeature.validation.ValidateUtil;

/**
 * Created by violet on 16/3/1.
 */
public class LengthValidator extends BaseValidator<String> {
    private int length = 20;

    public LengthValidator(TargetValueCreator<String> targetValueCreator) {
        super(targetValueCreator);
    }

    public LengthValidator(ValidateListener validateListener) {
        super(validateListener);
    }

    public LengthValidator(TargetValueCreator<String> targetValueCreator, ValidateListener validateListener) {
        super(targetValueCreator, validateListener);
    }

    @Override
    public boolean validation() {
        return ValidateUtil.isRightLength(getTargetValue(),getLength());
    }

    public int getLength() {
        return length;
    }

    public LengthValidator setLength(int length) {
        this.length = length;
        return this;
    }
}
