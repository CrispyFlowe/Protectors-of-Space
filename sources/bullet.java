package sources;


import java.awt.*;

import java.util.List;
import java.util.ArrayList;

import cflowe.extension.*;

public class bullet extends object {

    // game frame;
    // bullet(game frame){
    //     this.frame = frame;
    // }

    
    static List<bullet> bullets = new ArrayList<>();

    double damage = 1;

    double x = math.random(100, 800);
    double y = math.random(0, 600);

    int width = 0;
    int height = 0;

    Image img;

    int draw(Graphics pen, Object ... args){
        if (args.length > 0){
            camera camera_obj = (camera) args[0];
            pen.drawImage(img, (int) (x - camera_obj.x), (int) (y - camera_obj.y), null);
        }
        
        return 0;
    }

    int display(){
        
        return 0;
    }

    static int remove(bullet b){
        b.x = 10000;
        b.y = 10000;
        return 0;
    }

    static int bullet_reset(){
        bullets.clear();
        return 0;
    }

}


class player_bullet extends bullet {
    player_bullet(int start_x, int start_y, double damage){
        
        this.img = Toolkit.getDefaultToolkit().getImage(player.dir_player + "player_bullet.png");

        this.x = start_x;
        this.y = start_y;
        this.damage = damage;

        this.width = 10;
        this.height = 10;
    }

    /**
     * 
     * @param start_x
     * @param start_y
     * @param damage
     * @return
     */
    static int create(int start_x, int start_y, double damage){
        bullets.add(new player_bullet(start_x, start_y, damage));
        return 0;
    }

    // <>overload
    static int create(int start_x, int start_y){
        create(start_x, start_y, 1);
        return 0;
    }

}



