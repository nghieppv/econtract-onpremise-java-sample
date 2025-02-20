package com.dochub.common.CreateDocument;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentDto {
    private String id ;
    private int contractValue ;
    private String customerCode ;
    private String customerInformation ;
    private String no ;
    private String subject ;
    private String downloadUrl ;
    private List<DocumentProcessDto> processes  = new ArrayList<>();
    private WaitingProcess waitingProcess;
    private List<WaitingProcess> waitingProcesses;
    private Status status;

    public DocumentDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getContractValue() {
        return contractValue;
    }

    public void setContractValue(int contractValue) {
        this.contractValue = contractValue;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerInformation() {
        return customerInformation;
    }

    public void setCustomerInformation(String customerInformation) {
        this.customerInformation = customerInformation;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public List<DocumentProcessDto> getProcesses() {
        return processes;
    }

    public void setProcesses(List<DocumentProcessDto> processes) {
        this.processes = processes;
    }


    public List<WaitingProcess> getWaitingProcesses() {
        return waitingProcesses;
    }

    public WaitingProcess getWaitingProcess() {
        return waitingProcess;
    }

    public  Status getstatus(){
        return status;
    }
    public static class AccessPermission {
        private int value;
        private String description;
        // Getters and Setters
    }

    public static class DisplayType {
        private int value;
        private String description;
        // Getters and Setters
    }

    public static class WaitingProcess {
        private String id;
        private String createdDate;
        private int comId;
        private boolean isOrder;
        private int orderNo;
        private int pageSign;
        private DisplayType displayType;
        private AccessPermission accessPermission;
        private Status status;
        private int processedByUserId;
        private User processedByUser;
        private String documentId;
        private List<Object> fillingItems;
        // Getters and Setters
    }
    public static class Status {
        private int value;
        private String description;

        // Getters and Setters
    }
    public static class User {
        private int id;
        private String code;
        private String name;
        private String email;
        private String phone;
        private int comId;

        // Getters and Setters
    }
}

