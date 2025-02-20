package com.dochub.enumm;

public enum SignatureDisplayMode {
    TextOnly(1),
    TextAndImage(2),
    ImageOnly(3);
    public int value;
    SignatureDisplayMode(int value) {
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    SignatureDisplayMode() {
    }

}
