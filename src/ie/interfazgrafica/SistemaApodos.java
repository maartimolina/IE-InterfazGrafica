/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Usuario
 */
import java.util.Scanner;  //importamos scanner

public class SistemaApodos {
      public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Creamos el objeto Scanner para leer entradas

        System.out.print("Ingrese la cantidad de jugadores: "); 
        int n = sc.nextInt(); // Guardamos la cantidad de jugadores
        sc.nextLine(); // Limpiamos el buffer (porque nextInt deja un "Enter" pendiente)

        String[] apodos = new String[n]; // Creamos un array para guardar los apodos de todos los jugadores

     // Recorremos a cada jugador
        for (int i = 0; i < n; i++) {
            String apodo;

            System.out.print("Jugador " + (i + 1) + ", ingrese su apodo (3 a 10 caracteres, solo letras y espacios): ");
            apodo = sc.nextLine(); // Leemos el primer apodo


            apodos[i] = apodo; // Guardamos el apodo válido en el array
        }

        // Mostramos todos los apodos aceptados
        System.out.println("\nLista de apodos aceptados:");
        for (int i = 0; i < n; i++) {
            System.out.println("Jugador " + (i + 1) + ": " + apodos[i]);
        }
    }
      

}
