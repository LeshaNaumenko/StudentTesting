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

        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getCorrect_option().equals(parameters.get(i))) {
                answers.add(new Answer.Builder()
                        .setId(i)
                        .setQuestion(questionList.get(i).getDescriptionOfQuestion())
                        .setCorrectAnswer(questionList.get(i).getCorrect_option())
                        .setUserAnswer(parameters.get(i))
                        .setStatus(Answer.AnswerStatus.CORRECT)
                        .build());
            }else {
                answers.add(new Answer.Builder()
                        .setId(i)
                        .setQuestion(questionList.get(i).getDescriptionOfQuestion())
                        .setCorrectAnswer(questionList.get(i).getCorrect_option())
                        .setUserAnswer(parameters.get(i))
                        .setStatus(Answer.AnswerStatus.INCORRECT)
                        .build());
            }
        }
        return answers;
    }

    public int getRightAnswers(List<Answer> answers) {
        return (int)answers.stream().filter(c -> (c.getStatus().equals(Answer.AnswerStatus.CORRECT))).count();
    }
}
