package project.homelearn.controller.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.manager.inquiry.ManagerInquiryDto;
import project.homelearn.service.manager.ManagerInquiryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerInquiryController {

    private final ManagerInquiryService managerInquiryService;

    @GetMapping("/student-inquires")
    public List<ManagerInquiryDto> studentList(@RequestParam(name = "curriculumName", required = false) String curriculumName,
                                               @RequestParam(name = "curriculumTh", required = false) Long curriculumTh){

        List<ManagerInquiryDto> managerInquiries;
        if (curriculumTh != null && curriculumName != null && !curriculumName.isEmpty()){
            managerInquiries = managerInquiryService.getInquiryListWithCurriculumNameAndThFromStudents(curriculumName,curriculumTh);
        }
        else if (curriculumName != null && !curriculumName.isEmpty()){
            managerInquiries = managerInquiryService.getInquiryListWithCurriculumNameFromStudents(curriculumName);
        }
        else {
            managerInquiries = managerInquiryService.getInquiryListDefaultFromStudents();
        }

        return managerInquiries;
    }

    @GetMapping("/teacher-inquires")
    public List<ManagerInquiryDto> teacherList(@RequestParam(name = "curriculumName", required = false) String curriculumName,
                                               @RequestParam(name = "curriculumTh", required = false) Long curriculumTh){

        List<ManagerInquiryDto> managerInquiries;
        if (curriculumTh != null && curriculumName != null && !curriculumName.isEmpty()){
            managerInquiries = managerInquiryService.getInquiryListWithCurriculumNameAndThFromTeachers(curriculumName,curriculumTh);
        }
        else if (curriculumName != null && !curriculumName.isEmpty()){
            managerInquiries = managerInquiryService.getInquiryListWithCurriculumNameFromTeachers(curriculumName);
        }
        else {
            managerInquiries = managerInquiryService.getInquiryListDefaultFromTeachers();
        }

        return managerInquiries;
    }
}
