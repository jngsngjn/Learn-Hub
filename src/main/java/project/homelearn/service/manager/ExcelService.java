package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.manager.enroll.StudentEnrollDto;
import project.homelearn.entity.user.Gender;

import java.io.IOException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ExcelService {

    private final ManagerStudentService managerStudentService;

    public boolean importStudentFile(MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            /*
            0 : NAME
            1 : GENDER
            2 : EMAIL
            3 : PHONE
            4 : CURRICULUM
             */
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

                    StudentEnrollDto enrollDto = new StudentEnrollDto(name, gender, email, phone, curriculumName);
                    managerStudentService.enrollStudent(enrollDto);
                } catch (Exception e) {
                    log.error("Error processing row {}: ", currentRow.getRowNum(), e);
                    // 개별 오류 발생 시 해당 오류를 기록하고 다음 학생 데이터 처리 계속 진행
                }
            }
            return true;
        } catch (IOException e) {
            log.error("Error importing file : ", e);
            return false;
        } catch (Exception e) {
            log.error("Fail enroll students : ", e);
            return false;
        }
    }
}