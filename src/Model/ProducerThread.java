/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Scheduler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
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
        newContext = new ThreadContext(3, oldContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
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
        newContext = new ThreadContext(4, tmp, Consts.STATUS_THREAD_EXECUTING, this);
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
        int nextStackPointer = 5;

        boolean fullBuffer = (this.bufferOfItems.getBufferSize() == Consts.MAX_SIZE_BUFFER);
        /**
         * if buffer is not full, then the thread must keep goes on, otherwise,
         * the next time this thread executes, it goes sleep.
         */
        if (!fullBuffer) {
            nextStackPointer = 6;
        }
        newContext = new ThreadContext(nextStackPointer, oldContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
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
        newContext = new ThreadContext(6, currentContext.getProducedItem(), Consts.STATUS_THREAD_BLOCKED, this);
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
            newContext = new ThreadContext(7, currentContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
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
        newContext = new ThreadContext(8, currentContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
        this.currentContext = newContext;
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
        int newStackPointer = 9;
        
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
}
