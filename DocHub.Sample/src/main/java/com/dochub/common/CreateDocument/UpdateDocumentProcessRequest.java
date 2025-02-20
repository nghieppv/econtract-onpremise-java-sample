package com.dochub.common.CreateDocument;

import java.util.List;

public class UpdateDocumentProcessRequest {
    private String id;
    /// <summary>
    /// Xử lý tuần tự
    /// </summary>
    private boolean processInOrder;
    /// <summary>
    /// Danh sách quy trình thực hiện
    /// </summary>
    private List<UpdateDocumentProcessDto> processes;

    public UpdateDocumentProcessRequest(String id, boolean processInOrder, List<UpdateDocumentProcessDto> processes) {
        this.id = id;
        this.processInOrder = processInOrder;
        this.processes = processes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isProcessInOrder() {
        return processInOrder;
    }

    public void setProcessInOrder(boolean processInOrder) {
        this.processInOrder = processInOrder;
    }

    public List<UpdateDocumentProcessDto> getProcesses() {
        return processes;
    }

    public void setProcesses(List<UpdateDocumentProcessDto> processes) {
        this.processes = processes;
    }


}

