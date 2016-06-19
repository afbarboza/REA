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
package Model;

import Controller.Scheduler;
import View.MainFrame;
import View.MessageLostWakeupProducer;
import View.MessageWakeupConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumerThread extends RunnableThread {

    private static ConsumerThread instanceOfConsumerThread = null;

    /*counts the number of attemps of trying scheduling a sleeping consumer*/
    private static int attemptSchedSleepingConsumer;

    private ConsumerThread() {
        super();
    }

    public static ConsumerThread getInstance() {
        if (instanceOfConsumerThread == null) {
            instanceOfConsumerThread = new ConsumerThread();
        }
        attemptSchedSleepingConsumer = 0;
        return instanceOfConsumerThread;
    }

    /**
     * Execute the next instruction of thread Consumer.
     * <p>
     *
     * @return void
     */
    @Override
    public final void executeNextInstruction() {
        int nextStackPointer = 0;
        /*handling code outside the infinite loop of producer*/
        if (this.currentContext.getStackPointer() < 0) {
            executeInitialLines();
            return;
        }
        /*handling code inside the loop*/
        nextStackPointer = (this.currentContext.getStackPointer());
        switch (nextStackPointer) {
            case 0:
                /*line 2: "while(true)" */
                executeLine2(currentContext);
                break;
            case 1:
                /*line 3: "if(count == 0)" */
                executeLine3(currentContext);
                break;
            case 2:
                /*line 4: "sleep()" */
                executeLine4(currentContext);
                break;
            case 3:
                /*line 5: "item = remove_item()" */
                executeLine5(currentContext);
                break;
            case 4:
                /*line 6: "count--" */
                executeLine6(currentContext);
                break;
            case 5:
                /*line 7: "if(count == N-1)" */
                executeLine7(currentContext);
                break;
            case 6:
                /*line 8: "wakeup(producer)" */
                executeLine8(currentContext);
                break;
            case 7:
                /*line9: "consume_item(item)" */
                executeLine9(currentContext);
                break;
            default:
                break;
        }
    }

    /**
     * Consume (print) the item whick has been taken from the buffer by thread
     * Consumer.
     * <p>
     * @return void
     */
    public void callConsumeItem(int item) {
        
    }

    /**
     * Execute line 1 or line 2 of source code from Thread Consumer
     *
     * @return void
     * @see executeNextInstruction
     */
    private void executeInitialLines() {
        ThreadContext newContext = null;
        int nextStackPointer = 0;
        nextStackPointer = this.currentContext.getStackPointer() + 1;
        newContext = new ThreadContext(nextStackPointer, -1, Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
        return;
    }

    /**
     * Execute line 2 of source code from Thread Consumer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine2(ThreadContext currentContext) {
        ThreadContext newContext = null;
        ThreadContext oldContext = this.currentContext;
        newContext = new ThreadContext(1, oldContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
    }

    /**
     * Execute line 3 of source code from Thread Consumer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine3(ThreadContext currentContext) {
        ThreadContext newContext = null;
        ThreadContext oldContext = this.getCurrentContext();
        int nextStackPointer = 0;

        boolean bufferIsEmpty = (this.bufferOfItems.getBufferSize() == 0);
        //boolean bufferIsEmpty = (oldContext.readRegisterBufferSize() == 0);

        /**
         * if the buffer is not empty, the first instruction after "sleep()"
         * must be executed. otherwise, Consumer must call "sleep()" and will be
         * blocked
         */
        if (bufferIsEmpty) {
            nextStackPointer = 2;
            newContext = new ThreadContext(nextStackPointer, oldContext.getProducedItem(), Consts.STATUS_THREAD_GOING_BLOCK, this);
        } else {
            nextStackPointer = 3;
            newContext = new ThreadContext(nextStackPointer, oldContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        }

        this.currentContext = newContext;
    }

    /**
     * Execute line 4 of source code from Thread Consumer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine4(ThreadContext currentContext) {
        ThreadContext newContext = null;
        boolean stillSleeping = (this.getCurrentContext().getStatus() == Consts.STATUS_THREAD_BLOCKED);
        boolean isGoingSleep = (this.getCurrentContext().getStatus() == Consts.STATUS_THREAD_GOING_BLOCK);
        /*boolean bufferStillEmpty = (this.bufferOfItems.getBufferSize() == 0); */

        /**
         * checks whether this thread is still blocked (sleeping) or if the
         * other thread woke up this thread or the buffer still is empty
         */
        if (stillSleeping || isGoingSleep) {
            newContext = new ThreadContext(2, currentContext.getProducedItem(), Consts.STATUS_THREAD_BLOCKED, this);
            MainFrame.unableSchedulingConsumer();
        } else {
            newContext = new ThreadContext(3, currentContext.getProducedItem(), Consts.STATUS_THREAD_READY_TO_EXEC, this);

        }
        this.currentContext = newContext;
    }

    /**
     * Execute line 5 of source code from Thread Consumer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine5(ThreadContext currentContext) {
        try {
            ThreadContext newContext = null;
            int removedItem = this.bufferOfItems.consumeItem();
            newContext = new ThreadContext(4, removedItem, Consts.STATUS_THREAD_EXECUTING, this);
            this.currentContext = newContext;
        } catch (BufferUnderflowException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Execute line 6 of source code from Thread Consumer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine6(ThreadContext currentContext) {
        ThreadContext newContext = null;
        newContext = new ThreadContext(5, currentContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
    }

    /**
     * Execute line 7 of source code from Thread Consumer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine7(ThreadContext currentContext) {
        ThreadContext newContext = null;
        int nextStackPointer = 6;
        boolean bufferWasFull = (this.bufferOfItems.getBufferSize() == (Consts.MAX_SIZE_BUFFER - 1));
        /**
         * if buffer was full, then the thread Consumer must wakeup the thread
         * Producer. The wakeup is done by allowing the execution of function
         * executeLine8
         */
        if (!bufferWasFull) {
            nextStackPointer = 7;
        }
        newContext = new ThreadContext(nextStackPointer, currentContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
    }

    /**
     * Execute line 8 of source code from Thread Consumer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine8(ThreadContext currentContext) {
        ThreadContext newContext = null;
        ProducerThread.getInstance().wakeupProducer();
        newContext = new ThreadContext(7, this.getCurrentContext().getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
    }

    /**
     * Execute line 9 of source code from Thread Consumer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine9(ThreadContext currentContext) {
        ThreadContext newContext = null;
        callConsumeItem(this.getCurrentContext().getProducedItem());
        newContext = new ThreadContext(0, this.getCurrentContext().getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
    }

    /**
     * This function wakeup the thread Consumer.
     * <p>
     * This is done by recording the current status on stack (of scheduler) and
     * creating a new status for this thread, with the value
     * Conts.STATUS_THREAD_READY_TO_EXEC
     * <p>
     * @return void
     */
    public void wakeupConsumer() {
        ThreadContext myContext = this.getCurrentContext();
        int idiom = MainFrame.getInstanceOfMainFrame().getLanguage();
        /*makes MainFrame invisible*/
        MainFrame.getInstanceOfMainFrame().setEnabled(false);

        /*The thread can be blocked if, and only if, was sleeping (blocked)*/
        if (myContext.getStatus() == Consts.STATUS_THREAD_BLOCKED) {
            if (idiom == 0) {   /*shwo the wakeup message to the user*/
                MessageWakeupConsumer.showPortugueseInstance();
            } else {
                MessageWakeupConsumer.showEnglishInstance();
            }

            Scheduler.getInstance().programStatus.push(myContext);
            ThreadContext newContext = new ThreadContext(myContext.getStackPointer(), myContext.getProducedItem(), Consts.STATUS_THREAD_READY_TO_EXEC, this);
            this.currentContext = newContext;
            MainFrame.enableSchedulingConsumer();
        } else { /*show the lost signal of wakeup*/
            if (idiom == 0) {
                MessageLostWakeupProducer.showPortugueseInstance();
            } else {
                MessageLostWakeupProducer.showEnglishInstance();
            }
        } 
    }
}
