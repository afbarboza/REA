/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rea;

import View.*;

import Model.*;

import Controller.*;

/**
 *
 * @author alex
 */
public class REA {

    public static void printScreen() {
        ProducerThread pt = ProducerThread.getInstance();
        ConsumerThread ct = ConsumerThread.getInstance();
        Buffer b = Buffer.getInstance();

        /*System.out.println(">>>>Producer: ");
        System.out.println("Instruction Pointer: " + pt.getCurrentContext().getStackPointer());
        System.out.println("Item: " + pt.getCurrentContext().getProducedItem());
        System.out.println("Status: " + pt.getCurrentContext().getStatus());
        
        System.out.println(">>>>Consumer: ");
        System.out.println("Instruction Pointer: " + ct.getCurrentContext().getStackPointer());
        System.out.println("Item: " + ct.getCurrentContext().getProducedItem());
        System.out.println("Status: " + ct.getCurrentContext().getStatus()); */

        System.out.println(">>>Buffer: ");
        System.out.print("size of buffer: " + b.getBufferSize() + " ");
        for (int i = 0; i < b.getBufferSize(); i++) {
            System.out.format("# ");
        }
        System.out.println("\n End of Print \n");
    }

    public static void main(String[] args) {
        Scheduler sched = Scheduler.getInstance();
        for (int i = 0; i < 5000; i++) {
            int tmpRandom = (int) (Math.random() * 100.0);
            if (tmpRandom % 3 == 0) {
                sched.doContextSwitch(Consts.EXECUTE_NEXT_CONSUMER);
                printScreen();
            } else if (tmpRandom % 3 == 1) {
                sched.doContextSwitch(Consts.EXECUTE_NEXT_PRODUCER);
                printScreen();
            } else {
                if (i >= 100) {
                   sched.doContextSwitch(Consts.EXECUTE_BACK);
                   printScreen();
                }
            }
        }
    }

}
