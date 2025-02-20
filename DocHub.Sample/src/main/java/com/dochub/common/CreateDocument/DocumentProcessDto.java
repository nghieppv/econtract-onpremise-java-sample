package com.dochub.common.CreateDocument;

import com.dochub.enumm.DocumentAccessPermission;
import com.dochub.enumm.DocumentProcessStatus;
import com.dochub.enumm.EnumDto;

import java.util.Date;

public class DocumentProcessDto {
    private String id;
    private int comId;
    private boolean isOrder;
    private int orderNo;
    private String signatureText;
    private String reason;
    private int pageSign;
    private String position;
    private int fontSize;
    private EnumDto<Integer> accessPermission;
    private EnumDto<Integer> status;
    private int processedByUserId;
    private String documentId;

    public DocumentProcessDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public boolean isOrder() {
        return isOrder;
    }

    public void setOrder(boolean order) {
        isOrder = order;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getSignatureText() {
        return signatureText;
    }

    public void setSignatureText(String signatureText) {
        this.signatureText = signatureText;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getPageSign() {
        return pageSign;
    }

    public void setPageSign(int pageSign) {
        this.pageSign = pageSign;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public EnumDto<Integer> getAccessPermission() {
        return accessPermission;
    }

    public void setAccessPermission(EnumDto<Integer> accessPermission) {
        this.accessPermission = accessPermission;
    }

    public EnumDto<Integer> getStatus() {
        return status;
    }

    public void setStatus(EnumDto<Integer> status) {
        this.status = status;
    }


    public int getProcessedByUserId() {
        return processedByUserId;
    }

    public void setProcessedByUserId(int processedByUserId) {
        this.processedByUserId = processedByUserId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
