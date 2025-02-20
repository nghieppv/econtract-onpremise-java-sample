package com.dochub.common.CreateDocument;

public class FileInfoRequest {
    private String FilePath;
    private byte[] File;
    private String FileName;

    public FileInfoRequest() {
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public byte[] getFile() {
        return File;
    }

    public void setFile(byte[] file) {
        File = file;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }
}
