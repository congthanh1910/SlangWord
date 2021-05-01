package SlangWord;

import java.io.*;
import java.util.*;

public class Data {
    //private static final String DATA_FILE_NAME_BACKUP = "slang.txt";
    private static final String DATA_FILE_NAME_BACKUP = "test_backup.txt";
    //private static final String DATA_FILE_NAME = "data.txt";
    private static final String DATA_FILE_NAME = "test.txt";
    private static final String DATA_FILE_NAME_SEARCH_HISTORY = "searchHistory.txt";

    public HashMap<String, List<String>> read_slang_word()throws IOException{
        HashMap<String, List<String>> slang_word = new HashMap<String, List<String>>();
        BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_NAME));
        String str;
        br.readLine();
        while (true)
        {
            str = br.readLine();
            if (str==null)
                break;

            String[] parts = str.split("`");
            String[] meaning = parts[1].split("[|]");

            List<String> myList = new ArrayList<String>();
            for (int i = 0; i < meaning.length; i++) {
                myList.add(meaning[i].trim());
            }
            slang_word.put(parts[0], myList);

        }
        br.close();
        return slang_word;
    }
    public HashMap<String, List<String>> read_definition()throws IOException{
        HashMap<String, List<String>> definition = new HashMap<String, List<String>>();
        BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_NAME));
        String str;
        br.readLine();
        while (true)
        {
            str = br.readLine();
            if (str==null)
                break;

            String[] parts = str.split("`");
            String[] meaning = parts[1].split("[|]");

            for (int i = 0; i < meaning.length; i++) {
                List<String> myList = new ArrayList<String>();
                String key = meaning[i].trim();
                Boolean isExist = definition.containsKey(key);
                if (isExist){
                    myList = definition.get(meaning[i].trim());

                    myList.add(parts[0]);
                    definition.put(meaning[i].trim(), myList);
                }
                else{
                    myList.add(parts[0]);
                    definition.put(meaning[i].trim(), myList);
                }


            }
        }
        br.close();
        return definition;
    }
    public List<String> read_search_history()throws IOException{
        List<String> list_search_history = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_NAME_SEARCH_HISTORY));
        String str;
        while (true)
        {
            str = br.readLine();
            if (str==null)
                break;

            list_search_history.add(str);
        }
        return list_search_history;
    }
    public void write_search_history(List<String> list)throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE_NAME_SEARCH_HISTORY));

        for (int i = 0; i < list.size(); i++){
            bw.write(list.get(i));
            bw.newLine();
        }
        bw.close();
    }
    public void write_slang_word(HashMap<String, List<String>> list)throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE_NAME));
        bw.write("Slag`Meaning");
        bw.newLine();
        for (String key: list.keySet()){
            bw.write(key);
            bw.write("`");
            for (int i = 0; i < list.get(key).size(); i++){
                bw.write(list.get(key).get(i));
                if (i != list.get(key).size() - 1){
                    bw.write("|");
                }
            }
            bw.newLine();
        }
        bw.close();

    }
    public HashMap<String, List<String>> back_up_data()throws IOException{
        HashMap<String, List<String>> slang_word_backup = new HashMap<String, List<String>>();
        BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_NAME_BACKUP));
        String str;
        br.readLine();
        while (true)
        {
            str = br.readLine();
            if (str==null)
                break;

            String[] parts = str.split("`");
            String[] meaning = parts[1].split("[|]");

            List<String> myList = new ArrayList<String>();
            for (int i = 0; i < meaning.length; i++) {
                myList.add(meaning[i].trim());
            }
            slang_word_backup.put(parts[0], myList);

        }
        br.close();
        return slang_word_backup;
    }
}
