package edu.washington.dubeh.quizdroid;

/**
 * Created by icazadori on 2/14/2017.
 */

public class TopicRepository {
    private Topic topic;
    private String[] categories = {"Math", "Physics", "Marvel Super Heroes"};
    private String[] shortDescs = {"These are math questions", "These are physics questions", "These are Marvel questions"};

    public TopicRepository() {

    }

    public void createTopic(String chosenTopic) {
        String[] questions;
        String shortDesc;
        String longDesc;
        String[][] possibleAnswers;
        int[] answers;
        if(chosenTopic.equals("Math")) {
            questions = new String[]{"Math Question 1", "Math Question 2",
                    "Math Question 3", "Math Question 4"};
            shortDesc = "These are math questions";
            longDesc = "These questions will test your mathematical abilities";
        } else if(chosenTopic.equals("Physics")) {
            questions = new String[]{"Physics Question 1", "Physics Question 2",
                    "Physics Question 3", "Physics Question 4"};
            shortDesc = "These are physics questions";
            longDesc = "These questions will test your knowledge and understanding of physics";
        } else {
            questions = new String[]{"Marvel Question 1", "Marvel Question 2",
                    "Marvel Question 3", "Marvel Question 4"};
            shortDesc = "These are Marvel questions";
            longDesc = "These questions will test your knowledge of the Marvel universe";
        }
        possibleAnswers = new String[][]{{"answer 1", "answer 2", "answer 3", "answer 4"},
                {"answer 1", "answer 2", "answer 3", "answer 4"},
                {"answer 1", "answer 2", "answer 3", "answer 4"},
                {"answer 1", "answer 2", "answer 3", "answer 4"}};
        answers = new int[]{0, 1, 3, 2};
        topic = new Topic(chosenTopic, shortDesc, longDesc);
        for(int i = 0; i < questions.length; i++) {
            Question question = new Question(questions[i], possibleAnswers[i], answers[i]);
            topic.addQuestion(question);
        }
    }

    public Topic getTopic() {
        return topic;
    }

    public String[] getCategories() {
        return categories;
    }

    public String[] getShortDescs() {
        return shortDescs;
    }
}
