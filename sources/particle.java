package sources;

import java.awt.*;


import java.util.*;
import java.util.List;

import cflowe.extension.publics;






public class particle implements Runnable {

    // Graphics pen;
    // particle(Graphics pen){
    //     this.pen = pen;
    // }

    game frame;
    particle(game frame){
        this.frame = frame;
    }

    particle(){
        
    }


    
    

    static String dir_particle = "images/particles/";

    Image img_explode = Toolkit.getDefaultToolkit().getImage(dir_particle + "explode.png");

    // <>default
    Image img;

    List<Image> explodes = new ArrayList<>();
    {
        for (int i = 5; i > 0; i--){
            explodes.add(Toolkit.getDefaultToolkit().getImage(dir_particle + "explode" + i + ".png"));
        }
    }


    Image add1_img = Toolkit.getDefaultToolkit().getImage(dir_particle + "+1.png"); 


    int x = 0;
    int y = 0;

    Graphics p_pen;

    @SuppressWarnings("all")
    void particle_explode(int x, int y, Graphics pen){
        // double each_frame = Math.round(sec / 5);
        
            // p_pen = pen;
            // Runnable current_mat = new particle_explode(x, y);
            // Thread current_thread = new Thread(current_mat);

        
    }

    static List<particle> particles = new ArrayList<>();

    static final int name_explode = 1;
    static final int name_explode_shine = 2;
    static final int name_explode_small = 3;
    static final int name_add1 = 4;

    public static void particle_with(int type, double x2, double y2){
        switch (type){
            case name_explode:
                particles.add(new particle_explode(x2, y2));
                break;
            case name_add1:
                particles.add(new add_one(x2, y2));
        }
    }

    void manage(){
        
    }

    int draw(Graphics pen){
        pen.drawImage(img, x, y, null);
        return 0;
    }


    
    
    @Override
    public void run(){
        boolean loop_break = false;
        // System.out.println("Thread 'particle' begin");

        // display particle
        while (!(loop_break)){
            // System.out.print("run");
            
            // publics.println(particles.size());
            for (int _p = 0; _p < particles.size(); _p++){
                
                
                switch ((particles.get(_p).getClass().getSimpleName())){
                    case "particle_explode":
                        // publics.println(particles.size());
                        particle_explode p = (particle_explode) particles.get(_p);
                        // Graphics pen = this.frame.get_pen();

                        // publics.println(p);

                        if (p == null){
                            continue;
                        }


                        if (publics.nowtime() > p.next_frame_begin_time){

                            p.next_frame_begin_time = publics.nowtime() + 50;
                            p.animate_frame += 1;

                            if (p.animate_frame > 5){
                                particles.remove(p);
                                continue;
                            }

                            if (!(this.frame.game_state == game.state_in_level)){
                                loop_break = true;
                            }

                            // publics.println(particles.size());


                            p.img = explodes.get(p.animate_frame - 1);
                            p.x += 7;
                            // p.img = explodes.get(1);



                            // pen.drawImage(this.frame.player_obj.player_img, x, y, null);
                        }
                
                    // case "add_one":
                    //     add_one a = (add_one) particles.get(_p);

                    
                }
            }
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException e){ 
                e.printStackTrace();
            }
        }
        // System.out.println("Thread 'particle' end");
        
    }

    public void begin(particle thr){
        Runnable thread_particle = thr;
        new Thread(thread_particle).start();
    }
    
}

class particle_explode extends particle {

    Graphics p_pen;
    double x = 0;
    double y = 0;


    
    particle_explode(double x, double y){
        this.x = x;
        this.y = y;
    }


    Image img;


    

    int draw(Graphics pen){
        pen.drawImage(img, (int) (x /*- this.frame.camera_obj.x*/), (int) (y /*- this.frame.camera_obj.y*/), null);
        return 0;
    }


    long next_frame_begin_time = 0;

    int animate_frame = 0;

    

    // Runnable runs = new particle_explode(1, 2, p_pen);
    // {
    //     new Thread(runs).run();
    // }

    


    /*
    @Override
    public void run() {
        // if (!(p_pen == null)){
            // publics.println("play");
        for (int i = 0; i < 5; i++){
            p_pen.drawImage(explodes.get(i), begin_x, begin_y, null);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        // }
    }
    */

    
}

class add_one extends particle {
    add_one(){
        this.x = (int) x;
        this.y = (int) y;
    }

    public add_one(double x2, double y2) {
    }
}




