package com.android.training.basefeature.validation;

import com.android.training.basefeature.log.LogManager;

import java.util.LinkedList;
import java.util.List;

/**
 * 通用基础Validator类，所有的验证器实现类都应该继承自该类。
 * Created by violet on 16/2/24.
 */
public abstract class BaseValidator<T> implements Validator<T>{
    public String TAG = getClass().getSimpleName();

    /**多数接口中方法都是下面的成员变量抽取而来**/
    protected String validatorIdentify = getClass().getSimpleName();
    protected TargetValueCreator<T> targetValueCreator;

    protected List<Validator<T>> beforeValidators = new LinkedList<>();
    protected ValidateListener validateListener;
    protected boolean ignoreOnFailed = false;
    protected boolean ignoreOnSuccess = false;
    protected boolean stopOnValidateFailed = false;

    public BaseValidator(TargetValueCreator<T> targetValueCreator){
        this.targetValueCreator = targetValueCreator;
    }

    public BaseValidator(ValidateListener validateListener){
        this.validateListener = validateListener;
    }

    public BaseValidator(TargetValueCreator<T> targetValueCreator,ValidateListener validateListener){
        this.targetValueCreator = targetValueCreator;
        this.validateListener = validateListener;
    }

    public List<Validator<T>> getBeforeValidators() {
        return beforeValidators;
    }

    public Validator setBeforeValidators(List<Validator<T>> beforeValidators) {
        if(beforeValidators != null){
            this.beforeValidators = beforeValidators;
        }else{
            if(this.beforeValidators != null){
                this.beforeValidators.clear();
            }
        }
        return this;
    }

    public Validator addBeforeValidator(Validator<T> beforeValidator) {
        LogManager.getInstance().d(TAG, "addBeforeValidator");
        if(beforeValidator != null){
            if(this.beforeValidators != null){
                this.beforeValidators.add(beforeValidator);
            }else{
                this.beforeValidators = new LinkedList<>();
                this.beforeValidators.add(beforeValidator);
            }
        }
        return this;
    }

    public boolean beforeValidate(){
        boolean beforeValidateResult = true;
        LogManager.getInstance().d(TAG,"beforeValidate start");
        if(this.beforeValidators != null){
            for(Validator validator : beforeValidators){
                //保持isStopOnValidateFailed和ValidateManager中传入的值一致。
                validator.setStopOnValidateFailed(isStopOnValidateFailed());
                boolean validate = validator.validate();
                //如果beforeValidate验证失败，并且设置成失败就终止的话，直接返回。
                if(validate){
                    //validator.onSuccess();
                }else{
                    //只要发生过一次验证失败，不管是否停止执行，整个验证逻辑都应该返回验证失败。
                    beforeValidateResult = validate;
                    //validator.onFailed();
                    if(validator.isStopOnValidateFailed()){
                        break;
                    }
                }
            }
        }
        LogManager.getInstance().d(TAG,"beforeValidate beforeValidateResult = "+ beforeValidateResult);
        return beforeValidateResult;
    }

    @Override
    public TargetValueCreator<T> getTargetValueCreator() {
        return targetValueCreator;
    }

    @Override
    public Validator setTargetValueCreator(TargetValueCreator<T> targetValueCreator) {
        this.targetValueCreator = targetValueCreator;
        return this;
    }

    @Override
    public T getTargetValue() {
        if(targetValueCreator != null){
            T targetValue = targetValueCreator.createTargetValue();
            LogManager.getInstance().d(TAG,"getTargetValue is "+ (targetValue == null ? " null" : targetValue.toString()));
            return targetValue;
        }
        LogManager.getInstance().e(TAG, "getTargetValue is null");
        return null;
    }

    @Override
    public void beforeAdvice() {
        LogManager.getInstance().d(TAG,"beforeAdvice");
    }

    @Override
    public void afterAdvice() {
        LogManager.getInstance().d(TAG,"afterAdvice");
    }

    /**
     * 必须是final方法,该方法中执行了入下方法：<br>
     * {@link #beforeAdvice()}      AOP切面before方法<br>
     * {@link #beforeValidate()}    执行所有的AtomicBeforeValidators的validate()方法<br>
     * {@link #validation()}        执行验证逻辑<br>
     * {@link #afterAdvice()}       AOP切面after方法<br>
     * @return
     */
    @Override
    public final boolean validate() {
        LogManager.getInstance().d(TAG,"validate");
        beforeAdvice();
        boolean beforeValidateSuccess = beforeValidate();
        if(beforeValidateSuccess){
            LogManager.getInstance().d(TAG,"beforeValidateSuccess = true , continueValidate()");
            return continueValidate();
        }else{
            LogManager.getInstance().d(TAG,"beforeValidateSuccess = true , isStopOnValidateFailed = "+ isStopOnValidateFailed());
            if(isStopOnValidateFailed()){
                return beforeValidateSuccess;
            }else{
                return continueValidate();
            }
        }
    }

    /**
     * 判断是否继续执行下面的验证逻辑
     * @return
     */
    private boolean continueValidate(){
        LogManager.getInstance().d(TAG,"continueValidate");
        boolean validationSuccess = validation();
        if(validationSuccess){
            afterAdvice();
            onSuccess();
            return validationSuccess;
        }else{
            LogManager.getInstance().d(TAG,"continueValidate isStopOnValidateFailed() = " + isStopOnValidateFailed());
            afterAdvice();
            onFailed();
            return validationSuccess;
        }
    }

    @Override
    public void onSuccess() {
        LogManager.getInstance().e(TAG," onSuccess " + this.getClass().getSimpleName());
        if(isIgnoreOnSuccess()){
            LogManager.getInstance().d(TAG," ignoreOnSuccess = true ");
        }else{
            LogManager.getInstance().d(TAG," ignoreOnSuccess = false ");
            if(getValidateListener() != null){
                LogManager.getInstance().d(TAG," execute validateListener.onSuccess() ");
                getValidateListener().onSuccess();
            }else{
                LogManager.getInstance().d(TAG, " getValidateListener() == null ");
            }
        }
    }

    @Override
    public void onFailed() {
        LogManager.getInstance().e(TAG," onFailed " + this.getClass().getSimpleName());
        if(isIgnoreOnFailed()){
            LogManager.getInstance().d(TAG," ignoreOnFailed = true ");
        }else{
            LogManager.getInstance().d(TAG," ignoreOnFailed = false ");
            if(getValidateListener() != null){
                LogManager.getInstance().d(TAG," execute validateListener.ignoreOnFailed() ");
                getValidateListener().onFailed();
            }else{
                LogManager.getInstance().d(TAG," getValidateListener() == null ");
            }
        }
    }

    public ValidateListener getValidateListener() {
        LogManager.getInstance().d(TAG," getValidateListener ");
        return validateListener;
    }

    public Validator setValidateListener(ValidateListener validateListener) {
        LogManager.getInstance().d(TAG," setValidateListener ");
        this.validateListener = validateListener;
        return this;
    }

    @Override
    public boolean isIgnoreOnSuccess() {
        LogManager.getInstance().d(TAG," isIgnoreOnSuccess ");
        return ignoreOnSuccess;
    }

    @Override
    public Validator setIgnoreOnSuccess(boolean ignoreOnSuccess) {
        LogManager.getInstance().d(TAG," setIgnoreOnSuccess ");
        this.ignoreOnSuccess = ignoreOnSuccess;
        return this;
    }

    @Override
    public boolean isIgnoreOnFailed() {
        LogManager.getInstance().d(TAG," isIgnoreOnFailed ");
        return ignoreOnFailed;
    }

    @Override
    public Validator setIgnoreOnFailed(boolean ignoreOnFailed) {
        LogManager.getInstance().d(TAG," setIgnoreOnFailed ");
        this.ignoreOnFailed = ignoreOnFailed;
        return this;
    }

    @Override
    public String getValidatorIdentify() {
        LogManager.getInstance().d(TAG," getValidatorIdentify ");
        return validatorIdentify;
    }

    @Override
    public Validator setValidatorIdentify(String validatorIdentify) {
        LogManager.getInstance().d(TAG," setValidatorIdentify ");
        this.validatorIdentify = validatorIdentify;
        return this;
    }

    @Override
    public boolean isStopOnValidateFailed() {
        LogManager.getInstance().d(TAG," isStopOnValidateFailed = " + stopOnValidateFailed);
        return stopOnValidateFailed;
    }

    @Override
    public Validator setStopOnValidateFailed(boolean stopOnValidateFailed) {
        LogManager.getInstance().d(TAG," setStopOnValidateFailed = "+stopOnValidateFailed);
        this.stopOnValidateFailed = stopOnValidateFailed;
        return this;
    }
}
