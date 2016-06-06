/*
 *   Author: Alex Frederico Ramos Barboza <alex.barboza@usp.br>
 *   Author: Bruno Stefano <bruno.stefano@usp.br>
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 */
package Controller;

import Model.*;
import View.MainFrame;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scheduler {

    /*the singleton instance of this class*/
    private static Scheduler sched = null;
    /*this stack stores the consecutive contexts of threads*/
    public Stack programStatus = null;
    /*this stacks saves the set of values of the stack at any given context switching*/
    private Stack bufferRecord = null;
    /*the single thread producer*/
    private ProducerThread pt = null;
    /*the single thread thread consumer*/
    private ConsumerThread ct = null;

    private Scheduler() {
        programStatus = new Stack();
        bufferRecord = new Stack();
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
        /*selects the next/last thread to be executed/retrieved*/
        switch (codeOfExecution) {
            case (Consts.EXECUTE_NEXT_PRODUCER):
                /*saves the current status of buffer*/
                pushBufferFrame();
                /*deactivates the current thread*/
                deactivatesCurrentThread();
                /*executes the next step of producer*/
                pt.executeNextInstruction();
                break;
            case (Consts.EXECUTE_NEXT_CONSUMER):
                /*saves the current status of buffer*/
                pushBufferFrame();
                /*deactivates the current thread*/
                deactivatesCurrentThread();
                /*executes the next step of consumer*/
                ct.executeNextInstruction();
                break;
            case (Consts.EXECUTE_BACK):
                /*restores the old status of buffer*/
                popBufferFrame();
                /*take back one step in program step*/
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
     * recored old top of the stack.
     *
     * @return void
     */
    private void backTrackInstructions() {
        if (programStatus == null || programStatus.empty()) {
            return;
        }

        ThreadContext oldContextConsumer = (ThreadContext) this.programStatus.pop();
        this.ct.setCurrentContext(oldContextConsumer);
        
        if (oldContextConsumer.getStatus() == Consts.STATUS_THREAD_EXECUTING ||
            oldContextConsumer.getStatus() == Consts.STATUS_THREAD_READY_TO_EXEC) {
            MainFrame.enableSchedulingConsumer();
        } else if (oldContextConsumer.getStatus() == Consts.STATUS_THREAD_BLOCKED) {
            MainFrame.unableSchedulingConsumer();
        }
        
        ThreadContext oldContextProducer = (ThreadContext) this.programStatus.pop();
        this.pt.setCurrentContext(oldContextProducer);
        
        if (oldContextProducer.getStatus() == Consts.STATUS_THREAD_EXECUTING ||
            oldContextProducer.getStatus() == Consts.STATUS_THREAD_READY_TO_EXEC) {
            MainFrame.enableSchedulingConsumer();
        }  else if (oldContextProducer.getStatus() == Consts.STATUS_THREAD_BLOCKED) {
            MainFrame.unableSchedulingProducer();
        }
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
    
    /**
     * This function saves the current status of buffer of items in a stack.<p>
     * @see popBufferFrame
     * @return void
     */
    private void pushBufferFrame() {
        Buffer b = Buffer.getInstance();
        /*saving the n, n-1, ..., 2, 1, 0 element of buffer on buffer frame*/
        for (int i  = (b.getBufferSize() - 1); i >= 0; i--) {
            bufferRecord.push(b.get(i));
        }
        /*saving the size of buffer on buffer frame*/
        bufferRecord.push(b.getBufferSize());
    }
    
    /**
     * This function retrieves an old status of buffer of items from a stack.<p>
     * @see pushBufferFrame
     * @return void
     */
    private void popBufferFrame() {
        if (bufferRecord == null || bufferRecord.empty()) {
            return;
        }
        
        Buffer b = Buffer.getInstance();
        int oldBufferSize = 0;
        /*first, we must clear the current buffer*/
        b.makeBufferEmpty();
        /*now, we must check the old size of buffer*/
        oldBufferSize = (int) bufferRecord.pop();
        /*finally, the old status of buffer can be restored*/
        for (int i = 0; i < oldBufferSize; i++) {
            try {
                b.produceItem((int) bufferRecord.pop());
            } catch (BufferOverflowException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        }
    }
    
    public boolean checkDeadlockStatus() {
        boolean lockedProducer = (pt.getCurrentContext().getStatus() == Consts.STATUS_THREAD_BLOCKED);
        boolean lockedConsumer = (ct.getCurrentContext().getStatus() == Consts.STATUS_THREAD_BLOCKED);
        
        if (lockedProducer && lockedConsumer) {
            return true;
        }
        return false;
    }
    
    public static void restart() {
        
    }
}
