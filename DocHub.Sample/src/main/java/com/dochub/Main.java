package com.dochub;

import com.dochub.common.BatchImport.BatchImportDto;
import com.dochub.common.BatchImport.CreateBatchImportDataDto;
import com.dochub.common.BatchImport.PlaceHolderKey;
import com.dochub.common.CreateDocument.*;
import com.dochub.common.Result;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.dochub.enumm.SignatureDisplayMode;
import com.dochub.service.DocHubService;
import com.dochub.utils.Utils;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.lang3.RandomStringUtils;
import com.google.gson.Gson;
public class Main {

    static DocHubService docHubService = new DocHubService();

    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();

        // [1] Xác thực + Chọn công ty => Lấy thông tin access token
        Authenticate();

        // [2] Tạo chứng từ
        // [2.2] Tạo chứng từ import theo lô
        //          -CreateBatchImport()

        // [2.1] Tạo chứng từ (từ mẫu file pdf)
        DocumentDto doc = CreateDocument();

        // [3] Cập nhật quy trình chứng từ
        List<DocumentProcessDto> lstProcess = UpdateProcessDocument(doc.getId());

        // [4] Gửi quy trình chứng từ
        SendProcess(doc.getId());

        // [5] Lấy danh sách chứng từ (lấy chứng từ vừa tạo)
        DocumentList docWaitingProcess = GetDocument(doc.getNo());

        // [5.1] Trả kết quả (waitingProcess)
        Utils.ConsoleWriteLine(String.format("No:[%s]::::Waiting Process::::%s", doc.getNo(), gson.toJson(docWaitingProcess.getDocuments().get(0).getWaitingProcess())), Utils.ConsoleColor.RED);
        
        // [6] Xử lý chứng từ
        Utils.ConsoleWriteLine("::::LOOP::::", Utils.ConsoleColor.YELLOW);
        DocumentProcessDto processIdApprove = lstProcess.get(0);
        
        // [6.1] Xử lý chứng từ [Approve] xác nhận xử lý chứng từ
        var  approve1ndResult=  Approve(processIdApprove.getId(), null);
        if (approve1ndResult.getData().getReceiveOtpMethod() != Utils.ReceiveOtpMethod.NONE.getCode()) {
            Utils.ConsoleWriteLine(String.format("\nOTP sent to %s %s. [Approve] No: {%s}", Utils.ReceiveOtpMethod.fromCode(approve1ndResult.getData().getReceiveOtpMethod()),
                            approve1ndResult.getData().getReceiveOtpMethod() == Utils.ReceiveOtpMethod.EMAIL.getCode() ? String.format("Address:%s", approve1ndResult.getData().getReceiveOtpEmail()) : String.format("Phone: %s", approve1ndResult.getData().getReceiveOtpPhone()),
                            doc.getNo()),
                    Utils.ConsoleColor.GREEN);
            Utils.ConsoleWriteLine("- Please enter OTP to confirm. (OTP is valid within 5) ", Utils.ConsoleColor.GREEN);

            String otp;
            while (true){
                otp = Utils.readInput();
                // [6.1.1] Xử lý chứng từ [Approve] (trường hợp có OTP)
                var  approve2ndResult = Approve(processIdApprove.getId(), otp);
                if(approve2ndResult.getMessages()[0].equalsIgnoreCase("invalid otp")){
                    Utils.ConsoleWriteLine("Invalid OTP. Please try again.", Utils.ConsoleColor.RED);
                }else{
                    break;
                }
            }
        }else{
            Utils.ConsoleWriteLine("You have not registered to receive {sign, approve the use of 2-factor authentication}, Please contact admin!", Utils.ConsoleColor.RED);
        }
        DocumentProcessDto processIdSignDraw = lstProcess.get(1);
        
        // [6.2] Xử lý chứng từ [SignDraw] Ký nháy
        var signDraw1ndResult = SignDraw(processIdSignDraw.getId(), null);
        if (signDraw1ndResult.getData().getReceiveOtpMethod() != Utils.ReceiveOtpMethod.NONE.getCode()) {
            Utils.ConsoleWriteLine(String.format("\nOTP sent to %s %s. [SignDraw]  No: {%s}", Utils.ReceiveOtpMethod.fromCode(signDraw1ndResult.getData().getReceiveOtpMethod()),
                            signDraw1ndResult.getData().getReceiveOtpMethod() == Utils.ReceiveOtpMethod.EMAIL.getCode() ? String.format("Address:%s", signDraw1ndResult.getData().getReceiveOtpEmail()) : String.format("Phone: %s", signDraw1ndResult.getData().getReceiveOtpPhone()),
                            doc.getNo()),
                    Utils.ConsoleColor.GREEN);
            Utils.ConsoleWriteLine("- Please enter OTP to confirm. (OTP is valid within 5) ", Utils.ConsoleColor.GREEN);

            String otp;
            while (true){
                otp = Utils.readInput();
                
                // [6.2.1] Xử lý chứng từ [SignDraw] Ký nháy (có OTP)
                var  signDraw2ndResult = SignDraw(processIdSignDraw.getId(), otp);
                if(signDraw2ndResult.getMessages()[0].equalsIgnoreCase("invalid otp")){
                    Utils.ConsoleWriteLine("Invalid OTP. Please try again.", Utils.ConsoleColor.RED);
                }else{
                    break;
                }
            }
        }else{
            Utils.ConsoleWriteLine("You have not registered to receive {sign, approve the use of 2-factor authentication}, Please contact admin!", Utils.ConsoleColor.RED);
        }
        
        // [6.3] Xử lý chứng từ [ESign] Ký điện tử
        DocumentProcessDto processIdSignElectronic = lstProcess.get(2);
        var sign1ndResult = SignElectronic(processIdSignElectronic.getId(), null);
        if (sign1ndResult.getData().getReceiveOtpMethod() != Utils.ReceiveOtpMethod.NONE.getCode()) {
            Utils.ConsoleWriteLine(String.format("\nOTP sent to %s %s. [ESign] No: {%s}", Utils.ReceiveOtpMethod.fromCode(signDraw1ndResult.getData().getReceiveOtpMethod()),
                            sign1ndResult.getData().getReceiveOtpMethod() == Utils.ReceiveOtpMethod.EMAIL.getCode() ? String.format("Address:%s", sign1ndResult.getData().getReceiveOtpEmail()) : String.format("Phone: %s", sign1ndResult.getData().getReceiveOtpPhone()),
                            doc.getNo()),
                    Utils.ConsoleColor.GREEN);
            Utils.ConsoleWriteLine("- Please enter OTP to confirm. (OTP is valid within 5) ", Utils.ConsoleColor.GREEN);

            String otp;
            while (true){
                otp = Utils.readInput();
                
                // [6.3.1] Xử lý chứng từ [ESign] Ký điện tử (có Otp)
                var  sign2ndResult = SignElectronic(processIdSignElectronic.getId(), otp);
                if(sign2ndResult.getMessages()[0].equalsIgnoreCase("invalid otp")){
                    Utils.ConsoleWriteLine("Invalid OTP. Please try again.", Utils.ConsoleColor.RED);
                }else{
                    break;
                }
            }
        }else{
            Utils.ConsoleWriteLine("You have not registered to receive {sign, approve the use of 2-factor authentication}, Please contact admin!", Utils.ConsoleColor.RED);
        }
        
        // Gửi thông báo chứng từ xử lý hoàn tất
        Utils.ConsoleWriteLine("::::END LOOP::::", Utils.ConsoleColor.YELLOW);
        
        // [7] Gửi thông báo chứng từ xử lý hoàn tất - optional
        SendNotify(doc.getId());

        // [8] Lấy danh sách chứng từ
        DocumentList docStatus = GetDocument(doc.getNo());

        // [8.1] Kiểm tra trạng thái chứng từ
        Utils.ConsoleWriteLine(String.format("::::Status Document::::%s", gson.toJson(docStatus.getDocuments().get(0).getstatus())), Utils.ConsoleColor.RED);
    }

    public static void CreateBatchImport(){
        try {
            FileReader reader = new FileReader("appsettings.properties");

            Properties properties = new Properties();
            properties.load(reader);

            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String comId = properties.getProperty("comId");
            String baseUrl = properties.getProperty("baseUrl");

            int documentTemplateId = 1141; // lấy từ api danh sách mẫu chứng từ (/api/document-templates)
            int documentTypeId = 1089;// lấy từ api danh sách loại chứng từ (/api/document-types)
            int departmentId = 33;// lấy từ api danh sách bộ phận (/api/departments)

            DocHubService docHubService = new DocHubService();

            // Xác thực
            Result<String> authResult = null;

            authResult = docHubService.AuthenticateAsync(username, password, comId, baseUrl);

            System.out.println(authResult.getMessages()[0]);

            // Tạo lô chứng từ
            CreateBatchImportDataDto batchImportData = new CreateBatchImportDataDto();
            //batchImportData.setName("Ten lo");
            batchImportData.setDocumentTemplateId(documentTemplateId);
            batchImportData.setDocumentTypeId(documentTypeId);
            batchImportData.setDepartmentId(departmentId);

            // parameters - các tham số - các tham số phải đúng thứ tự
            // 6 tham số đầu là bắt buộc - ko được thay đổi thứ tự
            List<String> parameters = new ArrayList<>();
            parameters.addAll(Arrays.asList(
                    PlaceHolderKey.FileName,
                    PlaceHolderKey.No,
                    PlaceHolderKey.Subject,
                    PlaceHolderKey.ExpiryDate,
                    PlaceHolderKey.Description,
                    PlaceHolderKey.IsOrder));

            // danh sách người nhận - không được thay đổi thứ tự - gồm cặp người xử lý + quyền truy cập
            // VD: quy trình gồm 2 người xử lý
            for (int i = 1; i <= 2; i++)
            {
                parameters.addAll(Arrays.asList(PlaceHolderKey.Code, PlaceHolderKey.AccessPermission));
            }

            // tham số phụ => lấy từ api lấy thông tin mẫu chứng từ (/api/document-templates/{id})
            parameters.add("{{day}}");
            parameters.add("{{month}}");
            parameters.add("{{year}}");
            parameters.add("{{ben_a}}");
            parameters.add("{{ben_b}}");
            parameters.add("{{dien_tich_dat}}");
            parameters.add("{{dien_tich_nha}}");

            List<List<String>> rows = new ArrayList<List<String>>();
            String rdDocumentNo = RandomStringUtils.random(4, true, true);
            for (int i = 1; i <= 2; i++)
            {
                List<String> row = new ArrayList<>();
                row.addAll(Arrays.asList(
                        rdDocumentNo + i, // tên tệp tin
                        rdDocumentNo + i, // mã chứng từ không được phép trùng
                        "Hợp đồng " + rdDocumentNo + i, //tên chứng từ
                        "", //ngày hết hạn - định dạng dd/mm/yyyy (vd: 20/11/2022)
                        "Chứng từ thử nghiệm", //mô tả (nếu có)
                        "Y" //xử lý tuần tự (Y/N)
                ));

                // quy trình ký - số lượng người phải khớp với tham số phía trên - quy trình gồm 2 người thì phải thêm đủ 2 người
                row.addAll(Arrays.asList("baoth", "DR"));
                row.addAll(Arrays.asList("baoth", "D"));

                // Dữ liệu fill => truyền đúng vị trí ứng với tham số bên trên
                row.add("23");
                row.add("11");
                row.add("2022");
                row.add("Nguyễn Văn A_" + i);
                row.add("Nguyễn Văn B_" + i);
                row.add("1000");
                row.add("700");

                rows.add(row);
            }

            batchImportData.setParameters(parameters);
            batchImportData.setRows(rows);

            Result<BatchImportDto> createResult = docHubService.CreateBatchImportAsync(batchImportData);
            System.out.println(createResult.getMessages()[0]);

//            if (createResult.getSuccess() && createResult.getData() != null){
//                int batchId = createResult.getData().getId();
//                Result<BatchImportDto> sendResult = docHubService.SendBatchDocumentAsync(batchId);
//                System.out.println(sendResult.getMessages()[0]);
//            }
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
    public static Result<String> Authenticate() throws IOException{
        Properties properties = new Properties();
        FileReader reader = new FileReader("appsettings.properties");
        properties.load(reader);

        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String comId = properties.getProperty("comId");
        String baseUrl = properties.getProperty("baseUrl");

        Result<String> authResult = docHubService.AuthenticateAsync(username, password, comId, baseUrl);
        Utils.ConsoleWriteLine(authResult.getMessages()[0], authResult.getSuccess() ? Utils.ConsoleColor.GREEN: Utils.ConsoleColor.RED);
        return authResult;
    }

    public static DocumentDto CreateDocument() throws IOException
    {
        // Site 213
        int documentTypeId = 46;// lấy từ api danh sách loại chứng từ (/api/document-types)
        int departmentId = 33;// lấy từ api danh sách bộ phận (/api/departments)

        // Xác thực
        // Result<String> authResult = null;
        // authResult = docHubService.AuthenticateAsync(username, password, comId, baseUrl);
        // System.out.println(authResult.getMessages()[0]);

        // Tạo mới chứng từ
        String randomText = String.valueOf(UUID.randomUUID()).substring(32).toUpperCase();
        CreateDocumentRequest request = new CreateDocumentRequest();
        request.setTypeId(documentTypeId);
        request.setDepartmentId(departmentId);
        request.setNo(randomText);
        request.setSubject("Chứng từ thử nghiệm " + randomText);
        request.setDescription("Chứng từ thử nghiệm");

        //---------------------------------
        // Trường hợp sử dụng đường dẫn tệp tin chứng từ cố định
        request.getFileInfo().setFilePath("./src/main/java/com/dochub/Sample_file/sample.pdf");

        Result<DocumentDto> result = docHubService.CreateDocumentAsync(request);
        Utils.ConsoleWriteLine(result.getMessages()[0], result.getSuccess() ? Utils.ConsoleColor.GREEN: Utils.ConsoleColor.RED);

        return result.getData();
    }

    private static List<DocumentProcessDto> UpdateProcessDocument(String docId) throws IOException
    {
        List<UpdateDocumentProcessDto> lstProcess = new ArrayList<>();
        UpdateDocumentProcessDto approve = new UpdateDocumentProcessDto();
        approve.setOrderNo(1);
        approve.setProcessedByUserCode("LUULONG");
        approve.setAccessPermissionCode("A");

        UpdateDocumentProcessDto signDraw = new UpdateDocumentProcessDto();
        signDraw.setOrderNo(2);
        signDraw.setProcessedByUserCode("LUULONG");
        signDraw.setAccessPermissionCode("DR");
        signDraw.setPosition("0,702,192,792");

        UpdateDocumentProcessDto reject = new UpdateDocumentProcessDto();
        reject.setOrderNo(3);
        reject.setProcessedByUserCode("LUULONG");
        reject.setAccessPermissionCode("E");
        reject.setPosition("420,702,612,792");
        lstProcess.add(approve);
        lstProcess.add(signDraw);
        lstProcess.add(reject);

        UpdateDocumentProcessRequest processRequest = new UpdateDocumentProcessRequest(docId,true,lstProcess);

        Result<DocumentDto> result =  docHubService.UpdateProcessDocumentAsync(docId, processRequest);
        Utils.ConsoleWriteLine(result.getMessages()[0], result.getSuccess() ? Utils.ConsoleColor.GREEN: Utils.ConsoleColor.RED);

        return result.getData().getProcesses();
    }


    private static boolean SendProcess(String docId) throws IOException
    {
        Result<DocumentDto> result =  docHubService.SendProcessAsync(docId);
        Utils.ConsoleWriteLine(result.getMessages()[0], result.getSuccess() ? Utils.ConsoleColor.GREEN: Utils.ConsoleColor.RED);
        return result.getSuccess();
    }

    private static boolean SendNotify(String docId) throws IOException
    {
        Result<DocumentDto> result =  docHubService.SendNotifyAsync(docId);
        Utils.ConsoleWriteLine(result.getMessages()[0], result.getSuccess() ? Utils.ConsoleColor.GREEN: Utils.ConsoleColor.RED);
        return result.getSuccess();
    }

    private static DocumentList GetDocument(String no) throws IOException
    {
        Result<DocumentList> result =  docHubService.getDocumentAsync(no , 10 , 1 , false);
        //Utils.ConsoleWriteLine(result.getMessages()[0], result.getSuccess() ? Utils.ConsoleColor.GREEN: Utils.ConsoleColor.RED);
        return result.getData();
    }

    private static Result<ProcessResponse> Approve(String processId , String otp) throws IOException
    {
        Gson g = new Gson();
        ProcessDocumentDto process = new ProcessDocumentDto();
        process.setProcessId(processId);
        process.setSignatureDisplayMode(SignatureDisplayMode.ImageOnly.getValue());
        process.setReason("Đồng ý");
        process.setOtp(otp != null ? otp : "");
        process.setShowReason(false);
        process.setReject(false);
        process.setConfirmTermsConditions(true);
        String jsonResult =  docHubService.UpdateProcessAsync(process);
        Result<ProcessResponse> result = g.fromJson(jsonResult, new com.google.gson.reflect.TypeToken<Result<ProcessResponse>>() {});
        Utils.ConsoleWriteLine(result.getMessages()[0], result.getSuccess() ? Utils.ConsoleColor.GREEN: Utils.ConsoleColor.RED);
        return result;
    }

    private static Result<ProcessResponse> SignDraw(String processId, String otp ) throws IOException
    {
        File file = new File("./src/main/java/com/dochub/Sample_file/signature_base64String.txt");
        String base64String = Files.asCharSource(file, Charsets.UTF_8).read();
        ProcessDocumentDto process = new  ProcessDocumentDto();
        Gson g = new Gson();

        process.setProcessId(processId);
        process.setSignatureDisplayMode(SignatureDisplayMode.TextAndImage.getValue());
        process.setReason("Đồng ý");
        process.setSignatureImage(base64String);
        process.setSigningPage(1);
        process.setSigningPosition("0,702,192,792");
        process.setSignatureText("Xác nhận nội dung chứng từ");
        process.setFontSize(12);
        process.setOtp(otp != null ? otp : "");
        process.setShowReason(false);
        process.setReject(false);
        process.setConfirmTermsConditions(true);
        String jsonResult =  docHubService.UpdateProcessAsync(process);
        Result<ProcessResponse> result = g.fromJson(jsonResult, new com.google.gson.reflect.TypeToken<Result<ProcessResponse>>() {});
        Utils.ConsoleWriteLine(result.getMessages()[0], result.getSuccess() ? Utils.ConsoleColor.GREEN: Utils.ConsoleColor.RED);
        return result;
    }


    private static Result<ProcessResponse> SignElectronic(String processId, String otp ) throws IOException
    {
        File file = new File("./src/main/java/com/dochub/Sample_file/signature_base64String.txt");
        String base64String = Files.asCharSource(file, Charsets.UTF_8).read();
        ProcessDocumentDto process = new  ProcessDocumentDto();
        Gson g = new Gson();
        process.setProcessId(processId);
        process.setSignatureDisplayMode(SignatureDisplayMode.TextAndImage.getValue());
        process.setReason("Đồng ý");
        process.setSignatureImage(base64String);
        process.setSigningPage(1);
        process.setSigningPosition("0,702,192,792");
        process.setSignatureText("Xác nhận nội dung chứng từ");
        process.setFontSize(12);
        process.setOtp(otp != null ? otp : "");
        process.setShowReason(false);
        process.setReject(false);
        process.setConfirmTermsConditions(true);
        String jsonResult =  docHubService.UpdateProcessAsync(process);
        Result<ProcessResponse> result = g.fromJson(jsonResult, new com.google.gson.reflect.TypeToken<Result<ProcessResponse>>() {});
        Utils.ConsoleWriteLine(result.getMessages()[0], result.getSuccess() ? Utils.ConsoleColor.GREEN: Utils.ConsoleColor.RED);
        return result;
    }
}
