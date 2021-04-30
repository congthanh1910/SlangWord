package SlangWord;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("data.txt");
        while (true)
        {
            int i = fr.read();
            if (i==-1)
                break;
            char ch =(char) i;
            System.out.print(ch);
        }
        fr.close();
    }
}
