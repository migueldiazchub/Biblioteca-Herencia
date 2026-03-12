import java.util.Scanner;
import java.util.ArrayList;

public class GestorMediateca {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ItemBiblioteca libro1 = new Libro("101", "Mondgohongo", "manuel");
        ItemBiblioteca revista1 = new Revista("201", "Mondgohongo", 2);
        ItemBiblioteca dvd1 = new DVD("301", "Mondgohongo", "manuel");

        ArrayList<ItemBiblioteca> listaMediateca = new ArrayList<>();
        listaMediateca.add(libro1);
        listaMediateca.add(revista1);
        listaMediateca.add(dvd1);

        Boolean program = true;
        while(program){

            System.out.println("Selecciona una de las siguientes opciones: \n" +
                    "1. Mostrar catálogo\n" +
                    "2. Añadir nuevo ítem\n" +
                    "3. Modificar título\n" +
                    "4. Prestar ítem\n" +
                    "5. Devolver ítem\n" +
                    "6. Salir\n"
            );
            int opcionMenu = scanner.nextInt();

            switch(opcionMenu) {
                case 1: //Mostrar catálogo
                    for(int i = 0; i < listaMediateca.size(); i++){
                        System.out.println(listaMediateca.get(i));
                    }
                    break;

                case 2: //Añadir nuevo ítem
                    System.out.println("Selecciona el tipo de ítem: \n" +
                            "1. Libro\n" +
                            "2. Revista\n" +
                            "3. DVD\n" +
                            "4. Atrás\n"
                    );
                    int opcionAnadir = scanner.nextInt();

                    switch(opcionAnadir){}

                    break;

                case 3: //Modificar título
                    break;

                case 4: //Prestar ítem
                    System.out.println("Escribe el ID del ítem a prestar: ");
                    scanner.nextLine(); //Si no se añade esta línea, la siguiente no esperará a recibir el input
                    String idPrestar = scanner.nextLine();
                    Boolean prestarExiste = false;

                    for(int i = 0; i < listaMediateca.size(); i++){
                        ItemBiblioteca item = (listaMediateca.get(i));
                        if(idPrestar.equals(item.getId())){
                            prestarExiste = true;

                            if(item.getEstadoPrestado()){
                                System.out.println("Este ítem ya ha sido prestado.");
                                break;
                            }

                            item.prestar();
                            System.out.println("Se va a prestar un ejemplar de " + item.getTitulo() + "\n" +
                                    "Se podrá devolver en un plazo de " + item.getDiasMaximosPrestamo() + " días.");
                            break;
                        }
                    }
                    if(!prestarExiste) {
                        System.out.println("No existe ningún item con este ID.");
                    }
                    break;

                case 5: //Devolver ítem
                    System.out.println("Escribe el ID del ítem a devolver: ");
                    scanner.nextLine(); //Si no se añade esta línea, la siguiente no esperará a recibir el input
                    String idDevolver = scanner.nextLine();
                    Boolean devolverExiste = false;

                    for(int i = 0; i < listaMediateca.size(); i++){
                        ItemBiblioteca item = (listaMediateca.get(i));
                        if(idDevolver.equals(item.getId())){
                            devolverExiste = true;

                            if(!item.getEstadoPrestado()){
                                System.out.println("Este ítem no ha sido prestado.");
                                break;
                            }

                            item.devolver();
                            System.out.println("Escribe los días que el ítem pasó prestado: ");
                            int diasPrestados = scanner.nextInt();
                            int diasRetraso = diasPrestados - item.getDiasMaximosPrestamo();
                            if(diasRetraso > 0){
                                System.out.println("El ítem se devolvió fuera de plazo por " + diasRetraso + " días.\n" +
                                        "La multa a pagar es de " + item.calcularMulta(diasRetraso) + " euros.");
                            }

                            break;
                        }
                    }
                    if(!devolverExiste) {
                        System.out.println("No existe ningún item con este ID.");
                    }
                    break;

                case 6: //Salir
                    program = false;
                    break;
            }
        }
    }
}