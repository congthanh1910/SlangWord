package SlangWord;

import java.io.*;
import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void isClear(){
        System.out.println("\nEnter \"enter\" key to continue");
        String continueprogram = scanner.nextLine();
    }

    public static void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) throws IOException {
        DataManager dataManager = new DataManager();
        clrscr();
        String choose = null;
        boolean exit = false;

        while (true) {
            showMenu();
            choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    dataManager.searchSlangWord();
                    isClear();
                    break;
                case "2":
                    dataManager.searchDefinition();
                    isClear();
                    break;
                case "3":
                    dataManager.searchHistory();
                    isClear();
                    break;
                case "4":
                    dataManager.add();
                    isClear();
                    break;
                case "5":
                    dataManager.edit();
                    isClear();
                    break;
                case "6":
                    dataManager.delete();
                    isClear();
                    break;
                case "7":
                    dataManager.backup();
                    isClear();
                    break;
                case "8":
                    dataManager.random();
                    isClear();
                    break;
                case "9":
                    dataManager.quizSlang();
                    isClear();
                    break;
                case "10":
                    dataManager.quizDefinition();
                    isClear();
                    break;
                case "0":
                    System.out.println("Good bye!!!");
                    exit = true;
                    break;
                default:
                    System.out.println("invalid! please choose action in below menu:");
                    break;
            }
            if (exit) {
                dataManager.saveSearchHistory();
                dataManager.saveSlangWord();
                break;
            }
        }

    }
    public static void showMenu() {
        clrscr();
        System.out.println("-----------Menu------------");
        System.out.println("1. Search Slang Word");
        System.out.println("2. Search Slang Word by definition");
        System.out.println("3. Search history");
        System.out.println("4. Add new slang word");
        System.out.println("5. Edit slang word");
        System.out.println("6. Delete slang word");
        System.out.println("7. Reset data slang word");
        System.out.println("8. Random a slang word");
        System.out.println("9. Quiz show slang");
        System.out.println("10. Quiz show definition");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}