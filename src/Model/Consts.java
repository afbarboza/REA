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
public class Consts {
    public static final int STATUS_THREAD_EXECUTING =       1;
    public static final int STATUS_THREAD_BLOCKED =         2;
    public static final int STATUS_THREAD_READY_TO_EXEC =   3;
    
    public static final int EXECUTE_NEXT_CONSUMER = 0;
    public static final int EXECUTE_NEXT_PRODUCER = 1;
    public static final int EXECUTE_BACK = 2;
    
    public static final int MAX_SIZE_BUFFER = 5;
}
