package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.common.ProgressUpdate;
import project.homelearn.dto.manager.enroll.StudentEnrollDto;
import project.homelearn.entity.user.Gender;

import java.io.IOException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ExcelService {

    private final SimpMessagingTemplate template;
    private final ManagerStudentService managerStudentService;

    @Async
    public void importStudentFile(MultipartFile file) {

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows() - 1; // header row 제외
            int currentRowNum = 0;
            int successCount = 0;

            // 총 학생 수 정보를 처음에 전송
            template.convertAndSend("/topic/progress", new ProgressUpdate(0, totalRows, 0, successCount));

            for (Row currentRow : sheet) {
                if (currentRow.getRowNum() == 0) {
                    continue; // skip header row
                }

                try {
                    String name = currentRow.getCell(0).getStringCellValue();
                    Gender gender = Gender.valueOf(currentRow.getCell(1).getStringCellValue().toUpperCase());
                    String email = currentRow.getCell(2).getStringCellValue();
                    String phone = currentRow.getCell(3).getStringCellValue();
                    String curriculumName = currentRow.getCell(4).getStringCellValue();

                    log.info("Processing row {}: name={}, gender={}, email={}, phone={}, curriculumName={}",
                            currentRow.getRowNum(), name, gender, email, phone, curriculumName);

                    StudentEnrollDto enrollDto = new StudentEnrollDto(name, gender, email, phone, curriculumName);
                    boolean result = managerStudentService.enrollStudent(enrollDto);
                    if (!result) {
                        log.error("Failed to enroll student: {}", enrollDto);
                    } else {
                        successCount++;
                    }

                    currentRowNum++;
                    int progress = (currentRowNum * 100) / totalRows;
                    template.convertAndSend("/topic/progress", new ProgressUpdate(progress, totalRows, currentRowNum, successCount));
                } catch (Exception e) {
                    log.error("Error processing row {}: ", currentRow.getRowNum(), e);
                }
            }
        } catch (IOException e) {
            log.error("Error importing file : ", e);
        } catch (Exception e) {
            log.error("Fail enroll students : ", e);
        }
    }
}