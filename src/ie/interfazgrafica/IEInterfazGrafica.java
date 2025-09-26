/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ie.interfazgrafica;

import java.util.Random;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author Mar
 */
public class IEInterfazGrafica {

    // ==== HISTORIAL (últimas 5) ====
    private static final int HISTORIAL_MAX = 5;
    private static final Deque<String> HISTORIAL = new ArrayDeque<>();

    // Guarda una entrada. Si ya hay 5, descarta la más vieja.
    public static void guardarBatalla(String batalla) {
        if (HISTORIAL.size() == HISTORIAL_MAX) {
            HISTORIAL.removeFirst();
        }
        HISTORIAL.addLast(batalla);
    }

    // Muestra el historial con numeración al momento de listar
    public static void mostrarHistorial() {
        System.out.println("\n HISTORIAL MAS RECIENTE");
        if (HISTORIAL.isEmpty()) {
            System.out.println("(sin batallas registradas)");
            return;
        }
        int i = 1;
        for (String entrada : HISTORIAL) {
            System.out.println("BATALLA NRO: " + (i++) + " - " + entrada);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rnd = new Random();

        Heroe heroe = new Heroe(
                "Auron",
                130 + rnd.nextInt(31),
                24 + rnd.nextInt(9),
                8 + rnd.nextInt(6),
                30 + rnd.nextInt(71)
        );

        Villano villano = new Villano(
                "Nox",
                130 + rnd.nextInt(31),
                24 + rnd.nextInt(9),
                8 + rnd.nextInt(6),
                30 + rnd.nextInt(71)
        );

        System.out.println("ESTADO INICIAL");
        System.out.println("Heroe: " + heroe);
        System.out.println("Villano: " + villano);
        System.out.println("\nComienza la batalla\n");

        Personaje actual = heroe;
        Personaje enemigo = villano;
        int turno = 1;

        while (heroe.estaVivo() && villano.estaVivo()) {
            System.out.println("TURNO " + (turno++) + " - " + actual.getNombre());
            actual.aplicarEstadosAlInicioDelTurno();
            if (!actual.estaVivo()) {
                System.out.println(actual.getNombre() + " cayo por efectos persistentes");
                break;
            }

            actual.decidirAccion(enemigo);

            if (!enemigo.estaVivo()) {
                System.out.println("\n" + actual.getNombre() + " ha derrotado a " + enemigo.getNombre());
                break;
            }

            Personaje tmp = actual; actual = enemigo; enemigo = tmp;
            System.out.println();
        }

        System.out.println("RESULTADO FINAL");
        System.out.println("Vida del Heroe: " + heroe.getVida());
        System.out.println("Vida del Villano: " + villano.getVida());

        // ==== HISTORIAL: construir entrada y guardar ====
        int turnosTotales = turno - 1; // porque en cada iteración se hizo (turno++)
        String ganador;
        if (heroe.estaVivo() == villano.estaVivo()) {
            ganador = "Empate";
        } else {
            ganador = heroe.estaVivo() ? heroe.getNombre() : villano.getNombre();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Heroe: ").append(heroe.getNombre())
          .append(" | Villano: ").append(villano.getNombre())
          .append(" | Ganador: ").append(ganador)
          .append(" | Turnos: ").append(turnosTotales);

        guardarBatalla(sb.toString());
        mostrarHistorial();
    }
}

