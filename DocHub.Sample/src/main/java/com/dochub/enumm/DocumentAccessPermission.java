package com.dochub.enumm;

public enum DocumentAccessPermission {
    ViewOnly(1),
    SignDraw(2),
    Approve(3),
    SignDigital(4);

    public int value;
    DocumentAccessPermission(int value) {
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
