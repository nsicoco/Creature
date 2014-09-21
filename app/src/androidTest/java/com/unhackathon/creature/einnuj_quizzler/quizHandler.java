package com.unhackathon.creature.einnuj_quizzler;

/**
 * The mastermind class in charge of doling out elementary trivia questions.
 *
 * Created by einnuj on 9/20/2014.
 */
public class quizHandler {
    /* Data Fields */

    // Variable to hold math quiz, solver
    public mathQuiz mathQuiz;
    public mathSolver mathSolver;

    /* Constructor */
    public quizHandler() {
        this.mathQuiz = new mathQuiz();
        initHandler();
    }

    /* Initializers */

    /**
     * Initializes the mathQuiz with the appropriately randomized variables, including values and
     * operations.
     */
    public void initHandler() {
        // Load mathQuiz with a random amount (<=5) of random variables (<=100)
        mathQuiz.values = new byte[((int) Math.random() * 5) + 2];
        for (int i = 0; i < mathQuiz.values.length; i++) {
            mathQuiz.values[i] = (byte) (Math.random() * 100);
        }

        // Load mathQuiz with a matched amount of operations for mathQuiz values.
        mathQuiz.operations = new char[mathQuiz.values.length - 1];

        // Iterates through and sets each to a randomly generated operation between +, -, x, /
        /**
         * @TODO: VERY STRANGE BUG HERE. Somehow, a number is bleeding into our char[] additions.
         */
        for (int i = 0; i < mathQuiz.operations.length; i++) {
            if (Math.random() <= 0.5) {
                if (Math.random() <= 0.5) {
                    mathQuiz.operations[i] = '+';
                }
                else {
                    mathQuiz.operations[i] = '-';
                }
            }
            else {
                if (Math.random() <= 0.5) {
                    mathQuiz.operations[i] = 'x';
                }
                else {
                    mathQuiz.operations[i] = '/';
                }
            }
        }

        // Initializes mathSolver
        mathSolver = new mathSolver(mathQuiz);
        mathSolver.solve();
    }

    /* Data Manipulation */

    /**
     * A method that routes the answer to the appropriate method pending in/correct answer
     *
     * @param answer    the value given by the end-user as the answer to our quiz
     */
    public void checkAnswer(byte answer) {
        if (answer == mathSolver.solution) {
            correctAnswer();
        }
        else {
            incorrectAnswer();
        }
    }

    /**
     * This method provides the response for a correct answer; adding experience.
     */
    public void correctAnswer() {
        int expGain = (int) (mathQuiz.values.length * 100 * ((Math.random() * 2) + 1));
        // @TODO: pass expGain to our eventHandler -> pass expGain to statsManager's gainExperience
    }

    /**
     * This method provides the response for an incorrect answer: nothing yet.
     */
    public void incorrectAnswer() {
        // @TODO: what do we do on an incorrect answer?
    }

    /**
     * A method that returns a textual representation of the math prompt
     *
     * @return  the string rep'n of the prompt
     */
    @Override
    public String toString() {
        String s = "What is " + mathQuiz.values[0];
        for (int i = 0; i < mathQuiz.values.length-1; i++) {
            s += " " + mathQuiz.operations[i] + " " + mathQuiz.values[i+1];
        }
        s += "?";
        return s;
    }
}