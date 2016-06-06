/*
 *   Author: Alex Frederico Ramos Barboza <alex.barboza@usp.br>
 *   Author: Bruno Stefano <bruno.stefano@usp.br>
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 */
package Model;

import java.util.Stack;

public class Buffer {
    /*the singleton instance of Buffer used by threads Producer and Consumer*/
    private static Buffer instanceOfBuffer;
    /*store the produced and to-be-consumed-items. The buffer has a stack behavior*/
    private static Stack bufferItems;
    
    private Buffer() {        
        bufferItems = new Stack();
    }
    
    /**
     * Consume an item from the top of the buffer.<p>
     * @return item the item at the top of buffer which will be consumed.
     * @throws BufferUnderflowException at attempt of taking from an empty buffer
     */
    public int consumeItem() throws BufferUnderflowException {
        if (bufferItems.empty()) {
            throw new BufferUnderflowException();
        }
        return (int) Buffer.bufferItems.pop();
    }
    
    /**
     * Randomly choose an integer value and put at the top of the buffer. <p>
     * 
     * @return void
     * @throws BufferOverflowException 
     */
    public void produceItem(int newItem) throws BufferOverflowException {
        if (bufferItems.size() > Consts.MAX_SIZE_BUFFER) {
            throw new BufferOverflowException();
        }
        Buffer.bufferItems.push(newItem);
    }

    /**
     * Gets the number of element at stack <p>
     * 
     * @return the size of stack of the buffer of items
     */
    public int getBufferSize() {
        return bufferItems.size();
    }
    
    /**
     * This function simply clears the buffer (stack). <p>
     * This is done by taking out all objects from the stack of items (buffer)
     * @see Scheduler.pushBufferFrame()
     * @return void
     */
    public void makeBufferEmpty() {
        while (!bufferItems.empty()) {
            bufferItems.pop();
        }
    }
    
    /**
     * Return the singleton instance of buffer. <p>
     * 
     * @return instanceOfBuffer the singleton instance of buffer
     * @see Singleton Design Pattern
     */
    public static Buffer getInstance() {
        if (instanceOfBuffer == null) {
            instanceOfBuffer = new Buffer();
        }
        return instanceOfBuffer;
    }
    
    /**
     * Gets the index-th element from the buffer. <p>
     * @param index the index of the element to be peek. <p>
     * @return the element at index-th position
     */
    public int get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= Consts.MAX_SIZE_BUFFER) {
            throw new IndexOutOfBoundsException();
        }
        
        return ((int) Buffer.bufferItems.get(index));
    }
}
