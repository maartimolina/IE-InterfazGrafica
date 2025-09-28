/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

public class Villano extends Personaje {

    private boolean leviatanActivo = false;
    private int leviatanTurnosRestantes = 0;
    private Personaje leviatanObjetivo;

    public Villano(String nombre, int vida, int fuerza, int defensa, int porcentajeBendicion) {
        super(nombre, vida, fuerza, defensa, new BendicionDelVacio(), porcentajeBendicion);
    }

    // Inicia el casteo del Leviatán
    public void iniciarCasteoLeviatan(Personaje objetivo, int turnos) {
        leviatanActivo = true;
        leviatanTurnosRestantes = turnos;
        leviatanObjetivo = objetivo;
    }

    @Override
    public void aplicarEstadosAlInicioDelTurno() {
        super.aplicarEstadosAlInicioDelTurno();

        if (leviatanActivo) {
            // caster muerto → interrumpido
            if (!this.estaVivo()) {
                IEInterfazGrafica.registrarEventoEspecial(
                    getNombre() + " invoco \"Leviatan del Vacio\" (interrumpido)"
                );
                leviatanActivo = false;
                leviatanObjetivo = null;
                return;
            }

            // objetivo muerto → interrumpido
            if (leviatanObjetivo == null || !leviatanObjetivo.estaVivo()) {
                IEInterfazGrafica.registrarEventoEspecial(
                    getNombre() + " invoco \"Leviatan del Vacio\" (interrumpido: objetivo caido)"
                );
                leviatanActivo = false;
                leviatanObjetivo = null;
                return;
            }

            // avanza casteo
            leviatanTurnosRestantes--;

            // impacto al terminar
            if (leviatanTurnosRestantes <= 0) {
                int danio = leviatanObjetivo.getVida(); // 100% de la vida actual
                leviatanObjetivo.recibirDanioDirecto(danio);
                IEInterfazGrafica.registrarEventoEspecial(
                    getNombre() + " invoco \"Leviatán del Vacio\" → " + danio + " de danio"
                );
                leviatanActivo = false;
                leviatanObjetivo = null;
            }
        }
    }

    @Override
    public void decidirAccion(Personaje enemigo) {
        // Si no tiene arma → invoca sí o sí
        if (armaActual == null) {
            invocarArma();
            return;
        }

        // Si llega a 100% bendición y no usó supremo → castea Leviatán
        if (!leviatanActivo && porcentajeBendicion == 100 && getSupremosUsados() == 0) {
            new LeviatanDelVacio(this).ejecutar(enemigo);
            return;
        }

        // 20% de probabilidad de invocar otra arma, si no ataca
        if (rnd.nextDouble() < 0.2) {
            invocarArma();
        } else {
            atacar(enemigo);
        }
    }
}