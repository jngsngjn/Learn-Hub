package project.homelearn.controller.student.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.service.student.CompilerService;

import java.util.Map;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/students/compile")
@RequiredArgsConstructor
public class CompilerController {

    private final CompilerService compilerService;

    @PostMapping
    public String compileCode(@RequestBody Map<String, String> request) {
        String sourceCode = request.get("sourceCode");
        String language = request.get("language");
        return compilerService.compileCode(sourceCode, language);
    }
}
/*
{
    "sourceCode": "public class Main { public static void main(String[] args) { System.out.println(\"Hello, World!\"); } }",
    "language": "java"
}
{
    "sourceCode": "console.log(\"Hello, World!\");",
    "language": "javascript"
}
{
    "sourceCode": "print(\"Hello, World!\")",
    "language": "python"
}
 */