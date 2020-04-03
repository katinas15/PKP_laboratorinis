package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            File myObj = new File("C:\\Users\\l\\IdeaProjects\\untitled\\src\\com\\company\\data.txt");
            FileWriter myWriter = new FileWriter("cords.txt");
            Scanner myReader = new Scanner(myObj);
            String name = "antra Zona";
            String color = "red";
            myWriter.write(
                    "{\n" +
                            "\t\"name\": \"" + name + "\",\n" +
                            "\t\"color\": \"" + color + "\",\n" +
                    "\t\"bounds\": [\n"
            );
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(", ");
                myWriter.write("\t\t{\n" +
                        "\t\t\t\"x\": " + parts[0] + ",\n" +
                        "\t\t\t\"y\": " + parts[1] + "\n" +
                        "\t\t},\n");
            }
            myWriter.write("\t\t}\n\t]\n}");
            myReader.close();
            myWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
