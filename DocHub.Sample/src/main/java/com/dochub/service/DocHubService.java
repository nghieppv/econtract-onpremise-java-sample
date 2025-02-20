package com.dochub.service;

import com.dochub.common.*;
import com.dochub.common.BatchImport.BatchImportDto;
import com.dochub.common.BatchImport.CreateBatchImportDataDto;
import com.dochub.common.CreateDocument.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocHubService {
    private String _accessToken;
    private String _baseUrl;
    public Result<String> AuthenticateAsync(String username, String password, String comId, String baseUrl) throws IOException {
        _baseUrl = baseUrl;
        Gson gson = new Gson();
//        CloseableHttpClient client = Ultils.initSecureClient();
//        HttpPost request = new HttpPost(_baseUrl + "/api/auth/password-login");
//        String para = new JSONObject()
//                .put("username", username)
//                .put("password", password)
//                .put("companyId", comId)
//                .toString();
//        StringEntity params = new StringEntity(para);
//        request.addHeader("content-type", "application/json");
//        request.setEntity(params);
//
//        CloseableHttpResponse response = client.execute(request);
//        String respContent = EntityUtils.toString(response.getEntity());
//
//        Result<String> result = gson.fromJson(respContent, Result.class);
//
//        if (result.getSuccess()){
//            _accessToken = result.getData();
//        }

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n\"username\":\""+username+"\",\r\n    \"password\":\""+password+"\",\r\n    \"companyId\":"+comId+"\r\n}\r\n");
        Request request = new Request.Builder()
                .url(baseUrl+"/api/auth/password-login")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();

        Result<String> result = gson.fromJson(jsonString, Result.class);

        if (result.getSuccess()){
            _accessToken = result.getData();
        }
        return result;
    }

    public Result<BatchImportDto> CreateBatchImportAsync(CreateBatchImportDataDto dto) throws IOException {
        Gson gson = new Gson();
//        CloseableHttpClient client = Ultils.initSecureClient();
//
//        HttpPost request = new HttpPost(_baseUrl + "/api/batch-imports/create-advanced");
//        request.addHeader("content-type", "application/json");
//
//        String oauth = Ultils.Bearer + " " + _accessToken;
//        request.addHeader(Ultils.AUTHORIZATION, oauth);
//
//        String para = gson.toJson(dto);
//        StringEntity params = new StringEntity(para, "UTF-8");
//        request.setEntity(params);
//
//        CloseableHttpResponse response = client.execute(request);
//        String respContent = EntityUtils.toString(response.getEntity());

        String content = gson.toJson(dto);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(_baseUrl + "/api/batch-imports/create-advanced")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+ _accessToken)
                .build();
        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();
        Result<BatchImportDto> result = gson.fromJson(jsonString, new TypeToken<Result<BatchImportDto>>() {});
        return result;
    }

    public Result<BatchImportDto> SendBatchDocumentAsync(int id) throws IOException {
        Gson gson = new Gson();
//        CloseableHttpClient client = Ultils.initSecureClient();
//
//        HttpPost request = new HttpPost(_baseUrl + "/api/batch-imports/send/" + id);
//        request.addHeader("content-type", "application/json");
//
//        String oauth = Ultils.Bearer + " " + _accessToken;
//        request.addHeader(Ultils.AUTHORIZATION, oauth);
//
//        CloseableHttpResponse response = client.execute(request);
//        String respContent = EntityUtils.toString(response.getEntity());

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(_baseUrl + "/api/batch-imports/send/" + id)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+ _accessToken)
                .build();
        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();

        Result<BatchImportDto> result = gson.fromJson(jsonString, new TypeToken<Result<BatchImportDto>>() {});
        return result;
    }

    public Result<DocumentDto> CreateDocumentAsync(CreateDocumentRequest docRequest) throws IOException {
        Gson g = new Gson();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("No",docRequest.getNo())
                .addFormDataPart("Subject",docRequest.getSubject())
                .addFormDataPart("File","sample.pdf",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(docRequest.getFileInfo().getFilePath())))
                .addFormDataPart("Description",docRequest.getDescription())
                .addFormDataPart("TypeId", String.valueOf(docRequest.getTypeId()))
                .addFormDataPart("DepartmentId", String.valueOf(docRequest.getDepartmentId()))
                .build();
        Request request = new Request.Builder()
                .url(_baseUrl+"/api/documents/create")
                .method("POST", body)
                .addHeader("Authorization", "Bearer "+ _accessToken)
                .build();
        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();

        // De-serialize to an object
        Result<DocumentDto> result = g.fromJson(jsonString, new TypeToken<Result<DocumentDto>>() {});
        return result;
    }

    public Result<DocumentList> getDocumentAsync(String search, int pageSize, int page, boolean waitToESign) throws IOException {
        // Build the query parameters
        Gson g = new Gson();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("search", URLEncoder.encode(search, StandardCharsets.UTF_8));
        queryParams.put("pageSize", String.valueOf(pageSize));
        queryParams.put("page", String.valueOf(page));
        queryParams.put("WaitToESign", String.valueOf(waitToESign));

        // Construct the full URL with query parameters
        StringBuilder queryBuilder = new StringBuilder(_baseUrl + "/api/documents?");
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            queryBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String requestUrl = queryBuilder.substring(0, queryBuilder.length() - 1);

        // Create an HTTP client
        var client = HttpClient.newHttpClient();

        // Create the HTTP GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .header("Authorization", "Bearer " + _accessToken)
                .GET()
                .build();

        // Send the request
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check response status
        if (response.statusCode() != 200) {
            throw new RuntimeException("Request failed with status code: " + response.statusCode());
        }
        String jsonString = response.body();

        Result<DocumentList> result = g.fromJson(jsonString, new TypeToken<Result<DocumentList>>() {});

        return result;
    }

    public Result<DocumentDto> UpdateProcessDocumentAsync(String docId, UpdateDocumentProcessRequest processRequest) throws IOException
    {
        Gson g = new Gson();
        String content = g.toJson(processRequest);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(_baseUrl + "/api/documents/update-process")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+ _accessToken)
                .build();
        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();

        Result<DocumentDto> result = g.fromJson(jsonString, new TypeToken<Result<DocumentDto>>() {});
        return result;
    }

    public Result<DocumentDto> SendProcessAsync(String docId) throws IOException
    {
        Gson g = new Gson();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(_baseUrl + "/api/documents/send-process/"+docId)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+ _accessToken)
                .build();
        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();
        Result<DocumentDto> result = g.fromJson(jsonString, new TypeToken<Result<DocumentDto>>() {});
        return result;
    }

    public String UpdateProcessAsync(ProcessDocumentDto process) throws IOException
    {
        Gson g = new Gson();
        String content = g.toJson(process);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(_baseUrl + "/api/documents/process")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+ _accessToken)
                .build();
        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();
        //Result<DocumentDto> result = g.fromJson(jsonString, new TypeToken<Result<DocumentDto>>() {});
        // Convert JSON response to Result<String> object
        //Result<String> result = g.fromJson(jsonString, new TypeToken<Result<String>>() {}.getType());
        //return result;
        return  jsonString;
    }

    public Result<DocumentDto> SendNotifyAsync(String docId) throws IOException
    {
        Gson g = new Gson();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(_baseUrl + "/api/documents/send-notify/"+docId)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+ _accessToken)
                .build();
        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();
        Result<DocumentDto> result = g.fromJson(jsonString, new TypeToken<Result<DocumentDto>>() {});
        return result;
    }
}
