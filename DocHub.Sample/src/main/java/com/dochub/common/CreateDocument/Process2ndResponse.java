package com.dochub.common.CreateDocument;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Process2ndResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("createdDate")
    private String createdDate;

    @SerializedName("no")
    private String no;

    @SerializedName("subject")
    private String subject;

    @SerializedName("status")
    private Status status;

    @SerializedName("messages")
    private List<Message> messages;

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
}