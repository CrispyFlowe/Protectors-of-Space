package cflowe.blocks;

import cflowe.extension.publics;

public class time implements Runnable {
    /**
     * count time in mills
     */

    public long count_mills = 0;



    public void count(time assign_timer){
        // time timer_c = new time();
        Thread timer_thread = new Thread(assign_timer);
        timer_thread.start();
        
    }

    // <>thread
    @Override
    public void run(){

        for (; publics.False;){
            count_mills++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    void end_counter(){
        
    }
}


