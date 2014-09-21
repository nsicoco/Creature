package com.unhackathon.creature.einnuj_quizzler;

/**
 * A class that checks math queries against given answers
 *
 * Created by einnuj on 9/20/2014.
 */
public class mathSolver {
    /* Data Fields */

    // Hold for later use the mathematical function to solve
    public mathQuiz mathQuiz;

    // Hold the answer
    public byte solution;

    /* Constructor */
    public mathSolver(mathQuiz mathQuiz) {
        this.mathQuiz = mathQuiz;
    }

    /* Data Manipulation */

    /**
     * A simple method designed to iterate through the mathQuiz's data fields and calculate the
     * appropriate answer. Will also complete the initialization of mathSolver by setting its
     * field for 'solution'.
     */
    public void solve() {
        // This value will always be initialized, and should always be held.
        byte b = mathQuiz.values[0];

        // Iterate through all until you hit the second to last - we can stop here because we're
        // manipulating the value that is **one ahead of our index**.
        for (int i = 0; i < mathQuiz.values.length-1; i++) {
            // Copy the first set of operations
            char mander = mathQuiz.operations[i];
            // Find the operation, complete it with the values.
            switch (mander) {
                case '+':   b += mathQuiz.values[i+1];
                    break;
                case '-':   b -= mathQuiz.values[i+1];
                    break;
                case 'x':   b *= mathQuiz.values[i+1];
                    break;
                case '/':   b /= mathQuiz.values[i+1];
                    break;
                default:    b = 0;
                    break;
            }
        }
        solution = b;
    }
}
