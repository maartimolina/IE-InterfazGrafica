package ie.interfazgrafica;

import java.util.*;

public class IEInterfazGrafica {

    // ==== HISTORIAL (últimas 5) ====
    private static final int HISTORIAL_MAX = 5;
    private static final List<String> HISTORIAL = new ArrayList<>();

    // ==== EVENTOS ESPECIALES (ataques supremos) ====
    private static final List<String> eventosEspeciales = new ArrayList<>();

    // Registrar evento especial (ataque supremo usado)
    public static void registrarEventoEspecial(String evento) {
        eventosEspeciales.add(evento);
    }

    // Guarda una entrada en el historial
    public static void guardarBatalla(String batalla) {
        if (HISTORIAL.size() == HISTORIAL_MAX) {
            HISTORIAL.remove(0); // saca la más vieja
        }
        HISTORIAL.add(batalla);
    }

    public static void main(String[] args) {
        Random rnd = new Random();

        Heroe heroe = new Heroe(
                "Pumpi",
                130 + rnd.nextInt(31),
                24 + rnd.nextInt(9),
                8 + rnd.nextInt(6),
                30 + rnd.nextInt(71)
        );

        Villano villano = new Villano(
                "LordDark",
                130 + rnd.nextInt(31),
                24 + rnd.nextInt(9),
                8 + rnd.nextInt(6),
                30 + rnd.nextInt(71)
        );

        Personaje actual = heroe;
        Personaje enemigo = villano;
        int turno = 1;

        // ==== COMBATE ====
        while (heroe.estaVivo() && villano.estaVivo()) {
            actual.aplicarEstadosAlInicioDelTurno();
            if (!actual.estaVivo()) break;

            actual.decidirAccion(enemigo);

            if (!enemigo.estaVivo()) break;

            Personaje tmp = actual; actual = enemigo; enemigo = tmp;
            turno++;
        }

        // ==== HISTORIAL ====
        int turnosTotales = turno;
        String ganador = heroe.estaVivo() ? heroe.getNombre() : villano.getNombre();

        String resumen = "Heroe: " + heroe.getNombre()
                       + " | Villano: " + villano.getNombre()
                       + " | Ganador: " + ganador
                       + " | Turnos: " + turnosTotales;

        guardarBatalla(resumen);

        // ==== REPORTE FINAL ====
        String reporte = Reportes.generar(heroe, villano, eventosEspeciales, HISTORIAL, turnosTotales);
        System.out.println(reporte);
    }
}