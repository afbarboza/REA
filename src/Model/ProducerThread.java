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

import Controller.*;
import View.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerThread extends RunnableThread {

    private static ProducerThread instanceOfProducerThread = null;

    private ProducerThread() {
        super();
    }

    public static ProducerThread getInstance() {
        if (instanceOfProducerThread == null) {
            instanceOfProducerThread = new ProducerThread();
        }
        return instanceOfProducerThread;
    }

    /**
     * Execute the next instruction of thread Producer.
     * <p>
     *
     * @todo Quebrar essas instruções em assembly code.
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
                /*line 3: "item = produce_item()" */
                executeLine3(currentContext);
                break;
            case 2:
                /*line 4: "if (count == n)" */
                executeLine4(currentContext);
                break;
            case 3:
                /*line 5: "sleep()" */
                executeLine5(currentContext);
                break;
            case 4:
                /*line 6: "insert_item()" */
                executeLine6(currentContext);
                break;
            case 5:
                /*line 7: "count++" */
                executeLine7(currentContext);
                break;
            case 6:
                /*line 8: "if (count == 1)" */
                executeLine8(currentContext);
                break;
            case 7:
                /*line9: "wake_up(consumer)" */
                executeLine9(currentContext);
                break;
            default:
                break;
        }
    }

    /**
     * Selects a new value to be put at buffer.
     * <p>
     *
     * @return the ramdomly choosed value
     */
    private int callProduceItem() {
        return (int) (Math.random() * 100.0f);
    }

    /**
     * Execute line 1 or line 2 of source code from Thread Producer
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
     * Execute line 2 of source code from Thread Producer
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
     * Execute line 3 of source code from Thread Producer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine3(ThreadContext currentContext) {
        ThreadContext newContext = null;
        int tmp = callProduceItem();
        newContext = new ThreadContext(2, tmp, Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
    }

    /**
     * Execute line 4 of source code from Thread Producer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine4(ThreadContext currentContext) {
        ThreadContext newContext = null;
        ThreadContext oldContext = this.getCurrentContext();
        int nextStackPointer = 3;

        boolean fullBuffer = (this.bufferOfItems.getBufferSize() == Consts.MAX_SIZE_BUFFER);
        /**
         * if buffer is not full, then the thread must keep going on, otherwise,
         * the next time this thread executes, it goes sleep.
         */
        if (!fullBuffer) {
            nextStackPointer = 4;
            newContext = new ThreadContext(nextStackPointer, oldContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        } else {
            newContext = new ThreadContext(nextStackPointer, oldContext.getProducedItem(), Consts.STATUS_THREAD_GOING_BLOCK, this);
        }
        this.currentContext = newContext;
    }

    /**
     * Execute line 5 of source code from Thread Producer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine5(ThreadContext currentContext) {
        ThreadContext newContext = null;
        boolean stillSleeping = (this.getCurrentContext().getStatus() == Consts.STATUS_THREAD_BLOCKED);
        boolean isGoingSleep = (this.getCurrentContext().getStatus() == Consts.STATUS_THREAD_GOING_BLOCK);
        //boolean bufferStillFull = (this.bufferOfItems.getBufferSize() == Consts.MAX_SIZE_BUFFER);
        /**
         * checks whether this thread is still blocked (sleeping) or if the
         * other thread woke up this thread or whether the buffer still is full
         */
        if (stillSleeping || isGoingSleep) {
            newContext = new ThreadContext(3, currentContext.getProducedItem(), Consts.STATUS_THREAD_BLOCKED, this);
        } else {
            newContext = new ThreadContext(4, currentContext.getProducedItem(), Consts.STATUS_THREAD_READY_TO_EXEC, this);
        }
        this.currentContext = newContext;
    }

    /**
     * Execute line 6 of source code from Thread Producer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine6(ThreadContext currentContext) {
        try {
            ThreadContext newContext = null;
            this.bufferOfItems.produceItem(currentContext.getProducedItem());
            newContext = new ThreadContext(5, currentContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
            this.currentContext = newContext;
        } catch (BufferOverflowException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Execute line 7 of source code from Thread Producer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine7(ThreadContext currentContext) {
        ThreadContext newContext = null;
        newContext = new ThreadContext(6, currentContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
    }

    /**
     * Execute line 8 of source code from Thread Producer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine8(ThreadContext currentContext) {
        ThreadContext newContext = null;
        int newStackPointer = 7;

        boolean bufferWasEmpty = (this.bufferOfItems.getBufferSize() == 1);
        if (!bufferWasEmpty) {
            newStackPointer = 0;
        }
        newContext = new ThreadContext(newStackPointer, currentContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
    }

    /**
     * Execute line 9 of source code from Thread Producer
     *
     * @param currentContext the current context of the thread which will
     * advance one step further.
     */
    private void executeLine9(ThreadContext currentContext) {
        ThreadContext newContext = null;
        ConsumerThread.getInstance().wakeupConsumer();
        newContext = new ThreadContext(0, currentContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
    }

    /**
     * This function wakeup the thread Producer.
     * <p>
     * This is done by recording the current status on stack (of scheduler) and
     * creating a new status for this thread, with the value
     * Conts.STATUS_THREAD_READY_TO_EXEC
     * <p>
     * @return void
     */
    public void wakeupProducer() {
        ThreadContext myContext = this.getCurrentContext();
        int idiom = MainFrame.getInstanceOfMainFrame().getLanguage();
        MainFrame.getInstanceOfMainFrame().setEnabled(false);
        
        if (myContext.getStatus() == Consts.STATUS_THREAD_BLOCKED) {
            if (idiom == 0) {
                /*shwo the wakeup message to the user*/
                MessageWakeupProducer.showPortugueseInstance();
            } else {
                MessageWakeupProducer.showEnglishInstance();
            }

            Scheduler.getInstance().programStatus.push(myContext);
            ThreadContext newContext = new ThreadContext(myContext.getStackPointer(), myContext.getProducedItem(), Consts.STATUS_THREAD_READY_TO_EXEC, this);
            MainFrame.enableSchedulingProducer();
            this.currentContext = newContext;
        } else { /*show the lost signal of wakeup*/
            if (idiom == 0) {
                MessageLostWakeupProducer.showPortugueseInstance();
            } else {
                MessageLostWakeupProducer.showEnglishInstance();
            }
        }
    }
}
