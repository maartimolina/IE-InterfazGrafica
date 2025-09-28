package ie.interfazgrafica;

import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class IEInterfazGrafica {

    // ==== HISTORIAL (ultimas 5) ====
    private static final int HISTORIAL_MAX = 5;
    private static final List<String> HISTORIAL = new ArrayList<>();
    private static final Path HIST_PATH = Paths.get("historial_batallas.txt");

    // ==== EVENTOS ESPECIALES (ataques supremos) ====
    private static final List<String> eventosEspeciales = new ArrayList<>();

    static {
        cargarHistorialDesdeDisco();
    }

    public static void registrarEventoEspecial(String evento) {
        eventosEspeciales.add(evento);
    }

    // Guarda una entrada (con tope 5) y persiste
    public static void guardarBatalla(String batalla) {
        if (HISTORIAL.size() == HISTORIAL_MAX) {
            HISTORIAL.remove(0);
        }
        HISTORIAL.add(batalla);
        guardarHistorialEnDisco();
    }

    private static void cargarHistorialDesdeDisco() {
        try {
            if (Files.exists(HIST_PATH)) {
                List<String> lines = Files.readAllLines(HIST_PATH, StandardCharsets.UTF_8);
                int start = Math.max(0, lines.size() - HISTORIAL_MAX);
                HISTORIAL.clear();
                HISTORIAL.addAll(lines.subList(start, lines.size()));
            }
        } catch (IOException e) {}
    }

    private static void guardarHistorialEnDisco() {
        try {
            Files.write(HIST_PATH, HISTORIAL, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        System.out.println("=== SISTEMA DE APODOS ===");
        System.out.print("Ingrese el apodo del Heroe (3 a 10 caracteres, solo letras y espacios): ");
        String apodoHeroe = sc.nextLine();
        while (!ValidacionApodos.esValido(apodoHeroe)) {
            System.out.print("Apodo invalido. Intente nuevamente: ");
            apodoHeroe = sc.nextLine();
        }

        System.out.print("Ingrese el apodo del Villano (3 a 10 caracteres, solo letras y espacios): ");
        String apodoVillano = sc.nextLine();
        while (!ValidacionApodos.esValido(apodoVillano)) {
            System.out.print("Apodo invalido. Intente nuevamente: ");
            apodoVillano = sc.nextLine();
        }

        System.out.println("\nHeroe: " + apodoHeroe + "  |  Villano: " + apodoVillano);
        System.out.println("==========================================");

        // ==== CREACION DE PERSONAJES ====
        Heroe heroe = new Heroe(
                apodoHeroe,
                130 + rnd.nextInt(31),
                24 + rnd.nextInt(9),
                8 + rnd.nextInt(6),
                30 + rnd.nextInt(71)
        );

        Villano villano = new Villano(
                apodoVillano,
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

        sc.close();
    }
}
