package SlangWord;

import java.io.*;
import java.util.*;

public class DataManager {
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

    public void searchSlangWord() {
        System.out.println("Enter an slang word:");
        Scanner scan= new Scanner(System.in);
        String text= scan.nextLine();
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
        Scanner scan= new Scanner(System.in);
        String text= scan.nextLine();

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
    public void add(){

    }
    public void edit(){}
    public void delete(){}
    public void backup(){}
    public void random(){}
}
