package sources;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

import cflowe.extension.publics;

// import cflowe.extension.*;

public class item {
    
    

    static List<item> items = new ArrayList<>();

    double x = 0;
    double y = 0;

    int width = 10;
    int height = 10;


    item(){
         
    }

    int use(player po){
        return 0;
    }

    int draw(Graphics pen, Object ... args){
        if (args.length > 0){
            camera camera_obj = (camera) args[0];
            pen.drawImage(img, (int) (x - camera_obj.x), (int) (y - camera_obj.y), null);
        }
        return 0;
    }
    
    static int remove(item i){
        items.remove(i);
        return 0;
        
    }

    static int create(int ... args){
        // System.out.println("i");
        int x = 0;
        int y = 0;
        if (args.length > 0){
            // args init
            x = args[0];
            y = args[1];
        }
        // System.out.println("item at x: " + x + " y: " + y);
        items.add(new first_aid_kit(x, y));
        return 0;
    }

    Image img;

    static String dir_item = "images/item/";
    static String dir_particle = "images.particle/";
}

class first_aid_kit extends item {
    first_aid_kit(double x, double y){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_item + "first_aid_kit.png");
        this.x = x;
        this.y = y;
    }
    int healing = 3;

    int use(player po){
        po.hp += healing;
        
        return 0;
    }
        

    static int create(int ... args){
        // System.out.println("i");
        int x = 0;
        int y = 0;
        if (args.length > 0){
            // args init
            x = args[0];
            y = args[1];
        }
        // System.out.println("item at x: " + x + " y: " + y);
        items.add(new first_aid_kit(x, y));
        return 0;
    }
}



class powerup extends item {
    powerup(double x, double y){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_item + "powerup.png");

        this.x = x;
        this.y = y;
    }

    int use(player po){
        po.fast_fire_buff = publics.nowtime() + 3000;
        return 0;
    }

    static int create(int ... args){
        int x = 0;
        int y = 0;
        if (args.length > 0){
            // args init
            x = args[0];
            y = args[1];
        }
        items.add(new powerup(x, y));
        return 0;
    }
}



class shield extends item {
    shield(double x, double y){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_item + "shield.png");

        this.x = x;
        this.y = y;
    }

    int use(player po){
        po.invul_buff = publics.nowtime() + 5000;
        return 0;
    }

    static int create(int ... args){
        int x = 0;
        int y = 0;
        if (args.length > 0){
            // args init
            x = args[0];
            y = args[1];
        }
        items.add(new shield(x, y));
        return 0;
        
    }

}

class levelup extends item {
    levelup(double x, double y){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_item + "levelup.png");


        this.x = x;
        this.y = y;
    }

    int use(player po){
        po.level_up(1);
        return 0;
    }

    static int create(int ... args){
        int x = 0;
        int y = 0;
        if (args.length > 0){
            x = args[0];
            y = args[1];
        }
        items.add(new levelup(x, y));
        return 0;
               
    }
}




class coin extends item {
    coin(double x, double y){
        this.img = Toolkit.getDefaultToolkit().getImage(dir_item + "coin_normal.png");

        this.x = x;
        this.y = y;
    }

    int use(player po){
        po.coin += 1;
        return 0;
    }

    static Image coin_img = Toolkit.getDefaultToolkit().getImage(dir_item + "coin_normal.png");

    
    static int create(int ... args){
        int x = 0;
        int y = 0;
        if (args.length > 0){
            x = args[0];
            y = args[1];
        }
        items.add(new coin(x, y));
        return 0;
    }
} 






