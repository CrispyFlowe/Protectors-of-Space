package sources;

import java.awt.*;

import java.util.List;


import java.util.ArrayList;

import cflowe.extension.*;
import sources.gui.screengui;

public class enemy extends object {


    

    game frame;


    enemy(game frame){
        this.frame = frame;
    }
    enemy(){

    }

    

    /**
     * if dangerous is false, you can't damage it
     */
    boolean dangerous = true;

    
    
    static List<object> objects = new ArrayList<>();

    static String dir_enemy = "images/enemy/";
    Image plane_img = Toolkit.getDefaultToolkit().getImage(dir_enemy + "");

    
    static int min_speed = 1;
    double speed = 1;

    int full_hp = 1;
    int hp = 1;

    static int density = 30;

    int width = 10;
    int height = 10;

    double x = math.random(50, window.WIDTH - 100);
    double y = -20 + (width * -1);

    static boolean debug_enemy_hpbar_flag = false;

    

    static void append(enemy e){
        enemy.objects.add(e);
    }

    int valued = 1;

    int damage = 1;
    

    int number_id = 0;

    Image img;

    /**
     * if ground is true, player can fly over it
     */
    boolean ground = false;


    Image hpbar_img = Toolkit.getDefaultToolkit().getImage(player.dir_gui_display + "HealthBar1.png");


    int draw(Graphics pen, Object ... args){

        
        
        // if frame is null
        if (args.length > 0){
            camera camera_obj = (camera) args[0];
            if (debug_enemy_hpbar_flag){
                pen.drawImage(hpbar_img, (int) x + 5, (int) y - 15, null);
            }  
            pen.drawImage(img, (int)(x - camera_obj.x), (int)(y - camera_obj.y), null);
            // if (debug_enemy_hpbar_flag){
            //     pen.drawImage(hpbar_img, (int) x + 5, (int) y - 15, null);
            // }
            // pen.drawImage(img, (int)(x - 0), (int)(y - 0), null);
            // return 0; 
        }
    
        // normal
        // if (debug_enemy_hpbar_flag){
        //     pen.drawImage(hpbar_img, (int) x + 5, (int) y - 15, null);
        // }
        // pen.drawImage(img, (int)(x - this.frame.camera_obj.x), (int)(y - this.frame.camera_obj.y), null);
        
        return 0;
    }

    //reload
    int draw(Graphics pen){
        // draw(pen);
        return 0;
    }



    int animate(int ... args){
        
        return 0;
    }


    int drop_item(enemy e, double x, double y){
        int next_item = math.random(1, 100);
    
        if (next_item == 10){
            first_aid_kit.create((int) x, (int) y);
        } else if (next_item > 10 && next_item < 30 && e.number_id == missile.missile_id){
            powerup.create((int) x, (int) y);
        } else if (next_item == 1){
            shield.create((int) x, (int) y);
        } else if (next_item == 5){
            levelup.create((int) x, (int) y);
        } else if (next_item > 30 && next_item < 40){
            coin.create((int) x, (int) y);
        }
        return 0;
    }
    
    static int remove(enemy e){
        // if (enemies.size() > 0){
        //     enemies.remove(e); 
        // }
        
       

        e.x = 10000;
        e.y = 10000;
        return 0;
    }

    static int kill(enemy e, Graphics pen){
        // if (enemies.size() > 0){
        //     enemies.remove(e); 
        // }
        // Runnable _explode_thread = new particle_explode(e.x, e.y, pen);
        // Thread explode_thread = new Thread(_explode_thread);
        // explode_thread.start();

        particle.particle_with(particle.name_explode, e.x, e.y);

        remove(e);
        
        return 0;
    }

    
    /**
     * 
     * @param pen
     * @return {@code int} if success killed all, return 1, if not, 0
     */
    static int kill_all(Graphics pen){
        for (int _e = 0; _e < enemy.objects.size(); _e++){
            enemy e = (enemy) enemy.objects.get(_e);
            enemy.kill(e, pen);
        }
        return 0;
    }


    
    static enemy enemy_choice(enemy ... args){
        int rand_val = math.random(0, args.length - 1);
        return (args[rand_val]);
    }


    int enemy_reset(){
        objects.clear();
        return 0;
    }


    
     
    
}



class boomber extends enemy {
    boomber(double ... args){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_enemy + "missile_boomber.png");
        
        
        if (args.length > 0){
            this.x = args[0];
            this.y = args[1];
        // System.out.println(y);
        }
        

        this.hp = 3;
        this.speed = 2;
        this.damage = 3;
        this.valued = 5;

        this.width = 82;
        this.height = 80;

        this.number_id = boomber_id;
    }
    long next_fire = 0;

    static final int boomber_id = 1;

    static int create(int _x, int _y){
        objects.add(new boomber(_x, _y));
        return 0;
    }

    int animate(int ... args){
        if (publics.nowtime() > next_fire){
            next_fire = publics.nowtime() + 3000;
            small_fo.create(x + (Math.round(width / 2) - 15), y + height);
            // <>temp small_fo.create
        }
        return 0;
    }
} 




class small_fo extends enemy {
    small_fo(double ... args){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_enemy + "small_flying_object.png");

        if (args.length > 0){
            this.x = args[0];
            this.y = args[1];
        }
        
        this.hp = 1;
        this.speed = 5;
        this.damage = 1;
        this.valued = 1;

        this.width = 34;
        this.height = 34;

        this.number_id = small_fo_id;
    }

    static final int small_fo_id = 2;

    static int create(double _x, double _y){
        objects.add(new small_fo(_x, _y));
        return 0;
    }
}

 
class missile extends enemy {
    missile(){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_enemy + "missile.png");

        this.hp = 1;
        this.speed = 10;
        this.damage = 7;
        this.valued = 10;
        
        this.width = 33;
        this.height = 87;

        this.number_id = missile_id;
    }

    static final int missile_id = 3;

    int animate(int ... args){
        /**
         * int .. args: player x, player y
         */
        // enemy follow player
        if (x > args[0]){
            x -= 1;
        } else if (x < args[0]){
            x += 1;
        }
        return 0;
    }
}



class space_pawn extends enemy {
    space_pawn(){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_enemy + "space_pawn.png");

        this.hp = 1;
        this.speed = 5;
        this.damage = 1;
    

        this.width = 10;
        this.height = 34;

        this.number_id = enemy_bullet_id;

    }

    static final int enemy_bullet_id = 4;


    int direction = math.random(-1, 1);
    long animate_time = publics.nowtime() + math.random(1000, 5000);

    int animate(int ... args){
        
        if (publics.False){
            return 1; 
        }
        
        if (publics.nowtime() < animate_time){
            x += direction;
            if (x < 0 || x > window.WIDTH){
                direction *= -1;
            }
        } else {
            direction = math.random(-1, 1);
            animate_time = publics.nowtime() + math.random(1000, 5000);
        }
        return 0;
    }


    



}

class meteorite extends enemy {
    meteorite(){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_enemy + "meteorite.png");

        this.hp = 20;
        this.speed = 1;
        this.damage = 3;
        this.valued = 1;

        this.width = 88;
        this.height = 101;

        this.number_id = meteorite_id;
    }

    static final int meteorite_id = 5;
}




class space_rental extends enemy implements ground {
    space_rental(){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_enemy + "space_for_rent.png");

        this.hp = 40;
        this.speed = 1;
        this.damage = 0;
        this.valued = 100;

        this.ground = true;

        this.width = 90;
        this.height = 58;

        this.number_id = space_rental_id; 
    }

    static final int space_rental_id = 6;
}



class people extends enemy implements human, ground, passive {
    people(){
        
        
        this.hp = 1;
        this.speed = 1;
        this.dangerous = false;

        

        

        this.number_id = people_id;
        
    }

    // void success(){
    //     enemy.objects.remove();
    // }

    List<Image> progress_bar_img = new ArrayList<>();

    int rescue_progress = 0;
    long begin_time = 0;

    long resc_time = 0;

    static final int people_id = 7;
}

class refugee extends people {
    refugee(){

        // random gender
        this.img = (math.random(0, 1) == 0) 
        ? Toolkit.getDefaultToolkit().getImage(dir_enemy + "refugee_female.png")
        : Toolkit.getDefaultToolkit().getImage(dir_enemy + "refugee_male.png")
        ;

        // this.hp = 1;
        this.speed = 1;
        this.dangerous = false;

        this.ground = true;

        this.width = 34;
        this.height = 62;

        this.number_id = refugee_id;
    }

    
    {
        // load 10 images 
        for (int i = 1; i < 11; i++){
            // System.out.println(i);
            progress_bar_img.add(Toolkit.getDefaultToolkit().getImage(player.dir_gui_display + "progress" + i + ".png"));
        }
    }



    static final int refugee_id = 8;
}

class destroyer extends enemy implements not_damagable, boss {
    destroyer(){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_enemy + "destroyer.png");

        this.hp = 200;
        this.speed = 0.3;
        this.damage = 0;
        this.valued = 1000;

        this.ground = false;

        this.width = 165;
        this.height = 256;

        this.number_id = destroyer_id; 
    }

    static final int destroyer_id = 9;

    long next_move = 0;
    long next_shoot = 0;

    int move_dir = 0;

    screengui screengui_obj = new screengui();

    int animate(int ... args){
        
        if (publics.nowtime() > next_move){
            next_move = publics.nowtime() + 3000;

            move_dir = math.random(0, 360);
            
        }
        x += (Math.sin(move_dir)) * speed;
        y += (Math.cos(move_dir)) * speed;

        if (!(window.is_on_screen_x(x))){
            move_dir -= 180;
        }
        if (y > Math.round(window.HEIGHT / 2)){
            move_dir -= 180;
            y += (Math.cos(move_dir)) * 30;
        }

        if (y < (window.HEIGHT - Math.round(window.HEIGHT / 2)) * -1){
            y += Math.round(window.HEIGHT / 2);
        }

        if (publics.nowtime() > next_shoot){
            next_shoot = publics.nowtime() + math.random(500, 2000);
            
            enemy.append(new small_fo());
            enemy.append(new missile());
        }

        
        
       

        

        return 0;
    }
}


class area extends enemy implements ground, passive {
    area(){
        
    }
}

class tree extends area {
    tree(){
         // this.img; = Toolkit.getDefaultToolkit().getImage(dir_enemy + "tree.png");

        this.speed = 1;
        this.width = 94;
        this.height = 141;
    }
}


