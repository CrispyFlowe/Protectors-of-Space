package sources;



import cflowe.extension.*;
import data.save_load;
import levels.level_data;


import java.util.*;
import java.util.List;






import java.awt.*;


public class gui_interfaces {


    game frame;
    gui_interfaces(game frame){
        this.frame = frame;
    }
    
    //
    
    
        // if (!(this.frame == null)){
        
    
    
    //   

    {
        System.console().writer();
    }

    static String dir_thumbnail = "images/thumbnails/";

 
    static Image begin_thumbnail = Toolkit.getDefaultToolkit().getImage(dir_thumbnail + "space_invaders_thumbnail.png");

    int begin_interface_proc(Graphics pen){

        game game_obj = this.frame;
        // background background_obj = this.frame.background_obj;

        pen.drawImage(begin_thumbnail, -60, 0, null);

        // ask name
        if (game.game_begin_flag){
            String question = game_obj.opane("""
            what's your name? 
            (A user name can prevent lose your game data)
            """);


            if (question == null){} else if (question.length() < 1){}
            else {
                game.UNAME = question;
            }

            // read data and load to game
            List<Object> datas = new ArrayList<>();
            datas = save_load.load_data(game.UNAME);

            // load data
            if (datas.size() > 0){


                player_stat.play_time = (int) datas.get(0);
                game_obj.player_obj.coin = (int) datas.get(1);
                game_obj.player_obj.type = (int) datas.get(2);
                game_obj.player_obj.main_gun_level = (int) datas.get(3);
                game_obj.player_obj.side_cannon_level = (int) datas.get(4);
                game_obj.player_obj.wings_level = (int) datas.get(5);

                game_obj.game_music = ((int) datas.get(6)) > 0 ? true : false;
                game_obj.game_soundsfx = ((int) datas.get(7)) > 0 ? true : false;

            } else if (datas.size() > 0){
                // <>print
                publics.println("new player");
            }


            // return homepage (#13)

            game_obj.game_state = game.state_homepage;

        }

        return 0;
    }


    static Image prepare_thumbnail = Toolkit.getDefaultToolkit().getImage(dir_thumbnail + "prepare_interface.png");

    


    int prepare_interface_proc(Graphics pen){


        

        // game game_obj = this.frame;
        background background_obj = this.frame.background_obj;

        pen.drawImage(prepare_thumbnail, -60, 0, null);
        // background_obj.show_text()

        background_obj.show_text("> level " + player_stat.play_time, 50, 100, pen, 50, Color.ORANGE, Font.TYPE1_FONT);
        
        background_obj.show_text(
            "refugees: " + level_data.level1.get(level_data.level_refugees), 50, 250, pen, 30, Color.ORANGE, Font.PLAIN
        );

        
        // int local_x = 50;
        // int local_size = 30;
        // Color local_color = Color.DARK_GRAY;

        

        
        //#region
        //#endregion

        /**
         * LIB
         * background_obj.show_text("may get: ", local_x, 300, pen, local_size, local_color, Font.PLAIN);
         * background_obj.show_text("coin * 10 ~ 30", local_x, 350, pen, local_size, local_color, Font.PLAIN);
         * background_obj.show_text("space varient iron", local_x, 400, pen, local_size, local_color, Font.PLAIN);
         * background_obj.show_text("light fabric", local_x, 450, pen, local_size, local_color, Font.PLAIN);
         */


        // background_obj.show_text("IF YOU READY, CLICK SPACE", 50, 550, pen, 30, Color.WHITE, Font.PLAIN);
        
        
        return 0;
    }




    int pause_interface_proc(Graphics pen){

        // game game_obj = this.frame;
        // background background_obj = this.frame.background_obj;

        return 0;
    }







}






