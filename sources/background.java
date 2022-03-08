package sources;

import java.awt.*;

import cflowe.extension.publics;



public class background {
    
    game frame;


    

    
    background(game frame){
        this.frame = frame;
    }
    
    boolean title_flag = false;
    long title_end = 0;
    static String dir_background = "images/background/";

    Image space_img = Toolkit.getDefaultToolkit().getImage(dir_background + "space_pure.png");
    Image space_flip_img = Toolkit.getDefaultToolkit().getImage(dir_background + "nebula_flip.png");

    String space_img_name = "space_pure.png";

    static final String name_space_pixel = "space_pixel.png";
    static final String name_nebula = "nebula.png";
    static final String name_space_pure = "space_pure.png";
    
    Integer inv = 0;


    int draw(Graphics pen){
        pen.drawImage(space_img, 0, 0, null);
        pen.drawImage(space_flip_img, 0, 720, null);

        this.frame.background_obj.show_text("SCORE: " + this.frame.player_obj.score, 30, 70, pen);
        this.frame.background_obj.show_text("MSPT: " + game.MSPT, window.WIDTH - 200, 70, pen);

        // <>game over // <>temp abs pos color.RED
        

        return 0;
    }

    

    /**
     * draw a text on screen
     * 
     * @param text
     * @param x
     * @param y
     * @param pen
     * @param size_args
     * @param color_args
     * @param font_args
     * @return 0
     */

    
    public int show_text(String text, int x, int y, Graphics pen, Object ... args){
        int size = 30;
        Color color = Color.BLACK;
        int font = Font.BOLD;

        if (args.length > 0){
            size = (int) args[0];
        }
        if (args.length > 1){
            color = (Color) args[1];
        }
        if (args.length > 2){
            font = (int) args[2];
        }
        
        pen.setColor(color);
        pen.setFont(new Font("plain", font, size));

        pen.drawString(text, x, y);

        return 0;
    }  

    

    public int big_title(String text, Graphics pen, int ... args){
        title_end = publics.nowtime() + 3000;
        show_text(text, 90 + (window.WIDTH - 800), Math.round(window.HEIGHT / 2), pen, 100);
        return 0;
    }

    
    
}


