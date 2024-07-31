package project.homelearn.dto.chatgpt;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequestDto {
    private String model;
    private List<StudentQuestionDto> messages;

    public ChatGPTRequestDto(String model, String prompt){
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new StudentQuestionDto("user", prompt));
    }
}
