package cc.cannot.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhen on 4/8/2017.
 */
public class TxtTool {

    /**
     * Get data from txt list.
     *
     * @param name the name
     * @return the list
     */
    public List<String> getDataFromTxt(String name){
        List<String> list = new ArrayList<String>();

        File file = new File(name);
        try{
            if(file.isFile() && file.exists()){
                InputStreamReader isReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader buffReader = new BufferedReader(isReader);
                String lineTxt = null;
                while((lineTxt = buffReader.readLine()) != null){
                    if(lineTxt.isEmpty()==true)continue;
                    list.add(lineTxt);
                }
            }
            else{
                System.out.println("The file does not exist !!!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Write 2 txt no override int.
     *
     * @param words    the words
     * @param filePath the file path
     * @return the int
     */
    public int write2TxtNoOverride(String words, String filePath){
        File file = new File(filePath);
        try{
            if(file.exists()){
                System.out.println("The file does exist already!!!");
            }
            else{
                System.out.println("The file does not exist, we'll create it !!!");
                file.createNewFile();
            }
            FileOutputStream foStream = new FileOutputStream(filePath, true);
            foStream.write(words.getBytes());
            foStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    /**
     * Write 2 txt override int.
     *
     * @param words    the words
     * @param filePath the file path
     * @return the int
     */
    public int write2TxtOverride(String words, String filePath){
        File file = new File(filePath);
        try{
            if(file.exists()){
                System.out.println("The file does exist already, we'll override it!!!");
            }
            else{
                System.out.println("The file does not exist, we'll create it!!!");
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            bufferedWriter.write(words);
            bufferedWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }


}
