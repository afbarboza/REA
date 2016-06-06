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

public class Consts {
    public static final int THREAD_PRODUCER = 1;
    public static final int THREAD_CONSUMER = 2;
    
    public static final int STATUS_THREAD_EXECUTING =       1;
    public static final int STATUS_THREAD_BLOCKED =         2;
    public static final int STATUS_THREAD_READY_TO_EXEC =   3;
    public static final int STATUS_THREAD_GOING_BLOCK =     4;
    
    public static final int EXECUTE_NEXT_CONSUMER = 0;
    public static final int EXECUTE_NEXT_PRODUCER = 1;
    public static final int EXECUTE_BACK = 2;
    
    public static final int MAX_SIZE_BUFFER = 5;
}
