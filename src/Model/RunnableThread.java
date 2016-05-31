/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author alex
 */
public abstract class RunnableThread {
    /*the current context of the thread*/
    protected ThreadContext currentContext;
    /*the buffer owned by the thread*/
    protected Buffer bufferOfItems;
    /*this thread is the active thread?*/
    protected boolean activeThread;
    
    public RunnableThread() {
        /*constructor code goes here*/
    }
    
    public ThreadContext getCurrentContext() {
        return currentContext;
    }

    /**
     * Sets the new context of the thread.
     * (i.e.: the item, the stack pointer and its status) <p>
     * @param currentContext the new context of the thread
     * @return void
     */
    public void setCurrentContext(ThreadContext currentContext) {
        this.currentContext = currentContext;
    }
    
    /**
     * Simulate the action to be taken by the thread when the next line is executed.
     * @return void
     */
    public abstract void executeNextInstruction();
    
}
