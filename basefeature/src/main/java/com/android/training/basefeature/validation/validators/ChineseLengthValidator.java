package com.android.training.basefeature.validation.validators;


import com.android.training.basefeature.validation.BaseValidator;
import com.android.training.basefeature.validation.TargetValueCreator;
import com.android.training.basefeature.validation.ValidateListener;
import com.android.training.basefeature.validation.ValidateUtil;

/**
 * 在此描述该类用途
 *
 * @author chenpu
 * @version V1.0
 * @date 2016-04-27 15:49
 * @tips 在此提出您对代码的建议
 */
public class ChineseLengthValidator extends BaseValidator<String> {
    public ChineseLengthValidator(TargetValueCreator<String> targetValueCreator) {
        super(targetValueCreator);
    }

    public ChineseLengthValidator(ValidateListener validateListener) {
        super(validateListener);
    }

    public ChineseLengthValidator(TargetValueCreator<String> targetValueCreator, ValidateListener validateListener) {
        super(targetValueCreator, validateListener);
    }

    @Override
    public boolean validation() {
        return ValidateUtil.minAddressChineseLength(getTargetValue());
    }
}
