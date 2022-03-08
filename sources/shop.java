package sources;



import java.awt.*;

import cflowe.extension.*;



public class shop {

    game frame;

    shop(game frame){
        this.frame = frame;
    }
    

    boolean clicked(int min_x, int max_x, int min_y, int max_y){

        return (this.frame.clicked(min_x, max_x, min_y, max_y));

    }

    boolean optionpane(String text){
        return (this.frame.optionpane(text));
    }



    int shop_proc(Graphics pen){
        int top_x = -120; // -120 default, 0 while debug
        int top_y = 0; 
        pen.drawImage(begin_interface.update_si, top_x, top_y, null);


        // publics.mouse_position_x() > 590 - top_x && publics.mouse_position_x() < 780  - top_x &&
        // publics.mouse_position_y() > 585 - top_y && publics.mouse_position_y() < 625 - top_y

        publics.show(window.window_x); // 320
        publics.show(window.window_y); // 170

        background background_obj = this.frame.background_obj;
        player player_obj = this.frame.player_obj;



        pen.drawImage(coin.coin_img, 30, 50, null);
        background_obj.show_text("   " + player_obj.coin, 70, 80, pen, 30, Color.WHITE);


        // show level - update show each part's level and price
        // main gun
        if (player_obj.main_gun_level > 10){
            background_obj.show_text("MAX", 300, 180, pen, 20, Color.RED);
        } else {
            background_obj.show_text("(" + player_obj.main_gun_level + ")", 300, 180, pen, 20);
        }
        // side cannon
        if (player_obj.side_cannon_level > 10){
            background_obj.show_text("MAX", 200, 320, pen, 20, Color.RED);
        } else {
            background_obj.show_text("(" + player_obj.side_cannon_level + ")", 205, 320, pen, 20);
        }
        // wings
        if (player_obj.wings_level > 10){
            background_obj.show_text("MAX", 610, 380, pen, 20, Color.RED);
        } else {
            background_obj.show_text("(" + player_obj.wings_level + ")", 620, 380, pen, 20);
        }


        


        if (window.mouse_down){
            if (clicked(
                // last position is 865, 995, 175, 215, top_x, top_y
                665, 790, 2, 40
            )){
                this.frame.game_state = game.state_homepage;
            } 
            if (clicked(
                170, 290, 135, 160
            )){
                // main gun
                // publics.println("main gun");
                if (optionpane(
                    "are you sure to update main gun? damage + 70%," + " cost 5, Y/N")){

                    // publics.println("updated!");
                    player_obj.coin -= 5;
                    player_obj.main_gun_level += 1;
                }
            }

            if (clicked(
                80, 200, 265, 295
            )){
                // side cannon
                // publics.println("side cannon");
                if (optionpane("are you sure to update side cannon? damage + 50%, (cost 5, Y/N)")){
                    // publics.println("updated!");
                    player_obj.coin -= 5;
                    player_obj.side_cannon_level += 1; 
                }
            }  
            if (clicked(
                540, 620, 330, 355
            )){
                // wings(body)
                // publics.println("wings & body");
                if (optionpane("are you sure to update wings & body? player hp + 1, (cost 5, Y/N)")){
                    // publics.println("updated!");
                    player_obj.coin -= 5;
                    player_obj.wings_level += 1;
                }
            }

        }
        return 0;
    }



}


