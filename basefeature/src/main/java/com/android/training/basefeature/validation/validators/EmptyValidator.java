package com.android.training.basefeature.validation.validators;


import com.android.training.basefeature.validation.BaseValidator;
import com.android.training.basefeature.validation.TargetValueCreator;
import com.android.training.basefeature.validation.ValidateListener;
import com.android.training.basefeature.validation.ValidateUtil;

/**
 * Created by violet on 16/3/1.
 */
public class EmptyValidator extends BaseValidator<String> {
    public EmptyValidator(TargetValueCreator<String> targetValueCreator) {
        super(targetValueCreator);
    }

    public EmptyValidator(ValidateListener validateListener) {
        super(validateListener);
    }

    public EmptyValidator(TargetValueCreator<String> targetValueCreator, ValidateListener validateListener) {
        super(targetValueCreator, validateListener);
    }

    @Override
    public boolean validation() {
        return ValidateUtil.isNotEmpty(getTargetValue());
    }
}
