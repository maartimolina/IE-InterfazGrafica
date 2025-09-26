/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ie.interfazgrafica;

import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

public class IEInterfazGrafica {

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

        System.out.println("===== ESTADO INICIAL =====");
        System.out.println("Heroe -> " + heroe);
        System.out.println("Villano -> " + villano);
        System.out.println("\nComienza la batalla!\n");

        Personaje actual = heroe;
        Personaje enemigo = villano;
        int turno = 1;

        while (heroe.estaVivo() && villano.estaVivo()) {
            System.out.println("===== TURNO " + (turno++) + " - " + actual.getNombre() + " =====");
            actual.aplicarEstadosAlInicioDelTurno();
            if (!actual.estaVivo()) {
                System.out.println(actual.getNombre() + " cayo por efectos persistentes.");
                break;
            }

            actual.decidirAccion(enemigo);

            if (!enemigo.estaVivo()) {
                System.out.println("\n" + actual.getNombre() + " ha derrotado a " + enemigo.getNombre() + "!");
                break;
            }

            // cambio de turno
            Personaje tmp = actual; actual = enemigo; enemigo = tmp;
            System.out.println();
        }

        System.out.println("\n===== RESULTADO FINAL =====");
        System.out.println("Heroe -> Vida: " + heroe.getVida());
        System.out.println("Villano -> Vida: " + villano.getVida());

        // ===== RANKING DE VIDA FINAL (solo cálculo y muestra, sin reporte) =====
        List<Personaje> participantes = Arrays.asList(heroe, villano);

        // Ordenar por vida descendente; si empatan, por nombre A–Z
        participantes.sort(
            Comparator.comparingInt(Personaje::getVida).reversed()
                      .thenComparing(Personaje::getNombre)
        );

        System.out.println("\n--- RANKING DE VIDA FINAL ---");
        int pos = 1;
        for (Personaje p : participantes) {
            String estado = p.getVida() > 0 ? "OK" : "KO";
            // Si tu clase Personaje tiene getSupremosUsados(), mostramos ese dato; si no, podés quitar esa parte.
            System.out.println(
                pos++ + ") " + p.getNombre() +
                " — Vida: " + p.getVida() +
                " [" + estado + "]" +
                " | Supremos usados: " + p.getSupremosUsados()
            );
        }
    }
}

