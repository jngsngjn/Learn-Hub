package project.homelearn.dto.teacher.subject;

import lombok.Data;

import java.util.List;


// response
@Data
public class SimilarityGroupDto {
    private List<String> studentNames;
}