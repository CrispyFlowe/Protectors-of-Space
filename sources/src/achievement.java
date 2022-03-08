package sources.src;





import java.util.List;
import java.util.ArrayList;



public class achievement {
    

    public static String dir_achievement = "images/achievement/";

    static List<Integer> achievements = new ArrayList<>();

    public static final int ac_protector_of_earth = 1;
    public static final int ac_pro_driver = 2;
    public static final int ac_rich_driver = 3;





    public int give_achievement(int name){
        achievements.add(name);
        return 0;
    }


}


