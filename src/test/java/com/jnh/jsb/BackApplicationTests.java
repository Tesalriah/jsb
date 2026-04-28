package com.jnh.jsb;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class BackApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void t1() {
        List<Question> questionList = questionRepository.findAll();

        assertEquals(2, questionList.size());

        Question question = questionList.get(0);
        assertEquals("sbb가 무엇인가요?", question.getSubject());
    }

    @Test
    @DisplayName("findById")
    void t2() {
        Optional<Question> oq =  questionRepository.findById(1);

        if (oq.isPresent()) {
            Question q = oq.get();

            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }

    @Test
    @DisplayName("findBySubject")
    void t3() {
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?").get();
        // SELECT * FROM question WHERE subject = 'sbb가 무엇인가요?'
        assertThat(q.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("findBySubjectAndContent")
    void t4() {
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.").get();
        // SELECT * FROM question WHERE subject = 'sbb가 무엇인가요?' AND content = 'sbb에 대해서 알고 싶습니다.'
        assertThat(q.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("findBySubjectLike")
    void t5() {
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertThat(q.getSubject()).isEqualTo("sbb가 무엇인가요?");
    }

    @Test
    @DisplayName("수정")
    @Transactional
    void t6() { // 가장 먼저 실행시키기 위해서 t6가 아닌 t0으로 메서드명 변경
        Question question = questionRepository.findById(1).get();
        assertThat(question).isNotNull();

        question.setSubject("수정된 제목");
        questionRepository.save(question);

        Question foundQuestion = questionRepository.findBySubject("수정된 제목").get();
        assertThat(foundQuestion).isNotNull();
    }

    @Test
    @DisplayName("삭제")
    @Transactional
    void t9() {
        assertThat(questionRepository.count()).isEqualTo(2);

        Question question = questionRepository.findById(1).get();
        questionRepository.delete(question);

        assertThat(questionRepository.count()).isEqualTo(1);
    }
}
