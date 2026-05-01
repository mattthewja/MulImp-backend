package personal.mattthewja.mulimp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionPair {
    private String q1;
    private String q2;

    public QuestionPair swap() {
        String q2_temp = q2;
        String q2 = q1;
        String q1 = q2_temp;
        return this;
    }

    public QuestionPair getRandomised() {
        if (Math.random() > 0.5) {
            return this;
        } else {
            return swap();
        }
    }
};
