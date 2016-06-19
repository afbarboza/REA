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
    
    /**
     * Each ThreadContext simulates the context (resources and status) of a given thread.<p>
     * 
     * @param stackPointer the next instruction to be executed.
     * @param producedItem the item randomly integer produced/consumed
     * @param status the current status of the thread - ready, running, blocked or to be blocked.
     * @param ownerThread who have this conext.
     */
    public ThreadContext(int stackPointer, int producedItem, int status, RunnableThread ownerThread) {
        this.stackPointer = stackPointer;
        this.item = producedItem;
        this.status = status;
        this.ownerThread = ownerThread;
        this.sizeOfBuffer = Buffer.getInstance().getBufferSize();
    }
    
    /**
     * Gets the next instruction to be fetched.
     * @return the next instruction to be fetched
     */
    public int getStackPointer() {
        return stackPointer;
    }

    /**
     * Gets the item to be produced or consumed.
     * @return the item to be produced or consumed.
     */
    public int getProducedItem() {
        return item;
    }

    /**
     * Get the current status of the thread.
     * 
     * @return  STATUS_THREAD_EXECUTING, if thread is running or schedulable.
     *          STATUS_THREAD_BLOCKED, if thread cannot be scheduled (must wake up before).
     *          STATUS_THREAD_READY_TO_EXEC, if thread is schedulable
     *          STATUS_THREAD_GOING_BLOCK, if thread will be blocked in its next instruction.
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * Set the status f the thread.
     * @param   newStatus, with one of the follwing values:
     *          STATUS_THREAD_EXECUTING, if thread is running or schedulable.
     *          STATUS_THREAD_BLOCKED, if thread cannot be scheduled (must wake up before).
     *          STATUS_THREAD_READY_TO_EXEC, if thread is schedulable
     *          STATUS_THREAD_GOING_BLOCK, if thread will be blocked in its next instruction.
     */
    public void setStatus(int newStatus) {
        this.status = newStatus;
    }
    
    /**
     * Gets the current size of buffer
     * @return the current size of buffer
     */
    public int readRegisterBufferSize() {
        return this.sizeOfBuffer;
    }
}
