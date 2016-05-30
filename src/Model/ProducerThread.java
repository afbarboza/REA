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
public class ProducerThread extends RunnableThread {
    public static ProducerThread instanceOfProducerThread;
    
    private ProducerThread() {
        super();
    }
    
    public ProducerThread getInstanceProducerThread() {
        if (instanceOfProducerThread == null)
            instanceOfProducerThread = new ProducerThread();
        return instanceOfProducerThread;
    }
}
