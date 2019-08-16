package com.maioshaproject.validator;

import java.util.Map;

public class ValidationResult {

    //检验结果是否有错
    private boolean hasErrors;

    //存放错误信息的map
    private Map<String, String>errorMsgMap;

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }
}
