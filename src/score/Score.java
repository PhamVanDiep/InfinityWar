package score;

import java.io.*;
import java.util.Scanner;

public class Score {

    private String score;
    private final String SCORE_PATH = "D:\\Demo1\\src\\score\\score.txt";

    public Score(){
        score = null;
    }

    public String getScore() throws FileNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(SCORE_PATH);
        Scanner scanner = new Scanner(fileInputStream);

        try {
            while (scanner.hasNextLine()) {
                //System.out.println(scanner.nextLine());
                score = scanner.next();
            }
        } finally {
            try {
                scanner.close();
                fileInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
       return score;
    }

    public void setHighestScore(String score) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(SCORE_PATH);
        writer.print("");
        writer.print(score);
        writer.close();
    }
}
