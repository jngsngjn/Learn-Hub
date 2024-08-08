package project.homelearn.dto.common;

import lombok.Builder;
import lombok.Data;

// response
@Data
@Builder
public class HeaderCommonDto {

    private String curriculumFullName;
    private String name;
    private String imagePath;
    private Double progressRate;
}

/*
{
    "curriculumFullName": "네이버 클라우드 데브옵스 과정 1기",
    "name": "손흥민",
    "imagePath": "curriculum/ncp/1/profile/65cba0c9-715d-4a1a-a106-f37020282635.jpeg",
    "progressRate": 60.3
}
 */