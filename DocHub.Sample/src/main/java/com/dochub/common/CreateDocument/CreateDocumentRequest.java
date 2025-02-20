package com.dochub.common.CreateDocument;

import java.util.Date;
import java.util.List;

public class CreateDocumentRequest {
    private FileInfoRequest FileInfo = new FileInfoRequest();
    /// <summary>
    /// Tiêu đề chứng từ
    /// </summary>
    ///
    private String Subject;
    /// <summary>
    /// Mô tả
    /// </summary>
    private String Description;
    /// <summary>
    /// Loại chứng từ (Id)
    /// </summary>
    ///
    private int TypeId;
    /// <summary>
    /// Mẫu chứng từ (Id)
    /// </summary>
    private int DocumentTemplateId;
    /// <summary>
    /// Bộ phân (Id)
    /// </summary>
    ///
    private int DepartmentId;
    /// <summary>
    /// Mã chứng từ
    /// </summary>
    ///
    private String No;
    /// <summary>
    /// Mã khách hàng
    /// </summary>
    private String CustomerCode;
    /// <summary>
    /// Thông tin khách hàng
    /// </summary>
    private String CustomerInformation;
    /// <summary>
    /// Giá trị hợp đồng
    /// </summary>
    private int ContractValue;
    /// <summary>
    /// Có hiệu lực từ
    /// </summary>
    private Date ValidFrom;
    /// <summary>
    /// Có hiệu lực đến
    /// </summary>
    private Date ValidTo;
    /// <summary>
    /// Ngày hết hạn
    /// </summary>
    private Date ExpiryDate;
    /// <summary>
    /// File đính kèm (Id)
    /// </summary>
    private List<Long> AttachmentIds;
    /// <summary>
    /// Chứng từ liên quan (Id)
    /// </summary>
    private List<String> RelatedDocumentIds;
    /// <summary>
    /// Người dùng được chia sẻ (Id)
    /// </summary>
    private List<Integer> SharedUserIds;
    /// <summary>
    /// Nhóm người dùng được chia sẻ (Id)
    /// </summary>
    private List<Integer> SharedUserGroupIds;
    /// <summary>
    /// Bộ phận được chia sẻ (Id)
    /// </summary>
    private List<Integer> SharedDepartmentIds;
    /// <summary>
    /// File đính kèm
    /// </summary>
    ///
    private List<byte[]> Attachments;

    public CreateDocumentRequest() {
    }

    public FileInfoRequest getFileInfo() {
        return FileInfo;
    }

    public void setFileInfo(FileInfoRequest fileInfo) {
        FileInfo = fileInfo;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public int getDocumentTemplateId() {
        return DocumentTemplateId;
    }

    public void setDocumentTemplateId(int documentTemplateId) {
        DocumentTemplateId = documentTemplateId;
    }

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getCustomerInformation() {
        return CustomerInformation;
    }

    public void setCustomerInformation(String customerInformation) {
        CustomerInformation = customerInformation;
    }

    public int getContractValue() {
        return ContractValue;
    }

    public void setContractValue(int contractValue) {
        ContractValue = contractValue;
    }

    public Date getValidFrom() {
        return ValidFrom;
    }

    public void setValidFrom(Date validFrom) {
        ValidFrom = validFrom;
    }

    public Date getValidTo() {
        return ValidTo;
    }

    public void setValidTo(Date validTo) {
        ValidTo = validTo;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        ExpiryDate = expiryDate;
    }

    public List<Long> getAttachmentIds() {
        return AttachmentIds;
    }

    public void setAttachmentIds(List<Long> attachmentIds) {
        AttachmentIds = attachmentIds;
    }

    public List<String> getRelatedDocumentIds() {
        return RelatedDocumentIds;
    }

    public void setRelatedDocumentIds(List<String> relatedDocumentIds) {
        RelatedDocumentIds = relatedDocumentIds;
    }

    public List<Integer> getSharedUserIds() {
        return SharedUserIds;
    }

    public void setSharedUserIds(List<Integer> sharedUserIds) {
        SharedUserIds = sharedUserIds;
    }

    public List<Integer> getSharedUserGroupIds() {
        return SharedUserGroupIds;
    }

    public void setSharedUserGroupIds(List<Integer> sharedUserGroupIds) {
        SharedUserGroupIds = sharedUserGroupIds;
    }

    public List<Integer> getSharedDepartmentIds() {
        return SharedDepartmentIds;
    }

    public void setSharedDepartmentIds(List<Integer> sharedDepartmentIds) {
        SharedDepartmentIds = sharedDepartmentIds;
    }

    public List<byte[]> getAttachments() {
        return Attachments;
    }

    public void setAttachments(List<byte[]> attachments) {
        Attachments = attachments;
    }
}
