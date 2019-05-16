package com.soshiant.server.facade;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface FacadeResult<K extends Enum, D> {

    public D getData();

    public int getErrorCode();

    public int getErrorStep();

    public String getErrorKey();

    public String getSqlText();

    public String getErrorMessage();

    public Object getData(K key);

    public int getErrorCode(K key);

}
