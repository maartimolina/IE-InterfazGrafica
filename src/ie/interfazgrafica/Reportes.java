package ie.interfazgrafica;

import java.util.List;

public class Reportes {

    public static String generar(Personaje heroe, Personaje villano,
                                 List<String> eventosEspeciales,
                                 List<String> historial, int turnosTotales) {
        StringBuilder sb = new StringBuilder();

        // clamp por las dudas
        int vidaH = Math.max(0, heroe.getVida());
        int vidaV = Math.max(0, villano.getVida());

        sb.append("===========================================\n");
        sb.append("        REPORTE FINAL DE LA BATALLA\n");
        sb.append("===========================================\n");
        sb.append("Heroe: apodo ").append(heroe.getNombre())
          .append(" - Vida final: ").append(vidaH).append("\n");
        sb.append("Villano: apodo ").append(villano.getNombre())
          .append(" - Vida final: ").append(vidaV).append("\n\n");

        // --- ARMAS INVOCADAS ---
        sb.append("--- ARMAS INVOCADAS ---\n");
        sb.append(heroe.getNombre()).append(": ")
          .append(formatearArmas(heroe.getArmasInvocadas())).append("\n");
        sb.append(villano.getNombre()).append(": ")
          .append(formatearArmas(villano.getArmasInvocadas())).append("\n\n");

        // --- ATAQUES ESPECIALES ---
        sb.append("--- ATAQUES ESPECIALES ---\n");
        if (eventosEspeciales.isEmpty()) {
            sb.append("(ninguno)\n");
        } else {
            for (String ev : eventosEspeciales) sb.append(ev).append("\n");
        }
        sb.append("\n");

        // --- HISTORIAL RECIENTE ---
        sb.append("--- HISTORIAL RECIENTE ---\n");
        for (int i = 0; i < historial.size(); i++) {
            sb.append("BATALLA #").append(i + 1).append(" - ")
              .append(historial.get(i)).append("\n");
        }
        sb.append("=======================================\n");

        return sb.toString();
    }

    private static String formatearArmas(List<Arma> armas) {
        if (armas.isEmpty()) return "(ninguna)";
        java.util.LinkedHashMap<String,Integer> conteo = new java.util.LinkedHashMap<>();
        for (Arma a : armas) conteo.put(a.getNombre(), conteo.getOrDefault(a.getNombre(), 0) + 1);

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (var e : conteo.entrySet()) {
            if (!first) sb.append(", ");
            sb.append(e.getKey()).append(" (").append(e.getValue()).append(")");
            first = false;
        }
        return sb.toString();
    }
}