package SlangWord;

import java.io.*;
import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        DataManager dataManager = new DataManager();
        String choose = null;
        boolean exit = false;

        while (true) {
            showMenu();
            choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    dataManager.searchSlangWord();
                    break;
                case "2":
                    dataManager.searchDefinition();
                    break;
                case "3":
                    dataManager.searchHistory();
                    break;
                case "4":
                    dataManager.add();
                    break;
                case "5":
                    dataManager.edit();
                    break;
                case "6":
                    dataManager.delete();
                    break;
                case "7":
                    dataManager.backup();
                    break;
                case "8":
                    dataManager.random();
                    break;
                case "0":
                    System.out.println("exited!");
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
        System.out.println("\n");
        System.out.println("-----------Menu------------------------------------------------------------------");
        System.out.println("1. Search Slang Word");
        System.out.println("2. Search Slang Word by definition");
        System.out.println("3. Search history");
        System.out.println("4. Add new slang word");
        System.out.println("5. Edit slang word");
        System.out.println("6. Delete slang word");
        System.out.println("7. Reset data slang word");
        System.out.println("8. Random a slang word");
        System.out.println("9. ");
        System.out.println("10. ");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}