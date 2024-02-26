package com.example.m7_quiz_fragments.quiz

object QuizStorage {
    val questions = mutableListOf<QuestionForQuiz>()
    init{
        questions.add(
            QuestionForQuiz(
            "Где архив по данному уроку?",
            "Забыли подготовить программу по android-разработке!",
            "Съела корова!",
            "Ты их не видишь, а они есть! Как суслик в ДМБ."
            )
        )
        questions.add(QuestionForQuiz(
            "Кто должен судить GB за такую программу по android-разработке?",
            "Хорошо составленный контракт позволяет творить такую дичь!",
            "Бог им судья!",
            "Судья ДРЕД!"
        ))
        questions.add(QuestionForQuiz(
            "Как пройти собеседование после такого курса по android-разработке?",
            "С божей помощью!",
            "Никак!",
            "А зачем его проходить?\nС дипломом от GB с руками оторвут и без собеса!"
        ))
    }
}