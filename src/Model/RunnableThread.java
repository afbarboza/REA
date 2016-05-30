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
    protected Status currentStatus;

    public RunnableThread() {
        /*constructor code goes here*/
    }
    
    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }
    
}
