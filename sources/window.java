package sources;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
// import java.util.List;
import java.util.*;
import java.util.Map;

import cflowe.extension.*;
import data.save_load;



public class window extends JFrame {

    static window window_obj = new window();
    static boolean running = true;
    
    public static int mouse_x = 0;
    public static int mouse_y = 0;
    public static KeyEvent current_key;

      
    public static final boolean DEBUG = true;
    public static final boolean DEBUG_PRINT = true;

    public static boolean mouse_down = false;
 

    

    public static final Color pen_color = Color.BLACK;
    

    /**
     * window_x = 320
     */
    public static int window_x = 320;
    /**
     * window_y = 170
     */
    public static int window_y = 170;

    public static Map<KeyEvent, Boolean> key_pressed = new HashMap<>();

    static final int WIDTH = 800;
    static final int HEIGHT = 600; // 600 bigscreen: 1000 

    public int launch(){


    
        this.setVisible(true);
        this.setSize(WIDTH, HEIGHT);

        this.setLocationRelativeTo(null);
        this.setTitle("Protectors of Space");

     
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event){ 
                super.windowClosing(event);
                save_load.save_data(
                    game.UNAME, 
                    player_stat.play_time, game_obj.player_obj.coin, game_obj.player_obj.type,
                    game_obj.player_obj.main_gun_level, 
                    game_obj.player_obj.side_cannon_level,
                    game_obj.player_obj.wings_level, 
                    
                    game_obj.game_music ? 1 : 0, game_obj.game_soundsfx ? 1 : 0
                    ); 
                publics.println("save success, closing window...");
                System.exit(0); 
            }
        });
        


        // add listener to window
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event){
                super.mouseClicked(event);
                mouse_x = event.getX();
                mouse_y = event.getY();

                // if mouse left button clicked
                if (event.getButton() == 1){
                    mouse_x = event.getX();
                    mouse_y = event.getY();
                    
                     
                }  
            }

            @Override
            public void mousePressed(MouseEvent event){
                super.mousePressed(event);
                mouse_down = true;
            }
            

            
            @Override
            public void mouseReleased(MouseEvent event){
                super.mouseReleased(event);
                mouse_down = false;
            }

            
        });

            
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent event){
                super.keyPressed(event);


                if (!(key_pressed.containsKey(event))){
                    key_pressed.put(event, true);
                }



                if (event.getKeyCode() == KeyEvent.VK_X){
                // <>print
                    System.out.println("mouse x: " + (publics.mouse_position_x() - window_x));
                    System.out.println("mouse y: " + (publics.mouse_position_y() - window_y));
                }

                game.game_begin_flag = true;

                
                switch (event.getKeyCode()){


                    case KeyEvent.VK_SPACE:
                        game.game_begin_flag = true;
                        player.shoot_flag = true;
                        break;
                    
                    case KeyEvent.VK_F:
                        player.shoot_flag = true;
                        break;

                    case KeyEvent.VK_0:
                        // print mspt
                        System.out.println(game.MSPT);
                        // running = false;
                        break;
                    
                    case KeyEvent.VK_I:
                        player.heal_amount = 3;
                        break;

                    case KeyEvent.VK_L:
                        // fast fire
                        player.fast_fire_flag = true;
                        player.invul_flag = true;
                        break;

                    case KeyEvent.VK_P:
                         
                        game_obj.level_obj.level_pause = game_obj.level_obj.level_pause ? false : true;
                        break;

                    case KeyEvent.VK_O:
                        
                        enemy.objects.clear();
                        break;

                    case KeyEvent.VK_K:
                        game_obj.player_obj.show_launch_flag = game_obj.player_obj.show_launch_flag ? true : false;
                        break;
                       
                
                        
                    case KeyEvent.VK_CAPS_LOCK:
                        // <>value true
                        // disable auto fire (default)
                        player.auto_fire_flag = false;
                        break;
                        
                    
                } // end switch
                
            
            
            }


            @Override
            public void keyReleased(KeyEvent event){
                super.keyReleased(event);

                if (key_pressed.containsKey(event)){
                    key_pressed.remove(event);
                }
            }

        
            
        
        
        });





       

        return 0;
    }
    
    game game_obj = new game();

    // <>default
    Image offscreen_canvas;

    public static boolean is_on_screen(double x, double y){
        if (x > WIDTH || x < 0 || y > HEIGHT || y < 0){
            return false;
        } else {
            return true;
        }
    }

    public static boolean is_on_screen_x(double x){
        if (x > WIDTH || x < 0){
            return false;
        } else {
            return true;
        }
    }

    public static boolean is_on_screen_y(double y){
        if (y > HEIGHT || y < 0){
            return false;
        } else {
            return true;
        }
    }


    
    

    @Override
    public void paint(Graphics pen){
        offscreen_canvas = this.createImage(WIDTH, HEIGHT);
        Graphics offscreen_pen = offscreen_canvas.getGraphics();
        

        game_obj.proc(offscreen_pen);

        pen.drawImage(offscreen_canvas, 0, 0, null);

    }


    public void loop(){
        // do: repaint();
        // do print: System.out.println("refresh"); new pen
        game_obj.init();
        
        while (running){
            repaint();
            // do repaint (Graphics pen manager)
            // repaint method 0
        }
        
    }
}

