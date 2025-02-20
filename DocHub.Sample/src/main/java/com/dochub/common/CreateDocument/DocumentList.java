package com.dochub.common.CreateDocument;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DocumentList {
    @SerializedName("items")  // Ánh xạ "items" từ JSON vào danh sách
    private List<DocumentDto> documents;

    public List<DocumentDto> getDocuments() {
        return documents;
    }
}
