/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * @author Alex Frederico Ramos Barboza
 * @description This class represent the status of a thread
 */
public class ThreadContext {
    /*the next instruction to be executed*/
    private int stackPointer;
    /*the randomly integer produced*/
    private int item;
    /*the current status of the thread*/
    private int status;
    /*who owns this context? producer or consumer?*/
    private RunnableThread ownerThread;
    /*the current size of the buffer*/
    private int sizeOfBuffer;
    
    
    public ThreadContext(int stackPointer, int producedItem, int status, RunnableThread ownerThread) {
        this.stackPointer = stackPointer;
        this.item = producedItem;
        this.status = status;
        this.ownerThread = ownerThread;
        this.sizeOfBuffer = Buffer.getInstance().getBufferSize();
    }
        
    public int getStackPointer() {
        return stackPointer;
    }

    public int getProducedItem() {
        return item;
    }

    public int getStatus() {
        return status;
    }
        
    public void setStatus(int newStatus) {
        this.status = newStatus;
    }
    
    public int readRegisterBufferSize() {
        return this.sizeOfBuffer;
    }
}
