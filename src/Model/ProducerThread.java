/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
        if (instanceOfProducerThread == null)
            instanceOfProducerThread = new ProducerThread();
        return instanceOfProducerThread;
    }
    
    /**
     * Execute the next instruction of thread Producer. <p>
     * 
     * @return void
     */
    @Override
    public final void executeNextInstruction() {
        ThreadContext tc = null;
        int nextInstruction = (this.currentContext.getStackPointer() + 1) % 10;
        if (nextInstruction == -2 || nextInstruction == -1) {
            tc = new ThreadContext(nextInstruction, -1, Consts.STATUS_THREAD_EXECUTING, this);
            
        }  else if (nextInstruction == 1) {
            tc = new ThreadContext(nextInstruction, -1, Consts.STATUS_THREAD_EXECUTING, this);
            
        }  else if (nextInstruction == 2) {
            tc = new ThreadContext(nextInstruction, -1, Consts.STATUS_THREAD_EXECUTING, this);
            
        }  else if (nextInstruction == 3) {
            int newItem = doProduceItem();
            tc = new ThreadContext(nextInstruction, newItem, Consts.STATUS_THREAD_EXECUTING, this);
            
        }  else if (nextInstruction == 4) {
            tc = new ThreadContext(nextInstruction, this.currentContext.getProducedItem(), Consts.STATUS_THREAD_EXECUTING, this);
            if (this.bufferOfItems.getBufferSize() != Consts.MAX_SIZE_BUFFER)
                nextInstruction++;
        }  else if (nextInstruction == 5) {
            tc = new ThreadContext(nextInstruction, this.currentContext.getProducedItem(), Consts.STATUS_THREAD_BLOCKED, this);
            
        }  else if (nextInstruction == 6) {
            try {
                this.bufferOfItems.produceItem(this.currentContext.getProducedItem());
            } catch (BufferOverflowException ex) {
                Logger.getLogger(ProducerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }  else if (nextInstruction == 7) {
            
        }  else if (nextInstruction == 8) {
            
        }  else if (nextInstruction == 9) {
            
        }
    }
    
    /**
     * Selects a new value to be put at buffer. <p> 
     * 
     * @return the ramdomly choosed value
     */
    private int doProduceItem() {
        return (int) (Math.random() * 100.0);
    }
}
