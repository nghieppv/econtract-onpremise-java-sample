package com.dochub.common.CreateDocument;

import com.google.gson.annotations.SerializedName;

public class Process1ndResponse {
    @SerializedName("isBatchProcess")
    private boolean isBatchProcess;

    @SerializedName("isSingleProcess")
    private boolean isSingleProcess;

    @SerializedName("documentId")
    private String documentId;

    @SerializedName("accessPermission")
    private int accessPermission;

    @SerializedName("signMethod")
    private int signMethod;

    @SerializedName("signatureDisplayMode")
    private int signatureDisplayMode;

    @SerializedName("batchProcessStatus")
    private int batchProcessStatus;

    @SerializedName("signingPage")
    private int signingPage;

    @SerializedName("reason")
    private String reason;

    @SerializedName("confirmReason")
    private String confirmReason;

    @SerializedName("showReason")
    private boolean showReason;

    @SerializedName("fontSize")
    private int fontSize;

    @SerializedName("receiveOtpMethod")
    private int receiveOtpMethod;

    @SerializedName("receiveOtpPhone")
    private String receiveOtpPhone;

    @SerializedName("receiveOtpEmail")
    private String receiveOtpEmail;

    @SerializedName("requireOtpConfirmation")
    private boolean requireOtpConfirmation;

    @SerializedName("vnptCaPluginAdvancedSign")
    private boolean vnptCaPluginAdvancedSign;

    public boolean isBatchProcess() {
        return isBatchProcess;
    }

    public boolean isSingleProcess() {
        return isSingleProcess;
    }

    public String getDocumentId() {
        return documentId;
    }

    public int getAccessPermission() {
        return accessPermission;
    }

    public int getSignMethod() {
        return signMethod;
    }

    public int getSignatureDisplayMode() {
        return signatureDisplayMode;
    }

    public int getBatchProcessStatus() {
        return batchProcessStatus;
    }

    public int getSigningPage() {
        return signingPage;
    }

    public String getReason() {
        return reason;
    }

    public String getConfirmReason() {
        return confirmReason;
    }

    public boolean isShowReason() {
        return showReason;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getReceiveOtpMethod() {
        return receiveOtpMethod;
    }

    public String getReceiveOtpPhone() {
        return receiveOtpPhone;
    }

    public String getReceiveOtpEmail() {
        return receiveOtpEmail;
    }

    public boolean isRequireOtpConfirmation() {
        return requireOtpConfirmation;
    }

    public boolean isVnptCaPluginAdvancedSign() {
        return vnptCaPluginAdvancedSign;
    }
}
