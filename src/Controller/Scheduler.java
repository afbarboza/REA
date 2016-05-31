/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.util.Stack;

/**
 *
 * @author alex
 */
public class Scheduler {

    /*the singleton instance of this class*/
    private static Scheduler sched = null;
    /*this stack stores the consecutive contexts of threads*/
    public Stack programStatus = null;
    /*the single thread producer*/
    private ProducerThread pt = null;
    /*the single thread thread consumer*/
    private ConsumerThread ct = null;

    private Scheduler() {
        programStatus = new Stack();
        pt = ProducerThread.getInstance();
        ct = ConsumerThread.getInstance();
    }

    /**
     * Makes the context switching between threads.
     * <p>
     * This function deactivates the current thread.
     *
     * @param codeOfExecution the code of what is next step to be taken in the
     * program
     * @return void
     */
    public void doContextSwitch(int codeOfExecution) {
        /*deactivates the current thread*/
        deactivatesCurrentThread();
        /*selects the next/last thread to be executed/retrieved*/
        switch (codeOfExecution) {
            case (Consts.EXECUTE_NEXT_PRODUCER):
                pt.executeNextInstruction();
                break;
            case (Consts.EXECUTE_NEXT_CONSUMER):
                ct.executeNextInstruction();
                break;
            case (Consts.EXECUTE_BACK):
                backTrackInstructions();
                break;
        }
    }

    /**
     * Backtrack the program through back the first instruction.
     * <p>
     *
     * The function checks the owner of the top stack of status (programStatus)
     * .<p>
     * Then the stack is popped until the popped element is different from the
     * recored ancient top of the stack.
     *
     * @return void
     */
    private void backTrackInstructions() {
    }

    /**
     * Gets the single instance of Scheduler.<p>
     *
     * @return the singleton instance of Scheduler
     */
    public static Scheduler getInstance() {
        if (sched == null) {
            sched = new Scheduler();
        }
        return sched;
    }

    /**
     * This function deactivates the current executing thread.<p>
     *
     * @see doContextSwitching
     * @return void
     */
    private void deactivatesCurrentThread() {
        programStatus.push(pt.getCurrentContext());
        programStatus.push(ct.getCurrentContext());
    }
}
