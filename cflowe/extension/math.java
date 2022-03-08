package cflowe.extension;

// import java.util.ArrayList;
import java.util.List;



import java.util.*;



/**
 * based on java.lang.Math
 * more math method / constant and easier to use
 * 
 * @author CrispyFlowe
 */
public class math {

    public static final double TAU = Math.PI * 2;
    


    public static long floor(double number){
        return (Math.round(number - 0.5));
    }



    public static long ceil(double number){
        return (Math.round(number + 0.4999999));
    }



    /**
     * 
     * from number, to number
     * return random int between two number
     *
     * @param min
     * @param max
     * @return
     */
    public static int random(int min, int max){
        /**
         * return (int) (Math.random() * to) + from;
         */
        return (int) new Random().nextInt((max - min) + 1) + min;
    }

    

    /**
     * pick random number
     * 
     * example: randrange(1, 10, 2, 3, 4, 5)
     * // number between 1 and 10, but 2, 3, 4, 5 will not be picked
     *
     * @param min
     * @param max
     * @param ignore this number will not be picked
     * @return rand_val
     */
    public static int randrange(int min, int max, Integer ... ignore){
        /**
         * 
         */
        List<Integer> ignores = Arrays.asList(ignore);
        
        while (publics.True){
            int rand_val = random(min, max);
            if (!(ignores.contains(rand_val))){
                return rand_val;
            }
        }
        return 0;
    }

    



    /**
     * 
     * @param args
     * @return {@code Object} random object
     */
    public static Object random_choice(Object ... args){
        int rand_val = random(0, args.length - 1);
        return args[rand_val];

    }



    /**
     * random method
     * example chance(30) // 30 percent change will pick true, else false
     * 
     * @param percent
     * @return {@code boolean} true or false
     * 
     * 
     */
    public static boolean chance(double percent){
        if (math.random(0, 100) < percent){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param a triangle side opposite
     * @param b triangle side adjasent
     * @return {@code double} c triangle side hypotenuse
     */
    public static double hypotenuse(double a, double b){
        /**
         * 
         */
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
    


    

    /**
     * 
     * da, db, dx, dy
     * return distance from two objects
     *
     * @param da
     * @param db
     * @param dx
     * @param dy
     * @return
     */
    public static double distance(double da, double db, double dx, double dy){
        
        double ida = da - dx;
        double idb = db - dy;
        return (Math.sqrt((ida * ida) + (idb * idb)));
    
    }

    public static void sysout(String ... text){
        for (String t: text){
            System.out.println(t);
        }
    }


    







    /**
     * calculate average value, only accept numbers
     * @param elements numbers you want to count
     * @return {@code double}
     */
    public static double average(double ... elements){
        
        double average_val = 0;
        for (double e: elements){
            average_val += e;
        }
        return (average_val / elements.length);
        
    }

    
    @Deprecated
    public static double full_average(double full_val, int pieces){
        return 0;
    }

    



    /**
     * 
     * <>tags
     * Deprecated
     * 
     * value, percent: return a percent of a value --- for test
     *
     * @param value
     * @param percent
     * @return
     */
    @Deprecated
    public static double percent(double value, double percent){
        /**
         * 
         */
        return ((value / 100) * percent);
    }


    
    
     
    


    


    



}




