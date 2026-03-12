import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class GestorMediateca {
    private static void errorGenerico(){
        System.out.println("No es una opción válida");
    }

    public static void main(String[] args){


        Scanner scanner = new Scanner(System.in);

        //Lista de ejemplo
        ArrayList<ItemBiblioteca> listaMediateca = new ArrayList<>();
        listaMediateca.add(new Libro("1001", "El Rey de Amarillo", "Robert W. Chambers"));
        listaMediateca.add(new Libro("1002", "La Sombra sobre Innsmouth", "H. P. Lovecraft"));
        listaMediateca.add(new Revista("2001", "National Geographic", 23));
        listaMediateca.add(new Revista("2002", "Vogue", 12));
        listaMediateca.add(new DVD("3001", "La Cosa", "John Carpenter"));
        listaMediateca.add(new DVD("3002", "Cloverfield", "Matt Reeves"));

        boolean program = true;
        while(program){
            try {
                System.out.println("Selecciona una de las siguientes opciones: \n" +
                        "1. Mostrar catálogo\n" +
                        "2. Añadir nuevo ítem\n" +
                        "3. Modificar título\n" +
                        "4. Prestar ítem\n" +
                        "5. Devolver ítem\n" +
                        "6. Salir\n"
                );
                int opcionMenu = scanner.nextInt();
                scanner.nextLine(); //Si no se añade esta línea, la siguiente no esperará a recibir el input

                switch(opcionMenu) {
                    case 1: //Mostrar catálogo
                        for (ItemBiblioteca item : listaMediateca) {
                            System.out.println(item);
                        }
                        break;

                    case 2: //Añadir nuevo ítem
                        System.out.println("Selecciona el tipo de ítem: \n" +
                                "1. Libro\n" +
                                "2. Revista\n" +
                                "3. DVD\n" +
                                "4. Atrás\n"
                        );
                        int opcionTipoItem = scanner.nextInt();
                        scanner.nextLine(); //Si no se añade esta línea, la siguiente no esperará a recibir el input
                        if(opcionTipoItem < 1 || opcionTipoItem > 4){
                            errorGenerico();
                            break;
                        }

                        System.out.println("Escribe el ID del nuevo ítem: ");
                        String nuevoId = scanner.nextLine();
                        boolean idExiste = false;
                        for (ItemBiblioteca item : listaMediateca) {
                            if (nuevoId.equals(item.getId())) {
                                idExiste = true;
                                break;
                            }
                        }
                        if(idExiste){
                            System.out.println("Ya existe un ítem con ese ID.");
                            break;
                        }

                        System.out.println("Escribe el título del nuevo ítem: ");
                        String nuevoTitulo = scanner.nextLine();

                        switch(opcionTipoItem){
                            case 1:
                                System.out.println("Escribe el autor del nuevo ítem: ");
                                String nuevoAutor = scanner.nextLine();

                                listaMediateca.add(new Libro(nuevoId, nuevoTitulo, nuevoAutor));
                                break;

                            case 2:
                                System.out.println("Escribe el número de edición del nuevo ítem: ");
                                int nuevoNumeroEdicion = scanner.nextInt();

                                listaMediateca.add(new Revista(nuevoId, nuevoTitulo, nuevoNumeroEdicion));
                                break;

                            case 3:
                                System.out.println("Escribe el director del nuevo ítem: ");
                                String nuevoDirector = scanner.nextLine();

                                listaMediateca.add(new DVD(nuevoId, nuevoTitulo, nuevoDirector));
                                break;

                            case 4:
                                break;
                        }
                        break;

                    case 3: //Modificar título
                        System.out.println("Escribe el ID del ítem a modificar: ");
                        String idModificar = scanner.nextLine();
                        boolean modificarExiste = false;

                        for (ItemBiblioteca item : listaMediateca) {
                            if (idModificar.equals(item.getId())) {
                                modificarExiste = true;
                                System.out.println("Escribe el nuevo título del ítem: ");
                                String modificarTitulo = scanner.nextLine();
                                item.setTitulo(modificarTitulo);
                                break;
                            }
                        }
                        if(!modificarExiste){
                            System.out.println("No existe ningún ítem con este ID.");
                            break;
                        }

                        break;

                    case 4: //Prestar ítem
                        System.out.println("Escribe el ID del ítem a prestar: ");
                        String idPrestar = scanner.nextLine();
                        boolean prestarExiste = false;

                        for (ItemBiblioteca item : listaMediateca) {
                            if (idPrestar.equals(item.getId())) {
                                prestarExiste = true;

                                if (item.getEstadoPrestado()) {
                                    System.out.println("Este ítem ya se encuentra prestado.");
                                    break;
                                }

                                item.prestar();
                                System.out.println("Se ha prestado un ejemplar de " + item.getTitulo() + "\n" +
                                        "Se podrá devolver en un plazo de " + item.getDiasMaximosPrestamo() + " días.");
                                break;
                            }
                        }
                        if(!prestarExiste) {
                            System.out.println("No existe ningún ítem con este ID.");
                        }
                        break;

                    case 5: //Devolver ítem
                        System.out.println("Escribe el ID del ítem a devolver: ");
                        String idDevolver = scanner.nextLine();
                        boolean devolverExiste = false;

                        for (ItemBiblioteca item : listaMediateca) {
                            if (idDevolver.equals(item.getId())) {
                                devolverExiste = true;

                                if (!item.getEstadoPrestado()) {
                                    System.out.println("Este ítem no está prestado en este momento.");
                                    break;
                                }

                                item.devolver();
                                System.out.println("Escribe los días que el ítem pasó prestado: ");
                                int diasPrestados = scanner.nextInt();
                                int diasRetraso = diasPrestados - item.getDiasMaximosPrestamo();
                                if (diasRetraso > 0) {
                                    System.out.println("El ítem se devolvió fuera de plazo por " + diasRetraso + " días.\n" +
                                            "La multa a pagar es de " + item.calcularMulta(diasRetraso) + " euros.");
                                } else {
                                    System.out.println("El ítem se devolvió dentro del plazo.");
                                }
                                break;
                            }
                        }
                        if(!devolverExiste) {
                            System.out.println("No existe ningún ítem con este ID.");
                        }
                        break;

                    case 6: //Salir
                        program = false;
                        break;

                    default:
                        errorGenerico();
                        break;
                }
            } catch (Exception e) {
                scanner.nextLine(); //Esta línea es para que no entre en un bucle de lanzar errores
                errorGenerico();
            }
        }
    }
}