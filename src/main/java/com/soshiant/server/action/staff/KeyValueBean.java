package com.soshiant.server.action.staff;

/**
 * Created by IntelliJ IDEA.
 * User: Mahta
 * Date: 10/7/11
 * Time: 5:26 AM
 */
public class KeyValueBean {
    String key;
    String value;

    public KeyValueBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
