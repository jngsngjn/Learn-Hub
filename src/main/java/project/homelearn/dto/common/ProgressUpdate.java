package project.homelearn.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProgressUpdate {

    private int progress; // %
    private int total; // 총 인원
    private int current; // 현재 인원
    private int successCount;
}