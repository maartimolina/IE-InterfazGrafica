/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Usuario
 */
import java.util.Scanner; // usamos scanner

public class SistemaApodos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de jugadores: ");
        int n = sc.nextInt();
        sc.nextLine(); // limpia el Enter pendiente

        String[] apodos = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Jugador " + (i + 1) + ", ingrese su apodo (3 a 10 caracteres, solo letras y espacios): ");
            String apodo = sc.nextLine();

            // Mientras no sea valido, volver a pedir
            while (!ValidacionApodos.esValido(apodo)) {
                System.out.print("Apodo invalido. Intente nuevamente: ");
                apodo = sc.nextLine();
            }

            apodos[i] = apodo;
        }

        System.out.println("\nLista de apodos aceptados:");
        for (int i = 0; i < n; i++) {
            System.out.println("Jugador " + (i + 1) + ": " + apodos[i]);
        }

        sc.close();
    }
}
