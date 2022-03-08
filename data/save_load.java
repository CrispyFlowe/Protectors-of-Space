package data;



import java.io.*;
import java.util.List;
import java.util.ArrayList;

import cflowe.extension.publics;




public class save_load {
    
    static String dir_data = "data/";
    

    static final String SPACE = ".";

    static final boolean debug = false;

    /**
     * 
     * @param player_name
     * @param datas
     * @return
     */ 
    public static int save_data(String file_name, Object ... _datas){
        Object[] datas = _datas;

        try {
            PrintStream data_stream = new PrintStream(dir_data + file_name + "_data.txt");
            for (Object data: datas){
                data_stream.append(String.valueOf(data));
                data_stream.append(SPACE);
            }
            data_stream.close();
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        
        return 0;
    } 




    

   /**
    * read file, and return a arraylist with all data
    * <>tags
    * suppresswarnings all
    * @param file_name
    * @return 
    */
    
    public static List<Object> load_data(String file_name){
    

        

        try {
            BufferedReader data_stream = new BufferedReader(new FileReader(dir_data + file_name + "_data.txt"));
            String data = data_stream.readLine();
            data_stream.close(); 

            if (data == null){
                return null;
            }

            // int digit = 0;

            List<Object> datas = new ArrayList<>();

            String result = ""; 

            String letter = "";
            for (int i = 0; i < data.length(); i++){

                letter = String.valueOf(data.substring(i, i + 1));
                if (letter.contains(SPACE)){
                    
                    // digit += 1; 
                        if (debug){
                            publics.println(i + 1);
                        }
                    
                    if (!(result == "")){
                        // publics.println(i);
                        if (debug){
                            publics.println(result);
                        }

                         
                        int value = Integer.parseInt(result);
                            datas.add(value);
                            result = "";
                    }
                } else {
                    result = result + letter;
                }
                
            }   
            return datas; 
            
            

        } catch (Exception e) {
            // exception
            // e.printStackTrace();
        }

        
        return new ArrayList<>(); // return empty list
    

    }

    

    int backup_file(){
        return 0;
    }




    void cover_file(){
        
    }

    void move(){

    }
    
    
}







