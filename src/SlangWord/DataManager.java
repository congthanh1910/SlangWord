package SlangWord;

import java.io.*;
import java.util.*;

public class DataManager {
    public static Scanner scanner = new Scanner(System.in);
    HashMap<String, List<String>> word_list_slang;
    HashMap<String, List<String>> word_list_definition;
    List<String> list_search_history;
    private Data data;

    public DataManager() throws IOException {
        data = new Data();
        word_list_slang = data.read_slang_word();
        word_list_definition = data.read_definition();
        list_search_history = data.read_search_history();
    }

    public void saveSlangWord()throws IOException{
        data.write_slang_word(word_list_slang);
    }

    public void searchSlangWord() {
        System.out.println("Enter an slang word:");
        String text= scanner.nextLine();
        list_search_history.add(text);

        List<String> myList2 = new ArrayList<String>();
        myList2 = word_list_slang.get(text);
        if (myList2 == null){
            System.out.println("This word does not exist.");
        }
        else{
            for (int i = 0; i < myList2.size(); i++){
                System.out.println("Meaning " + (i+1) + ": " + myList2.get(i));
            }
        }
    }
    public void searchDefinition() {
        System.out.println("Enter an slang word:");
        String text= scanner.nextLine();

        List<String> myList2 = new ArrayList<String>();
        for (String i : word_list_definition.keySet()) {
            if (i.contains(text))
            {
                myList2.add("Definition: " + (i) + "\nSlang: " + word_list_definition.get(i) + "\n");
            }
        }
        for (int i = 0; i < myList2.size(); i++){
            System.out.println((i+1) + "." + myList2.get(i));
        }
    }

    public void searchHistory (){
        for (int i = 0; i < list_search_history.size(); i++){
            System.out.println((i+1) + ": " + list_search_history.get(i));
        }
    }
    public void saveSearchHistory()throws IOException{
        data.write_search_history(list_search_history);
    }
    public void add()throws IOException{
        List<String> listMeaning = new ArrayList<>();
        System.out.println("Enter an slang word new:");
        String key = scanner.nextLine();
        List<String> meaningSlangWordNew = new ArrayList<>();

        while(true){
            System.out.println("Enter a meaning slang word new: 'y' if you done");
            String meaing = scanner.nextLine();
            if (meaing.equals("y")){
                break;
            }
            meaningSlangWordNew.add(meaing);
        }

        Boolean isExist = word_list_slang.containsKey(key);
        String type = null;
        if (isExist){
            System.out.println("1. Overwrite");
            System.out.println("2. Duplicate");
            System.out.println("Cancel");
            type = scanner.nextLine();
        }
        else{
            word_list_slang.put(key, meaningSlangWordNew);
        }


        if (type.equals("1")){
            word_list_slang.put(key, meaningSlangWordNew);
        }
        else if (type.equals("2")){
            List<String> myList2 = new ArrayList<String>();
            myList2 = word_list_slang.get(key);
            for (int i = 0; i < meaningSlangWordNew.size(); i++){
                myList2.add(meaningSlangWordNew.get(i));
            }
            word_list_slang.put(key, myList2);
        }
    }
    public void edit(){
        System.out.println("Enter an slang word want edit:");
        String key = scanner.nextLine();

        Boolean isExist = word_list_slang.containsKey(key);
        if (!isExist){
            System.out.println("Slang word does not exist");
            return;
        }

        List<String> meaingNewList = new ArrayList<>();
        while(true){
            System.out.println("Enter a meaning slang word new: 'y' if you done");
            String meaing = scanner.nextLine();
            if (meaing.equals("y")){
                break;
            }
            meaingNewList.add(meaing);
        }
        word_list_slang.put(key, meaingNewList);
        System.out.println("Slang word " + key + " update successfully");
    }
    public void delete(){
        System.out.println("Enter an slang word want delete:");
        String key = scanner.nextLine();

        Boolean isExist = word_list_slang.containsKey(key);
        if (!isExist){
            System.out.println("Slang word does not exist");
            return;
        }
        word_list_slang.remove(key);
        System.out.println("Slang word " + key + " delete successfully");
    }


}
