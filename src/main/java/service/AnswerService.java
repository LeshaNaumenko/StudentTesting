package service;

import model.entity.Answer;
import model.entity.Question;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class AnswerService {
    final static Logger logger = Logger.getLogger(QuestionService.class);


    public List<Answer> getAnswers(List<Question> questionList, List<String> parameters) {
        ArrayList<Answer> answers = new ArrayList<>();

        for (int counter = 0; counter < questionList.size(); counter++) {
            if (questionList.get(counter).getCorrectOption().equals(parameters.get(counter))) {
                answers.add(createAnswer(questionList, parameters, counter, Answer.AnswerStatus.CORRECT));
            }else {
                answers.add(createAnswer(questionList, parameters, counter, Answer.AnswerStatus.INCORRECT));
            }
        }
        return answers;
    }

    private Answer createAnswer(List<Question> questionList, List<String> parameters, int counter, Answer.AnswerStatus incorrect) {
        return new Answer.Builder()
                .setId(counter)
                .setQuestion(questionList.get(counter).getDescriptionOfQuestion())
                .setCorrectAnswer(questionList.get(counter).getCorrectOption())
                .setUserAnswer(parameters.get(counter))
                .setStatus(incorrect)
                .build();
    }

    public int getRightAnswers(List<Answer> answers) {
        return (int)answers.stream().filter(c -> (c.getStatus().equals(Answer.AnswerStatus.CORRECT))).count();
    }
}
