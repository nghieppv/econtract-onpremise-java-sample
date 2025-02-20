package com.dochub.common.CreateDocument;

public class UpdateDocumentProcessDto {
    private String id;
    private int orderNo;
    private int processedByUserId;
    private String processedByUserCode;
    /// <summary>
    /// ViewOnly = 1,
    /// SignDraw = 2,
    /// Approve = 3,
    /// SignDigital = 4,
    /// </summary>
    private int accessPermission;
    private String accessPermissionCode;
    private String position;
    private int pageSign;

    public UpdateDocumentProcessDto() {
    }

    public UpdateDocumentProcessDto(int orderNo, String processedByUserCode, String accessPermissionCode, String position) {
        this.orderNo = orderNo;
        this.processedByUserCode = processedByUserCode;
        this.accessPermissionCode = accessPermissionCode;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getProcessedByUserId() {
        return processedByUserId;
    }

    public void setProcessedByUserId(int processedByUserId) {
        this.processedByUserId = processedByUserId;
    }

    public String getProcessedByUserCode() {
        return processedByUserCode;
    }

    public void setProcessedByUserCode(String processedByUserCode) {
        this.processedByUserCode = processedByUserCode;
    }

    public int getAccessPermission() {
        return accessPermission;
    }

    public void setAccessPermission(int accessPermission) {
        this.accessPermission = accessPermission;
    }

    public String getAccessPermissionCode() {
        return accessPermissionCode;
    }

    public void setAccessPermissionCode(String accessPermissionCode) {
        this.accessPermissionCode = accessPermissionCode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPageSign() {
        return pageSign;
    }

    public void setPageSign(int pageSign) {
        this.pageSign = pageSign;
    }
}
