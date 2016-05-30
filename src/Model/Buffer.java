/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Stack;

/**
 *
 * @author alex
 */
public class Buffer {
    private static Buffer instanceOfBuffer;
    private static Stack bufferItems;
    private static int index;
    
    private Buffer() {        
        bufferItems = new Stack();
        index = 0;
    }
    
    public int consumeItem() throws BufferUnderflowException {
        if (bufferItems.empty()) {
            throw new BufferUnderflowException();
        }
        return (int) Buffer.bufferItems.pop();
    }
    
    public void produceItem() throws BufferOverflowException {
        if (bufferItems.size() > 5) {
            throw new BufferOverflowException();
        }
        int newItem = (int) (Math.random() * 100.0f);
        Buffer.bufferItems.push(newItem);
    }
    
    public static Buffer getInstance() {
        if (instanceOfBuffer == null) {
            instanceOfBuffer = new Buffer();
        }
        return instanceOfBuffer;
    }
}
