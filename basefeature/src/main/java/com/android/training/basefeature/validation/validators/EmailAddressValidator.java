package com.android.training.basefeature.validation.validators;

import com.android.training.basefeature.validation.BaseValidator;
import com.android.training.basefeature.validation.TargetValueCreator;
import com.android.training.basefeature.validation.ValidateListener;
import com.android.training.basefeature.validation.ValidateUtil;

/**
 * 校验邮箱格式是否合法
 * Created by violet on 16/2/24.
 */
public class EmailAddressValidator extends BaseValidator<String> {

    public EmailAddressValidator(TargetValueCreator<String> targetValueCreator) {
        super(targetValueCreator);
    }

    public EmailAddressValidator(ValidateListener validateListener) {
        super(validateListener);
    }

    public EmailAddressValidator(TargetValueCreator<String> targetValueCreator, ValidateListener validateListener) {
        super(targetValueCreator, validateListener);
    }

    @Override
    public boolean validation() {
        return ValidateUtil.isEmail(getTargetValue());
    }
}
