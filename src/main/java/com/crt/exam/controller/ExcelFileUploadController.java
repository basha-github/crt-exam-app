package com.crt.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crt.exam.dto.UploadResponse;
import com.crt.exam.entity.Question;
import com.crt.exam.service.ExcelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Adjust for your React dev server
public class ExcelFileUploadController {

	@Autowired
	private ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
        	System.out.println("hello from react!!!!!!");
        	System.out.println("file ----------->"+file);
            if (file.isEmpty()) {
            	System.out.println(" file is empty.....");
                return ResponseEntity.badRequest()
                    .body(new UploadResponse(false, "Please select a file to upload", null, 0));
            }

            String contentType = file.getContentType();
            if (!isValidExcelFile(contentType)) {
            	System.out.println("invalid Excel file.......");
                return ResponseEntity.badRequest()
                    .body(new UploadResponse(false, "Invalid file type. Only Excel files are allowed", null, 0));
            }

            UploadResponse response = excelService.processExcelFile(file);
            System.out.println("file processed ... and response...."+response);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new UploadResponse(false, "Upload failed: " + e.getMessage(), null, 0));
        }
    }

    private boolean isValidExcelFile(String contentType) {
        return contentType != null && (
            contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
            contentType.equals("application/vnd.ms-excel")
        );
    }
    
    
    @GetMapping("/all/questions")
    public List<Question>  getAllQuestions(){
    
    	return excelService.getAllQuestions();
    
    
    }
}