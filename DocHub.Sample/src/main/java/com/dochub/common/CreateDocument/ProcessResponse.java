package com.dochub.common.CreateDocument;

import com.dochub.common.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class ProcessResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("createdDate")
    private String createdDate;

    @JsonProperty("lastModifiedDate")
    private String lastModifiedDate;

    @JsonProperty("completedDate")
    private String completedDate;

    @JsonProperty("no")
    private String no;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("description")
    private String description;

    @JsonProperty("waitingProcess")
    private WaitingProcess waitingProcess;

    @JsonProperty("processInOrder")
    private boolean processInOrder;

    @JsonProperty("type")
    private DocumentType type;

    @JsonProperty("file")
    private DocumentFile file;

    @JsonProperty("downloadUrl")
    private String downloadUrl;

    @JsonProperty("receiveOtpMethod")
    private Integer receiveOtpMethod;

    @JsonProperty("receiveOtpPhone")
    private String receiveOtpPhone;

    @JsonProperty("receiveOtpEmail")
    private String receiveOtpEmail;

    @JsonProperty("requireOtpConfirmation")
    private Boolean requireOtpConfirmation;

    public static class DocumentFile {
        @JsonProperty("name")
        private String name;

        @JsonProperty("size")
        private long size;
        // Getters & Setters
    }

    public static class DocumentType {
        @JsonProperty("id")
        private int id;

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;
    }

    public static class WaitingProcess {
        @JsonProperty("id")
        private String id;

        @JsonProperty("createdDate")
        private String createdDate;

        @JsonProperty("isOrder")
        private boolean isOrder;

        @JsonProperty("orderNo")
        private int orderNo;

        @JsonProperty("pageSign")
        private int pageSign;

        @JsonProperty("position")
        private String position;

        @JsonProperty("accessPermission")
        private AccessPermission accessPermission;

        @JsonProperty("status")
        private Status status;

        // Constructor mặc định
        public WaitingProcess() {}
    }
    public static class AccessPermission {
        @JsonProperty("value")
        private int value;

        @JsonProperty("description")
        private String description;

        // Constructor mặc định
        public AccessPermission() {}
    }
    public static class Status {
        @SerializedName("value")
        private int value;

        @SerializedName("description")
        private String description;
    }

    public static class Message {
        @SerializedName("createdDate")
        private String createdDate;

        @SerializedName("content")
        private String content;
    }

    // Constructor mặc định
    public ProcessResponse() {}

    // Getters và Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCreatedDate() { return createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

    public String getLastModifiedDate() { return lastModifiedDate; }
    public void setLastModifiedDate(String lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }

    public String getCompletedDate() { return completedDate; }
    public void setCompletedDate(String completedDate) { this.completedDate = completedDate; }

    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public WaitingProcess getWaitingProcess() { return waitingProcess; }
    public void setWaitingProcess(WaitingProcess waitingProcess) { this.waitingProcess = waitingProcess; }

    public boolean isProcessInOrder() { return processInOrder; }
    public void setProcessInOrder(boolean processInOrder) { this.processInOrder = processInOrder; }

    public DocumentType getType() { return type; }
    public void setType(DocumentType type) { this.type = type; }

    public DocumentFile getFile() { return file; }
    public void setFile(DocumentFile file) { this.file = file; }

    public String getDownloadUrl() { return downloadUrl; }
    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }

    public Integer getReceiveOtpMethod() { return receiveOtpMethod; }
    public void setReceiveOtpMethod(Integer receiveOtpMethod) { this.receiveOtpMethod = receiveOtpMethod; }
    public String getReceiveOtpPhone() { return receiveOtpPhone; }
    public void setReceiveOtpPhone(String receiveOtpPhone) { this.receiveOtpPhone = receiveOtpPhone; }
    public String getReceiveOtpEmail() { return receiveOtpEmail; }
    public void setReceiveOtpEmail(String receiveOtpEmail) { this.receiveOtpEmail = receiveOtpEmail; }
    public Boolean getRequireOtpConfirmation() { return requireOtpConfirmation; }
    public void setRequireOtpConfirmation(Boolean requireOtpConfirmation) { this.requireOtpConfirmation = requireOtpConfirmation; }
}
