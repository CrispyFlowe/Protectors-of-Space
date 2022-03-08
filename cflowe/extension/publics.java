package cflowe.extension;


import java.awt.*;
import java.awt.MouseInfo;

import java.util.List;
import java.util.ArrayList;



public class publics {


    public static boolean True = true;
    public static boolean False = false;

    public static double mouse_position_x(){
        Point current_mouse = MouseInfo.getPointerInfo().getLocation();
        return current_mouse.getX();
    }

    

    
    public static double mouse_position_y(){
        Point current_mouse = MouseInfo.getPointerInfo().getLocation();
        return current_mouse.getY();
    }

    // public static boolean mouse_click(){
    //     Point current_mouse = MouseInfo.getPointerInfo().getLocation();
    //     return true;
    // }


    // <>overload
    public static double mouse_position_x(double window_x){
        return mouse_position_x() - window_x;
    }

    // <>overload
    public static double mouse_position_y(double window_y){
        return mouse_position_x() - window_y;
    }

    

    
    // public static int match_value(Object text){
    //     return (Integer.valueOf(text));
    // }

    // function print
    // switch (text.getClass().getSimpleName()){
    //     case "String":
    //         System.out.println(String.valueOf(text));
    //         break;

    //     case "Integer":
    //         break;

    // }




    /**
     * <li> publics.println
     * <li> <>tags
     * <li> print texts
     * 
     * @param texts
     * @return 1 - exit accidently: the length of texts args is zero, you need to input arg
     */
    //

    

    public static int println(Object ... texts){
        // text
        if (texts.length < 0){
            return 1; 
        }
        
        for (Object text: texts){
            
            System.out.println(String.valueOf(text));
        }
        
        return 0;
    }

    



    public static int printf(String text, Object ... args){
        System.out.printf(text, args);
        return 0;
    }

    public static void show(Object ... arg){
        // do nothing
    }


    
    /**
    * from number, to number
    * return random double between two number
    */
    public static double rand_double(double from, double to){
        return (Math.random() * to) + from;
    }



    /**
     * return current game time in mills
     * @return
     */
    public static long nowtime(){
        return System.currentTimeMillis();
    }

    public static long nowtimesec(){
        return 1000L;
    }



    /**
     * 
     * this is use to load images
     * if number of image you need to load > 2, you can use this to load image
     *
     * @param url
     * @return
     */ 
    public static Image image_load(String url){

        Image img_result = Toolkit.getDefaultToolkit().getImage(url);
        if (img_result == null){
            throw new IndexOutOfBoundsException();
        }
        return img_result;
    }

    // public static object random_choice(object ... args){
    //     int rand_val = random(0, args.length);
    //     return (args[rand_val]);
    // }




    

    
    // <>anno 

    /**
     * 
     * <>tags
     * Deprecated
     * 
     * public static --- for test function
     * if expression is false, return true, else (is true) return false
     * same as !()
     *
     * @param expression
     * @return
     */
    @Deprecated
    public static boolean not(boolean expression){

        return expression ? false : true;
    }

 

    /**
     * 
     * <>tags
     * Deprecated
     * 
     * object args: use to set a new Object list quickly --- for test
     *
     * @param args
     * @return
     */
    @Deprecated
    public static List<Object> new_list(Object ... args){
        
        List<Object> current_list = new ArrayList<>();
        for (Object arg: args){
            current_list.add(arg);
            
        }
        return current_list;

    }
}




