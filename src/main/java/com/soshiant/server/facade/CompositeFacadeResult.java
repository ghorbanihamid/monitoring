package com.soshiant.server.facade;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class CompositeFacadeResult implements FacadeResult<Enum, EnumMap> {

    private int errorCode = 0;

    private int errorStep = 0;

    private String errorKey = "";

    private String sqlText = "";

    private String errorMessage = "";


    private Map<Enum, FacadeResult> enumMap;

    public CompositeFacadeResult(Facade facade) {
        this.enumMap = new EnumMap(facade.getKeysEnumClass());
    }

    public CompositeFacadeResult setData(Enum key, Object data) {
        FacadeResult facadeResult;
        if (!FacadeResult.class.isInstance(data)) {
            facadeResult = new SimpleFacadeResult(data).success();
        } else {
            facadeResult = FacadeResult.class.cast(data);
        }
        enumMap.put(key, facadeResult);
        return this;
    }

    public CompositeFacadeResult setData(Enum key, Object data, Exception exception) {
        FacadeResult facadeResult;
        if (!FacadeResult.class.isInstance(data)) {
            SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(data);
            simpleFacadeResult.setErrorMessage(exception.getMessage());
            simpleFacadeResult.fail();
            facadeResult = simpleFacadeResult;
        } else {
            facadeResult = FacadeResult.class.cast(data);
        }
        add(key, facadeResult);
        return this;
    }

    public int getErrorCode() {

        Collection<FacadeResult> facadeResultCollection = enumMap.values();
        for (FacadeResult facadeResult : facadeResultCollection) {
            if (facadeResult.getErrorCode() != 0) {
                return facadeResult.getErrorCode();
            }
        }
        return errorCode;
    }

    public int getErrorStep() {
        return errorStep;
    }

    public String getErrorKey() {

        return errorKey;
    }

    public String getSqlText() {
        return sqlText;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // @Override
    public EnumMap getData() {
        return EnumMap.class.cast(enumMap);
    }

    //@Override
    public Object getData(Enum key) {
        return enumMap.get(key).getData();
    }

    //@Override
    public int getErrorCode(Enum key) {
        return enumMap.get(key).getErrorCode();
    }

    //@Override
    public String getErrorMessage(Enum key) {
        return enumMap.get(key).getErrorMessage();
    }

    public CompositeFacadeResult add(Enum key, FacadeResult facadeResult) {
        enumMap.put(key, facadeResult);
        return this;
    }

    public void remove(Enum key) {
        remove(key);
    }

    public CompositeFacadeResult success() {
        this.errorCode = 0;
        return this;
    }

    public CompositeFacadeResult fail() {
        this.errorCode = -1;
        return this;
    }


}
