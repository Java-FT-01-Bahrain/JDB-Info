import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<String> superheroes = new ArrayList<>(); //create a list of superheroes

        superheroes.add("Spiderman");
        superheroes.add("Batman");
        superheroes.add("Superman");

        for (String superhero : superheroes)
            System.out.println(superhero);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object to take input from user
        System.out.println("Enter the number of your favorite superhero");

        int num = Integer.parseInt(scanner.nextLine()); //parse a string into an integer

        try {
            System.out.println(superheroes.get(num));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The number you entered is invalid. Please enter from 0 to 2");
        }

    }

}