package sources;

import cflowe.extension.publics;

/**
 * class camera
 * every moving object's draw function in this game need a argument to input camera object
 */
//





public class camera {

    game frame; 

    camera(game frame){
        this.frame = frame;
    }


    double x = 10;
    double y = 1;

    static int last_x = 0;
    static int last_y = 0;

    int shake(double shake_x, double shake_y){
        x = x * shake_x;
        y = y * shake_y;
        return 0;
    }
    
    
    //reload
    int shake_camera(){
        shake(-16, 16);
        shake(-1.8, 1.8);
        
        for (int i = 0; i < 16; i++){
            shake(publics.rand_double(0.5, 0.8), publics.rand_double(0.5, 0.8));
        }
        shake(0, 0);
        return 0;
    }
    
     
    //reload
    int shake(double range){
        shake(1, 1);
        return 0;
    }

}
