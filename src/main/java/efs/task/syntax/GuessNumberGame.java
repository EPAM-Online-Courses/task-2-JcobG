package efs.task.syntax;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    private final int totalAttempts;
    private final int prize;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        //TODO: Implement the constructor
        int M;
        try{
            M = Integer.parseInt(argument);
            if(M<1||M>400){
                System.out.println(UsefulConstants.WRONG_ARGUMENT);
                throw new IllegalArgumentException(UsefulConstants.WRONG_ARGUMENT);
            }
            this.totalAttempts=calculateAttempts(M);

            System.out.println("Zakres: <1,"+M+">");
           // System.out.println("Liczba prób: "+totalAttempts);
            Random rand = new Random();
            this.prize = rand.nextInt(M)+1;
        }
        catch(NumberFormatException e){
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException(UsefulConstants.WRONG_ARGUMENT);
        }
    }
    private int calculateAttempts(int M){
        return (int)Math.floor(Math.log(M)/Math.log(2))+1;
                //Math.max(1, (int) Math.ceil(Math.log(M) / Math.log(2)));
    }

    public void play() {
        int usedAttempts = 1;
        Scanner scanner = new Scanner(System.in);
        String guess;
        while (usedAttempts <= totalAttempts) {
            progressBar(usedAttempts);
            System.out.println("PODAJ");
            guess = scanner.nextLine();
            int guessInt;
            try {
                guessInt = Integer.parseInt(guess);
                if (guessInt > prize) System.out.println(UsefulConstants.TO_MUCH);
                else if (guessInt < prize) System.out.println(UsefulConstants.TO_LESS);
                else {
                    System.out.println(UsefulConstants.YES);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
            }

            usedAttempts++;
        }
        if (usedAttempts > totalAttempts) System.out.println(UsefulConstants.UNFORTUNATELY);
        else System.out.println(UsefulConstants.CONGRATULATIONS);

    }
    private void progressBar(int usedAttempts){
        StringBuilder bar= new StringBuilder();
              bar.append("[");
        for(int i=1; i<=totalAttempts; i++){
            if(i<=usedAttempts) bar.append("*");
            else bar.append(".");
        }
        bar.append("]");
        System.out.println(bar);
    }
}
