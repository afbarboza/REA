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
public class Status {
    /*the next instruction to be executed*/
    private int stackPointer;
    /*the randomly integer produced*/
    private int item;
    /*the current status of the thread*/
    private int status;
    /*who owns this status? producer or consumer?*/
    private RunnableThread ownerThread;
    
    public Status(int stackPointer, int producedItem, int status) {
        this.stackPointer = stackPointer;
        this.item = producedItem;
        this.status = status;
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
}
