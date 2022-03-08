package sources.gui;

import java.awt.*;
import java.util.*;
import java.util.List;




public class screengui {


    


    static String dir_gui_display = "images/gui_display/";
    

    public List<Image> health_bar_imgs = new ArrayList<>();
    {
        for (int i = 0; i < 16; i++){

            health_bar_imgs.add(Toolkit.getDefaultToolkit().getImage(dir_gui_display + "HealthBar" + i + ".png"));
        }
    } 


    public int gui_get_health_bar(int _val, int _full_val){
        int health_bar_index = 0;

        // 90, 210
        int val = Math.round((_val * 100) / _full_val);

        // 19, 6

        // total 20 images
        health_bar_index = Math.round((100 - val) / 5);
        

        // if (val < 95 && val > 5){
        //     health_bar_index = 19 - val;
            
        // } else if (val > 18){
        //     // full health bar
        //     health_bar_index = 1;
        // } else if (val < 7){
        //     // empty health bar
        //     health_bar_index = 15;
        // } else {
        //     health_bar_index = 1;
        // }

        return health_bar_index;
    }



    
}



