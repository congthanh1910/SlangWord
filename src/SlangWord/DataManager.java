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
        System.out.println("Enter an definition: ");
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
    public void add()throws IOException, NullPointerException{
        List<String> listMeaning = new ArrayList<>();
        System.out.println("Enter an slang word new:");
        String key = scanner.nextLine();
        List<String> meaningSlangWordNew = new ArrayList<>();

        while(true){
            System.out.println("Enter a meaning slang word new: 'y' if you done");
            String done = scanner.nextLine();
            if (done.equals("y")){
                break;
            }
            meaningSlangWordNew.add(done);
        }

        Boolean isExist = word_list_slang.containsKey(key);
        String type = null;
        if (isExist){
            System.out.println("1. Overwrite");
            System.out.println("2. Duplicate");
            type = scanner.nextLine();
        }
        else{
            word_list_slang.put(key, meaningSlangWordNew);
            System.out.println("Add new slang word successfully");
        }

        if(type == null){
            return;
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
        System.out.println("Add new slang word successfully");
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
    public void backup()throws IOException{
        word_list_slang = data.back_up_data();
        System.out.println("Reset data successfully");
    }
    public void random(){
        Object[] crunchifyKeys = word_list_slang.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
        System.out.println("*** Random slang word *** \n");
        System.out.println("Slang word: " + key);
        System.out.println("Meaning: ");
        for (int i = 0; i < word_list_slang.get(key).size(); i++){
            System.out.println((i+1) + ". " + word_list_slang.get(key).get(i));
        }
    }

    public void quizSlang(){
        Object[] keySlang = word_list_slang.keySet().toArray();
        Object key = keySlang[new Random().nextInt(keySlang.length)];

        Object[] keyDefinition = word_list_definition.keySet().toArray();
        Object answer1 = keyDefinition[new Random().nextInt(keyDefinition.length)];
        Object answer2 = keyDefinition[new Random().nextInt(keyDefinition.length)];
        Object answer3 = keyDefinition[new Random().nextInt(keyDefinition.length)];
        System.out.println("*** Quiz show *** \n");
        System.out.println("Meaning of : " + key);

        Random generator = new Random();
        int value = generator.nextInt(word_list_slang.get(key).size());
        List<String> listAnswer = new ArrayList<>();
        listAnswer.add(word_list_slang.get(key).get(value));
        listAnswer.add(answer1.toString());
        listAnswer.add(answer2.toString());
        listAnswer.add(answer3.toString());
        Collections.shuffle(listAnswer);

        for (int i = 1; i <= 4; i++){
            System.out.println("Answer "+ i +": " + listAnswer.get(i-1));
        }

        System.out.println("Your answer : ");
        boolean AnswerUserValid = false;
        String[] answerValid = {"1", "2", "3", "4"};
        boolean checkUserAnswer = false;

        while(AnswerUserValid == false){
            String answerUser = scanner.nextLine();
            for (String i: answerValid){
                if(answerUser.equals(i)){
                    AnswerUserValid = true;
                }
            }
            if(AnswerUserValid == false){
                System.out.println("Answer invalid!!");
                System.out.println("Please enter the correct value.");
            }
            else{
                checkUserAnswer = checkAnswer(key.toString(), listAnswer.get(Integer.parseInt(answerUser)-1), "slang");
                break;
            }
        }

        if(checkUserAnswer){
            System.out.println("Congratulations, correct answer.");
        }
        else{
            System.out.println("Sorry, the answer is wrong.");
        }
    }
    public void quizDefinition(){
        Object[] keyDefinition = word_list_definition.keySet().toArray();
        Object key = keyDefinition[new Random().nextInt(keyDefinition.length)];

        Object[] keySlang = word_list_slang.keySet().toArray();
        Object answer1 = keySlang[new Random().nextInt(keySlang.length)];
        Object answer2 = keySlang[new Random().nextInt(keySlang.length)];
        Object answer3 = keySlang[new Random().nextInt(keySlang.length)];

        System.out.println("*** Quiz show *** \n");
        System.out.println("Slang of word : " + key);

        Random generator = new Random();
        int value = generator.nextInt(word_list_definition.get(key).size());
        List<String> listAnswer = new ArrayList<>();
        listAnswer.add(word_list_definition.get(key).get(value));
        listAnswer.add(answer1.toString());
        listAnswer.add(answer2.toString());
        listAnswer.add(answer3.toString());
        Collections.shuffle(listAnswer);

        for (int i = 1; i <= 4; i++){
            System.out.println("Answer "+ i +": " + listAnswer.get(i-1));
        }
        System.out.println("Your answer : ");
        boolean AnswerUserValid = false;
        String[] answerValid = {"1", "2", "3", "4"};
        boolean checkUserAnswer = false;

        while(AnswerUserValid == false){
            String answerUser = scanner.nextLine();
            for (String i: answerValid){
                if(answerUser.equals(i)){
                    AnswerUserValid = true;
                }
            }
            if(AnswerUserValid == false){
                System.out.println("Answer invalid!!");
                System.out.println("Please enter the correct value.");
            }
            else{
                checkUserAnswer = checkAnswer(key.toString(), listAnswer.get(Integer.parseInt(answerUser)-1), "definition");
                break;
            }
        }

        if(checkUserAnswer){
            System.out.println("Congratulations, correct answer.");
        }
        else{
            System.out.println("Sorry, the answer is wrong.");
        }
    }
    public boolean checkAnswer(String key, String answer, String type){
        List<String> listDefinition= new ArrayList<>();
        if (type.equals("slang")){
            listDefinition = word_list_slang.get(key);
        }

        if (type.equals("definition")){
            listDefinition = word_list_definition.get(key);
        }

        for (String i: listDefinition){
            if (answer.equals(i)){
                return true;
            }
        }
        return false;
    }
    public int continueProgram(String order){
        String[] orderValid = {"y", "n"};
        for (String i: orderValid){
            if (!order.equals(i)){
                return -1;
            }
        }
        if (order.equals("y")){
            return 1;
        }
        return 0;
    }
}
