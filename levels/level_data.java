package levels;



import java.util.*;
import java.util.HashMap;
 





/**
 * level_name
 * level_id
 * level_hard_level
 * level_reward
 */

public class level_data {

    level_data(){
        
    }

    
    // public static final String
    public static final String level_id = "level_id";

    public static final String level_name = "level_name";

    public static final String level_refugees = "level_refugees";

    public static final String level_goal = "level_goal";
    public static final String level = "";
    public static final String iiii = "";


    

    public static Map<String, Object> level1 = new HashMap<>();
    public static Map<String, Object> level2 = new HashMap<>();

    public static void init(){
        level1.put(level_name, "prologue");
        level1.put(level_id, 1);
        level1.put(level_refugees, 0);
        level1.put(
            level_goal, 
            """
            ! You need to fight with enemies and survive
            - save 10 refugees
            - destroy at least 3 buildings
            if you have problem during fighting with enemy, come back instantly
            Good Luck!
            """
        );
        
        // System.out.println(level1.get(level_name));
        
        
        
    } 
     
    // std::cout << "prologue" << std::endl;
    // {
    //     System.out.println(level1.get(refugees_amout));
    // }


    // public static int init(){
    //     level_data();
    // }
    
    
}






