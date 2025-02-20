package com.dochub.common.CreateDocument;

import com.dochub.enumm.SignatureDisplayMode;

import java.util.List;

public class ProcessDocumentDto {
    /// Id quy trình chờ xử lý - lấy từ waitingRecipient.processId (api đăng nhập bằng mã xử lý)
    private String processId;
    /// Chế độ hiển thị chữ ký
    private int signatureDisplayMode;
    /// Lý do
    private String reason;
    /// Hình ảnh chữ ký
    private String signatureImage;
    /// Số trang ký
    private int signingPage;
    /// Vị trí ký
    private String signingPosition;
    /// Chữ ký
    private String signatureText;
    /// Cỡ chữ
    private int fontSize;
    /// Mã xác nhận OTP
    private String otp;
    private boolean showReason;
    /// Từ chối ký chứng từ
    private boolean reject;
    /// Ký Base64Data, dùng cho phương pháp ký UsbToken
    private List<String> signedBase64Data; // use for SignMethod = UsbToken
    private boolean confirmTermsConditions;
    public ProcessDocumentDto() {
    }

    public ProcessDocumentDto(String processId, int signatureDisplayMode, String reason, String signatureImage, int signingPage, String signingPosition, String signatureText, int fontSize, String otp, boolean showReason, boolean reject, List<String> signedBase64Data, boolean confirmTermsConditions) {
        this.processId = processId;
        this.signatureDisplayMode = signatureDisplayMode;
        this.reason = reason;
        this.signatureImage = signatureImage;
        this.signingPage = signingPage;
        this.signingPosition = signingPosition;
        this.signatureText = signatureText;
        this.fontSize = fontSize;
        this.otp = otp;
        this.showReason = showReason;
        this.reject = reject;
        this.signedBase64Data = signedBase64Data;
        this.confirmTermsConditions= confirmTermsConditions;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public int getSignatureDisplayMode() {
        return signatureDisplayMode;
    }

    public void setSignatureDisplayMode(int signatureDisplayMode) {
        this.signatureDisplayMode = signatureDisplayMode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSignatureImage() {
        return signatureImage;
    }

    public void setSignatureImage(String signatureImage) {
        this.signatureImage = signatureImage;
    }

    public int getSigningPage() {
        return signingPage;
    }

    public void setSigningPage(int signingPage) {
        this.signingPage = signingPage;
    }

    public String getSigningPosition() {
        return signingPosition;
    }

    public void setSigningPosition(String signingPosition) {
        this.signingPosition = signingPosition;
    }

    public String getSignatureText() {
        return signatureText;
    }

    public void setSignatureText(String signatureText) {
        this.signatureText = signatureText;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean isShowReason() {
        return showReason;
    }

    public void setShowReason(boolean showReason) {
        this.showReason = showReason;
    }

    public boolean isReject() {
        return reject;
    }

    public void setReject(boolean reject) {
        this.reject = reject;
    }

    public List<String> getSignedBase64Data() {
        return signedBase64Data;
    }

    public void setSignedBase64Data(List<String> signedBase64Data) {
        this.signedBase64Data = signedBase64Data;
    }

    public boolean isConfirmTermsConditions() {
        return confirmTermsConditions;
    }

    public void setConfirmTermsConditions(boolean confirmTermsConditions) {
        this.confirmTermsConditions = confirmTermsConditions;
    }
}
