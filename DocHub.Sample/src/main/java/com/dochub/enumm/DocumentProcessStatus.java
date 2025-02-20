package com.dochub.enumm;

public enum DocumentProcessStatus {
    Rejected(-1),
    Waiting(1),
    Viewed(2),
    Signed(3),
    Approved(4),
    InQueue(5);

    public int value;
    DocumentProcessStatus(int value) {
        this.value=value;
    }
}
