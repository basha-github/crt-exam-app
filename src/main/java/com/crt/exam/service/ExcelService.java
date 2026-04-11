package com.crt.exam.service;



import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crt.exam.dto.UploadResponse;
import com.crt.exam.entity.Question;
import com.crt.exam.repo.QuestionsRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExcelService {
	
	
	@Autowired
	private QuestionsRepo questionRepo;

    public UploadResponse processExcelFile(MultipartFile file) {
    	List<Question> questionList = new ArrayList<Question>();
        
        List<Map<String, Object>> data = new ArrayList<>();
        int rowCount = 0;

        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            
            List<String> headers = new ArrayList<>();
            if (headerRow != null) {
                for (Cell cell : headerRow) {
                    headers.add(getCellValueAsString(cell));
                }
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, Object> rowData = new HashMap<>();
                Question question = new Question();
                question.setQno(UUID.randomUUID().toString());
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j);
                    rowData.put(headers.get(j), getCellValue(cell));
                    if(j==1) {
                    	question.setTopic(getCellValue(cell).toString());
                    }
                    else if(j==2) {
                    	question.setContent(getCellValue(cell).toString());
                    }
                    else if(j==3) {
                    	question.setOpt1(getCellValue(cell).toString());
                    }
                    else if(j==4) {
                    	question.setOpt2(getCellValue(cell).toString());
                    }
                    else if(j==5) {
                    	question.setOpt3(getCellValue(cell).toString());
                    }
                    else if(j==6) {
                    	question.setOpt4(getCellValue(cell).toString());
                    }
                    else if(j==7) {
                    	question.setAns(getCellValue(cell).toString());
                    }
                    else if(j==8) {
                    	question.setExplaination(getCellValue(cell).toString());
                    }
                }
                questionList.add(question);
                data.add(rowData);
                rowCount++;
            }

            saveDataIntoDB(questionList);
           // log.info("Processed {} rows from file: {}", rowCount, file.getOriginalFilename());

            return new UploadResponse(
                true, 
                "File uploaded successfully", 
                data, 
                rowCount
            );

        } catch (Exception e) {
           // log.error("Error processing Excel file", e);
            throw new RuntimeException("Failed to process Excel file: " + e.getMessage());
        }
    }

    private void saveDataIntoDB(List<Question> questionList) {
    	System.out.println("Before Saving Questions into DB");
		try {
    	questionRepo.saveAll(questionList);
    	System.out.println("Successfully  Savved Questions into DB");
		}catch(Exception e) {
			System.out.println("Error occured while Saving Questions into the DB....");
			e.printStackTrace();
		}
		
	}

	private Object getCellValue(Cell cell) {
        if (cell == null) return null;
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                }
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    private String getCellValueAsString(Cell cell) {
        Object value = getCellValue(cell);
        return value != null ? value.toString() : "";
    }

	public List<Question> getAllQuestions() {
		return questionRepo.findAll();
	}
}