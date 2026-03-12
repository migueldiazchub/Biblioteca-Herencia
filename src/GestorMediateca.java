import java.util.Scanner;

public class GestorMediateca {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ItemBiblioteca libro1 = new Libro("3232", "Mondgohongo", "manuel");
        ItemBiblioteca revista1 = new Revista("3232", "Mondgohongo", 2);
        ItemBiblioteca dvd1 = new DVD("3232", "Mondgohongo", "manuel");

        System.out.println(libro1);
        System.out.println(revista1);
        System.out.println(dvd1);
    }
}