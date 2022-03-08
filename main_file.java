

import cflowe.extension.*;
import sources.window;

// import cflowe.extension.*;




/**
 * <li> <>commands or notes helps: 
 * 
 * <li> <>game over - mark where stop the game
 * <li> <>temp: temporary code - if have a better plan, will be deleted or just for debug
 * <li> <>print - mark where use print (include System.out.println or publics.print)
 * <li> <>thread - mark where start new threads
 * <li> <>tags - mark function (method) tag example: Deprecated, Warning
 * 
 * <li> not very often use: 
 * 
 * <li> <>value - temporary value
 * <li> <>default - mark where use variable default value, not necessary needed
 * <li> <>shrink - code without indentation
 * <li> <>anno - mark where use annotations, not necessary needed
 * 
 * <li> <>ness - mark this code or function is not necessary needed
 * 
 * <li> --- --- --- --- ---
 * <li> this project use return value to detect function suscess or failed: 
 * <li> return 0 - this function suscess finished. 
 * <li> return 1 - there are some problem with it, but not need to throw an error
 * 
 * example: init
 * int init(){
 *     // do something
 *     if (...){
 *         // failed to init
 *         return 1;
 *     } else {
 *         return 0;
 *     }
 * }
 * 
 * long time running function don't need a return value
 * example: loop
 * void loop(){
 *     while (true){
 *         // do something
 *     }
 * }
 * 
 * 
 */
//
/*


for (int i = 0; i < 9; i++){
    publics.println(math.random(3, 7));
}
*/


public class main_file {
    public static void main(String[] args){
        System.out.println("Hello, World!");

        publics.nowtime(); // use method from publics to prevent import not use warning
    
        // print
        
        
        
        

        window window_obj = new window();
        window_obj.launch();
        window_obj.loop();
        // game.proc();
    }
    

}



