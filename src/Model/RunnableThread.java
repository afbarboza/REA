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

public abstract class RunnableThread {
    /*the current context of the thread*/
    protected ThreadContext currentContext;
    /*the buffer owned by the thread*/
    protected Buffer bufferOfItems;
    /*this thread is the active thread?*/
    protected boolean activeThread;
    
    public RunnableThread() {
        this.currentContext = new ThreadContext(-2, -1, Consts.STATUS_THREAD_READY_TO_EXEC, this);
        this.bufferOfItems = Buffer.getInstance();
        this.activeThread = true;
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
