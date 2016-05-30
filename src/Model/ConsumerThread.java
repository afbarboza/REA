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
public class ConsumerThread extends RunnableThread {
    private static ConsumerThread instanceOfConsumerThread;
    
    private ConsumerThread() {
        super();
    }
    
    public ConsumerThread getInstanceConsumerThread() {
        if (instanceOfConsumerThread == null)
            instanceOfConsumerThread = new ConsumerThread();
        return instanceOfConsumerThread;
    }
}
