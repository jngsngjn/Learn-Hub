package project.homelearn.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.homelearn.entity.board.QuestionBoard;
import project.homelearn.entity.user.User;
import project.homelearn.repository.board.QuestionBoardRepository;
import project.homelearn.repository.user.UserRepository;
import project.homelearn.service.student.StudentQuestionBoardService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class ViewCountTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionBoardRepository questionBoardRepository;

    @Autowired
    private StudentQuestionBoardService studentQuestionBoardService;

    @Test
    public void testViewCountConcurrency() throws InterruptedException {
        // given
        Long questionBoardId = createTestQuestionBoard();
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        // when
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    studentQuestionBoardService.incrementViewCount(questionBoardId);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        // then
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();
        Assertions.assertThat(questionBoard.getViewCount()).isEqualTo(numberOfThreads);
    }

    private Long createTestQuestionBoard() {
        User user = userRepository.findByUsername("jngsngjn");

        QuestionBoard questionBoard = new QuestionBoard();
        questionBoard.setTitle("Test Title");
        questionBoard.setContent("Test Content");
        questionBoard.setUser(user);
        questionBoardRepository.save(questionBoard);
        return questionBoard.getId();
    }
}