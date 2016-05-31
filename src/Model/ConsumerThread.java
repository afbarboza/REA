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
public class ConsumerThread extends RunnableThread {
    private static ConsumerThread instanceOfConsumerThread = null;
    
    private ConsumerThread() {
        super();
    }
    
    public static ConsumerThread getInstance() {
        if (instanceOfConsumerThread == null)
            instanceOfConsumerThread = new ConsumerThread();
        return instanceOfConsumerThread;
    }
    
    /**
     * Execute the next instruction of thread Consumer. <p>
     * 
     * @return void
     */
    @Override
    public final void executeNextInstruction() {
        
    }
    
    public void doConsumeItem() {
        try {
            this.bufferOfItems.consumeItem();
        } catch (BufferUnderflowException ex) {
           
        }
    }
    
    /**
     * This function wakeup the thread Consumer. <p>
     * This is done by recording the current status on stack (of scheduler) and
     * creating a new status for this thread, with the value Conts.STATUS_THREAD_READY_TO_EXEC <p>
     * @return void
     */
    public void wakeupConsumer() {
        ThreadContext myContext = this.getCurrentContext();
        Scheduler.getInstance().programStatus.push(myContext);
        ThreadContext newContext = new ThreadContext(myContext.getStackPointer(), myContext.getProducedItem(), Consts.STATUS_THREAD_READY_TO_EXEC, this);
        this.currentContext = newContext;
    }
}
