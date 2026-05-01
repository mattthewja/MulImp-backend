package personal.mattthewja.mulimp.store;

import org.springframework.stereotype.Repository;
import personal.mattthewja.mulimp.model.QuestionPair;

import java.util.List;

@Repository
public class QuestionStore {
    private final List<QuestionPair> questionPairs = List.of(
            new QuestionPair(
                    "What food could you eat for the rest of your life?",
                    "What was the last meal you ate yesterday?"
            ),
            new QuestionPair(
                    "What song are you playing at your funeral?",
                    "What song would you do a MLB walkout for?"
            ),
            new QuestionPair(
                    "How many days could you survive without your phone?",
                    "How many days "
            ),
            new QuestionPair(
                    "Which country would you live in for the rest of your life?",
                    "Which country would no one notice if it disappears?"
            ),
            new QuestionPair(
                    "If you were to forget the last 5 years of your life, what" +
                            "would be the one sentence you leave yourself before forgetting?",
                    "What would you tell yourself 5 years in the past?"
            )
    );

    public QuestionPair getRandomQuestionPair() {
        QuestionPair pair = questionPairs.get((int) Math.floor(Math.random() * questionPairs.size()));
        return pair.getRandomised();
    }
}
