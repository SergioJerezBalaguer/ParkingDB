package learning.tokioschool.parking;

import java.time.LocalDateTime;
import java.util.Scanner;

public class App {

    private static final Parking parking = new Parking();

    public static void main(String[] args) {

        boolean salir = false;
        int opcionMenu;
        System.out.println("SE HA INICIALIZADO EL SOFTWARE PARA EL CONTROL DE ACCESO DEL PARKING");
        try (Scanner sc = new Scanner(System.in)) {
            do {
                pintarMenu();

                try {
                    opcionMenu = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException ex) {
                    opcionMenu = 0;
                }

                switch (opcionMenu) {
                    case 0:
                        System.out.println("Por favor, introduzca opcion del menu en formato numerico");
                        break;
                    case 1:
                        registrarEntradaCoche(sc);
                        break;
                    case 2:
                        String matricula = registrarSalidaCoche(sc);
                        parking.cantidadAPagar(matricula);
                        break;
                    case 3:
                        parking.imprimirCochesParking();
                        break;
                    case 4:
                        parking.imprimirCochesSistema();
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no contemplada");
                }
            } while (!salir);
        } catch (Exception ex) {
            System.out.println("Error interno en el sistema");
        }
        System.out.println("SE HA PARADO EL SOFTWARE PARA EL CONTROL DE ACCESO DEL PARKING");
    }

    public static void pintarMenu() {
        System.out.println();
        System.out.println("¿Que desea hacer?");
        System.out.println("1. Registrar entrada coche");
        System.out.println("2. Registrar salida coche");
        System.out.println("3. Imprimir coches en el parking");
        System.out.println("4. Imprimir coches en el sistema");
        System.out.println("5. Salir");
        System.out.println();
    }

    public static void registrarEntradaCoche(final Scanner sc) {
        try {
            System.out.println("REGISTRAR COCHE ENTRADA");
            System.out.println("Introduzca matricula");
            String matricula = sc.nextLine();
            System.out.println("Introduzca marca");
            String marca = sc.nextLine();
            System.out.println("Introduzca modelo");
            String modelo = sc.nextLine();

            if (parking.existeCoche(matricula) && parking.getCoche(matricula).getHoraSalida() == null) {
                System.out.println("Coche actualmente dentro del parking, no se puede registrar su entrada hasta que no se registre su salida");
            } else {
                parking.putCoche(matricula, new Coche(matricula, marca, modelo, LocalDateTime.now(), null));
                System.out.println("Coche registrado correctamente en la base de datos.");
            }

            System.out.println("COCHE REGISTRADO EN EL SISTEMA. MATRICULA " + matricula + " DATOS " + parking.getCoche(matricula));

        } catch (Exception ex) {
            System.out.println("Error al registrar coche");
        }
    }

    public static String registrarSalidaCoche(final Scanner sc) {
        String matricula = null;
        try {
            System.out.println("REGISTRAR COCHE SALIDA");
            System.out.println("Introduzca matricula");
            matricula = sc.nextLine();

            if (parking.existeCoche(matricula)) {
                parking.registrarSalida(matricula);
                System.out.println("REGISTRADO SALIDA DE COCHE EN EL SISTEMA. MATRICULA " + matricula + " DATOS " + parking.getCoche(matricula));
            } else {
                System.out.println("No hay ningún coche registrado con la matricula introducida");
                matricula = null;
            }

        } catch (Exception ex) {
            System.out.println("Error al registrar salida coche");
        }
        return matricula;
    }

}
