package com.example.sit305quizapp;

public class Question {
    public String questions[] = {
            "Android Studio is based on the IntelliJ IDEA, a _______ integrated development environment for software.",
            "Apps provide ____ entry points",
            "Apps adapt to ____ devices",
            "The ____ class is a crucial component of an Android app, and the way activities are launched and put together is a fundamental part of the platform's application model.",
            "An ____ is a messaging object you can use to request an action from another app component."

    };

    public String choices[][] = {
            {"C", "C++", "JAVA"},
            {"multiple", "single", "both are correct"},
            {"physical", "virtual", "different"},
            {"Main","Activity","you can name by yourself"},
            {"Intent","pass","turn"}
    };

    public String correctAnswer[] = {
            "JAVA",
            "multiple",
            "different",
            "Activity",
            "Intent",
    };

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getchoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getchoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getchoice3(int a){
        String choice = choices[a][2];
        return choice;
    }


    public String getCorrectAnswer(int a){
        String answer = correctAnswer[a];
        return answer;
    }

}
