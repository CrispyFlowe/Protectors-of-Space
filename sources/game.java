package sources;

import java.awt.*;


import javax.swing.*;

import cflowe.extension.*;
// import data.save_load;
import cflowe.blocks.*;

 
// import java.util.List;
// import java.util.ArrayList;
import levels.level_data;



public class game {

    




    static int FPS = 30;
    static boolean game_begin_flag = false;

    public static int loop_speed = 0; //default 0,  > 10, then 2, > 100, then 4
    
    public static long MSPT = 10;


    public static boolean running = true;

    public static boolean game_over_flag = false;

    public static long game_time = 0; 
    
    public static String UNAME = "guest";

    

    public boolean particle_effect = true;

    // -*- -*- -*- -*- -*-
    int game_state = 0; // state_begin

    public static final int state_begin = 0;
    public static final int state_in_level = 1;
    public static final int state_homepage = 2;
    public static final int state_shop = 3;
    public static final int state_achievement = 4;
    public static final int state_update = 5;
    public static final int state_game_over = 6;
    public static final int state_settings = 7;
    public static final int state_prepare = 8;
    
    
    spawner spawner_obj = new spawner(this);

    long next_spawn = 0;

    object object_obj = new object();

    background background_obj = new background(this);
    player player_obj = new player(this);
    // player_bullet player_bullet_obj = new player_bullet(100, 100);
    camera camera_obj = new camera(this);
    level level_obj = new level(this);
    shop shop_obj = new shop(this);

    time timer_obj = new time();

    gui_interfaces gui_interfaces_obj = new gui_interfaces(this);
    

    enemy enemy_obj = new enemy(this);

    particle particle_obj = new particle(this);

    
    // randomally add bullet to screen
    // <>temp
    /*
    {
        for (int i = 0; i < 10; i++){
            bullet add_bullet = new player_bullet(100, 100);
            bullet.bullets.add(add_bullet);
        }
    }
    */
    
    int init(){

        level_data.init();

        


        return 0;
    }



    int level_reward = 0;

    // -*- -*- -*- -*- -*- -*- -*-

    Graphics current_pen;

    public Graphics get_pen(){
        return current_pen;
    }

    int proc(Graphics pen){

        long func_begin = publics.nowtime();

        current_pen = pen;
        

        switch (game_state){
            case state_begin:
                gui_interfaces_obj.begin_interface_proc(pen);
                break;

            case state_homepage:
                homepage_proc(pen);
                break;
            
                
            case state_in_level:
                level_obj.level_proc(pen);
                break;

            case state_game_over:
                game_over_proc(pen);
                break;

            case state_shop:
                // do inshop
                shop_obj.shop_proc(pen);
                break;
                
            case state_achievement: 
                // do achievement
                break;

            case state_settings:
                settings_proc(pen);
                // settings
                break;

            case state_prepare:
                gui_interfaces_obj.prepare_interface_proc(pen);
                break;

            

        }

        // reset();

        MSPT = publics.nowtime() - func_begin;
            
        return 0;
    }

    

    static boolean temp_var_s2 = false;

    
    Image game_over_image = Toolkit.getDefaultToolkit().getImage(background.dir_background + "game_over.png");


    
    
    int game_over_proc(Graphics pen){
        pen.drawImage(game_over_image, -20, 0, null);
        background_obj.show_text("YOUR SCORE IS: " + player_obj.score, 55, 205, pen, 30, Color.PINK);
        background_obj.show_text("YOUR REWARD IS: " + level_reward, 55, 255, pen, 30, Color.GREEN);
        background_obj.show_text(
            "YOU SAVED " + player_obj.saved_people + " PEOPLES! REWARD + " + player_obj.saved_people,
            55, 355, pen, 35, Color.ORANGE
            );

        // back to main manu
        if (clicked(
            300, 400, 465, 500
        )){
            game_reset();
            game_state = state_homepage;
        }

        return 0;
    }


    Image settings_img = Toolkit.getDefaultToolkit().getImage(begin_interface.dir_thumbnail + "settings.png");
    boolean game_music = true;
    boolean game_soundsfx = true;

    long button_interval = 0;
    boolean button_release = false;

    int settings_proc(Graphics pen){

        pen.drawImage(settings_img, -20, 0, null);

        button_release = publics.nowtime() > button_interval;

        background_obj.show_text("music: " + game_music, 30, 200, pen, 30, Color.ORANGE);
            if (button_release && clicked(
                25, 210, 145, 180
            )){
                button_interval = publics.nowtime() + 300;
                game_music = game_music ? false : true;
                
            }


        background_obj.show_text("sound: " + game_soundsfx, 30, 250, pen, 30, Color.ORANGE);
            if (button_release && clicked(
                30, 205, 200, 215
            )){
                button_interval = publics.nowtime() + 300;
                game_soundsfx = game_soundsfx ? false : true;

            }

        background_obj.show_text("background: " + background_obj.space_img_name, 30, 300, pen, 30, Color.ORANGE);
            if (button_release && clicked(
                25, 215, 240, 275
            )){
                if (window.DEBUG){
                    
                
                
                // change background
                    // nebula
                    if (background_obj.space_img_name == background.name_space_pure){

                        background_obj.space_img = Toolkit.getDefaultToolkit().getImage(
                            background.dir_background + "nebula.png"
                        );
                        background_obj.space_img_name = background.name_nebula;
                        
                        // publics.println(background_obj.space_img_name);
                    }
                    // space_pixel
                    else if (background_obj.space_img_name == background.name_nebula){

                        background_obj.space_img = Toolkit.getDefaultToolkit().getImage(
                        background.dir_background + "space_pixel.png"
                        );
                        background_obj.space_img_name = background.name_space_pixel;
                        
                        // publics.println(background_obj.space_img_name);
                    }
                    else if (background_obj.space_img_name == background.name_space_pixel){

                        background_obj.space_img = Toolkit.getDefaultToolkit().getImage(
                        background.dir_background + "space_pure.png"
                        );
                        background_obj.space_img_name = background.name_space_pure;

                        // publics.println(background_obj.space_img_name);
                    }
                }
                button_interval = publics.nowtime() + 300;
                
                
            }

            
        background_obj.show_text("particle effects: " + true, 30, 400 , pen, 30, Color.MAGENTA);

        background_obj.show_text("back", 50, 580, pen, 30, Color.CYAN);
            if (clicked(
                40, 130, 520, 555
            )){
                button_interval = publics.nowtime() + 300;
                game_state = state_homepage;

            }

        return 0;
        
    }


    

    /**
     * 
     * @param min_x
     * @param max_x
     * @param min_y
     * @param max_y
     * @param top_x_args
     * @param top_y_args
     * @return
     */

    boolean clicked(int min_x, int max_x, int min_y, int max_y, int ... args){
        int top_x = 0;
        int top_y = 0;
        if (args.length > 0){
            top_x = args[0];
            top_y = args[1];
        }
        if (window.mouse_down &&
            publics.mouse_position_x() - window.window_x > min_x + top_x && 
            publics.mouse_position_x() - window.window_x < max_x + top_x &&
            publics.mouse_position_y() - window.window_y > min_y + top_y && 
            publics.mouse_position_y() - window.window_y < max_y + top_y
        ){
            return true;
        } else {
            return false;
        }


    }


    boolean optionpane_open = false;
    boolean optionpane(String ... input_text){
        
        
        if (!(optionpane_open)){
            optionpane_open = true; 
            reset();
            
            String text = "Are you sure? (Y / N)";
            if (input_text.length > 0){
                text = input_text[0]; 
            }
            String answer = JOptionPane.showInputDialog(text);
            if (answer == null){
                optionpane_open = false; 
                return false;
            }
            if (answer.toLowerCase().contains("y")){ 
                optionpane_open = false;
                return true;
            } else if (answer.toLowerCase().contains("n")){
                optionpane_open = false;
                return false;
            } else {
                optionpane_open = false;
                return false;
            }
        }
        return false;
    }

    // <>overload
    boolean optionpane(String text, boolean open_condition){
        if (open_condition){
            return optionpane(text);
        } 
        return false;
    }

    String opane(String text){
        if (!(optionpane_open)){
            optionpane_open = true;
            reset();
            String answer = JOptionPane.showInputDialog(text);
            optionpane_open = false;
            return answer;
        } else {
            optionpane_open = false;
            return "";
        }
    }


    



    



     


    
    

    int homepage_proc(Graphics pen){
        int image_x = 0;
        int image_y = -45;
        pen.drawImage(begin_interface.homepage_si, image_x, image_y, null); 

        background_obj.show_text(
        "Welcome, " + UNAME + "! (if this is not you, click here)", 30, 580, pen, 20, Color.CYAN
        );
        

        if (window.mouse_down){
            // start game button
            if (clicked(
                65, 275, 235, 280
                
            )){
                // level init
                game_state = state_in_level;
                
                
                // calculate particle thread
                particle_obj.begin(particle_obj);
                // reset();
                level_obj.level_init();
                player_stat.play_time += 1;
                timer_obj.count(timer_obj);
            }
            if (clicked(
                // shop button
                65, 270, 305, 355
            )){
                game_state = state_shop;
                // shop state note
            }
            if (
                // achievement button   
                true
            ){
                // do achievement 
            }
            if (clicked(
                70, 270, 455, 500
            )){
                // do settings
                game_state = state_settings;
            }
            
        }



        return 0;
    }




    void reset(){
        window.mouse_down = false;
        // return 0;
    }

    int game_reset(){
        player_obj.player_reset();
        enemy_obj.enemy_reset();
        bullet.bullet_reset();
        
        return 0;
    }


} 


