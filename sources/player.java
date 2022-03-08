package sources;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import cflowe.extension.*;




public class player extends object {

    game frame;
    
    player(game frame){
        this.frame = frame;
    }


    static int heal_amount = 0;
    static int shoot_interval = 100;
    static String dir_player = "images/player/";
    static String dir_gui_display = "images/gui_display/";
    static boolean auto_fire_flag = true;

    static int grade = 0;


    // player buff & effects
    static boolean fast_fire_flag = false;
    long fast_fire_buff = 0;
    static boolean invul_flag = false;
    long invul_buff = 0;

    static final int standard_hp = 20;

    long fire_cd = 0;
    
    int level = 1;

    // Image player_img = Toolkit.getDefaultToolkit().getImage(dir_player + "player_plane.png");

    Image player_img = Toolkit.getDefaultToolkit().getImage(dir_player + "cflowe_023.png");





    // Image player_img = Toolkit.getDefaultToolkit().getImage(enemy.dir_enemy + "missile_ship.png");
    

    Image health_bar_img = Toolkit.getDefaultToolkit().getImage(dir_gui_display + "HealthBar1.png");

    List<Image> health_bar_imgs = new ArrayList<>();
    {
        for (int i = 0; i < 16; i++){

            health_bar_imgs.add(publics.image_load(dir_gui_display + "HealthBar" + i + ".png"));
        }
    } 
 
    static boolean level_up_disp = false;

    



    public static final int type_a = 1; // player default(cflowe_004)
    public static final int type_b = 2; // cflowe_011
    public static final int type_c = 3; // cflowe_023


    
 
    public int type = type_a;

    int collide_shape = 30;
    int detect_range = collide_shape * 5;



    double x = 100;
    double y = 100;

    int score = 0;

    public int coin = 0;

    public int main_gun_level = 1;
    public int side_cannon_level = 1;
    public int wings_level = 1;
    public int missile_level = 1;
    


    int max_hp = standard_hp + wings_level;
    int hp = max_hp;
    int width = 136;
    int height = 198;
    int offset_x = 65;
    int offset_y = 30;
    int speed = 6;
    long invul_end = publics.nowtime();
    int animate_frame = 0; // max 16
    double damage = 1;
    int saved_people = 0;

    boolean show_launch_flag = false;


    
    int draw(Graphics pen){
        pen.drawImage(player_img, (int)(x - this.frame.camera_obj.x), (int)(y - this.frame.camera_obj.y), null);
        // pen.drawImage(health_bar_imgs.get(hp < 15 && hp > 0 ? 15 - hp : 1), (int) x, (int) y + 20, null);
        int health_bar_index = 0;
        if (hp < 19 && hp > 6){
            health_bar_index = 19 - hp;
    
        } else if (hp > 18){
            // full health bar
            health_bar_index = 1;
        } else if (hp < 7){
            // empty health bar
            health_bar_index = 15;
        } else {
            health_bar_index = 1;
        }
        pen.drawImage(health_bar_imgs.get(health_bar_index), 
        (int) (x - this.frame.camera_obj.x), (int) (y + 20 - this.frame.camera_obj.y), null);

        if (publics.False){
        // <>shrink
        this.frame.background_obj.show_text("GAME TIME: " + this.frame.timer_obj.count_mills / 1000, 30, 110, pen);
        }

        return 0;
    } 



    int display(){

        // player fire
        if (auto_fire_flag && publics.nowtime() > fire_cd){
            if (publics.nowtime() > fast_fire_buff){
                fire_cd = publics.nowtime() + shoot_interval;
            }
            // fast fire
            else if (publics.nowtime() < fast_fire_buff){
                fire_cd = publics.nowtime() + 10;
            }

            shoot_flag = true;
        }
        
        if (heal_amount > 0){
            hp += heal_amount;
            heal_amount = 0;
        }
    
        // control with keyboard
        /*
            int vel_x = 0;
            int vel_y = 0;
            vel_x = speed * ((event_pool.key_d ? 1 : 0) - (event_pool.key_a ? 1 : 0));
            vel_y = speed * ((event_pool.key_s ? 1 : 0) - (event_pool.key_w ? 1 : 0));
            x += vel_x;
            y += vel_y;
        */

        // control with mouse

        
        
        x = (int) publics.mouse_position_x() - window.window_x - offset_x;
        y = (int) publics.mouse_position_y() - window.window_y - Math.round(window.WIDTH / 20);

        // limit player not go out of screen
        if (x > window.WIDTH + width){
            x = window.window_x + window.WIDTH - height;
        }
        if (y > window.HEIGHT + height){
            y = window.window_y + window.HEIGHT - height;
        }

        max_hp = standard_hp +  wings_level;
        
        if (hp > max_hp){
            hp = max_hp;
        }

        
        if (shoot_flag){
            shoot();
            shoot_flag = false;
        }
        if (collide_flag){
            hurt(1);
            collide_flag = false;
        }
        if (hp < 1){
            hp = 0;
            game_over();

        }
        

        return 0;
    }

    static boolean shoot_flag = false;
    static boolean collide_flag = false;

    int game_over(){

        // <>print caption = you lose
        game.game_over_flag = true;

        // System.out.println("you lose!");
        return 0;
    }


    int shoot(){
        player_bullet.create((int)x + Math.round(width / 2) - 20, (int)y - 20, 1 + (main_gun_level / 100) * 70);
        // three stream, but damage is 0.2
        if (type == type_c){
            player_bullet.create((int)x + Math.round(width / 2) - 50, (int)y - 20, 0.2);
            player_bullet.create((int)x + Math.round(width / 2) + 10, (int)y - 20, 0.2);
        }
        
        return 0;
    }



    
    
    int hurt(int dmg){
        if (publics.nowtime() > invul_buff){
            hp -= dmg;
        }
        // <>temp print 
        System.out.println(hp);
        return 0;
    }


    int level_up(int ... args){
        // <>temo todo: level += args[0];
        level++;
        level_up_disp = true;
        if (args.length > 0){
            
            return 1;
        }
        
        return 0;
    }

    int player_reset(){
        // reset 1a
        max_hp =  wings_level;
        hp = max_hp;
        
        speed = 6;
        invul_end = publics.nowtime();
        animate_frame = 0; // max 16
        // temp code: double damage = 1;

        // reset 1b
        

        
        x = 100;
        y = 100;

        score = 0;
        return 0;
    }
} 






class player_stat {
    

    public static int play_time = 0;

    // public datas

    public int level = 0;

    



    // in level datas
    public int enemy_killed = 0;
    public int refugee_saved = 0;
    
   



}




