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
