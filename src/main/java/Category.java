import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Category {

    private int fileType;

    private HashMap<String, Integer> categoryWords = new HashMap<>();

    Category(String filename, int whichFile) {

        try {
            File f = new File("src/main/resources/categories/"+filename);

            Scanner s = new Scanner(f);

            if(whichFile == 1) {
                fileType = 1;

                while(s.hasNextLine()) {//
                    categoryWords.put(s.nextLine(),1);
                }
            }else if(whichFile == 2){
                fileType = 2;
                while(s.hasNextDouble()) {
                    categoryWords.put(s.nextLine(),1);
                }

            }
            else if(whichFile == 3){
                fileType = 3;
                while(s.hasNextLine()) {
                    categoryWords.put(s.nextLine(),1);
                }
            }else if(whichFile == 23){
                fileType = 23;
                while(s.hasNextLine()) {
                    categoryWords.put(s.nextLine(),1);
                }
            }else {
                s.close();
                fileType = -1;
                return;

            }

            s.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    HashMap getContainer(){
        return categoryWords;
    }

    int getFileType(){
        return fileType;
    }


}
