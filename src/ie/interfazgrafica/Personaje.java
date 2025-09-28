package ie.interfazgrafica;

import java.util.Random;
import java.util.ArrayList;

public abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected int fuerza;
    protected int defensaBase;

    protected Arma armaActual; // arma equipada
    protected Bendicion fuenteDePoder; // BendicionCelestial o BendicionDelVacio
    protected int porcentajeBendicion; // 0..100

    // Estados por turnos
    private int venenoTurnosRestantes = 0;
    private int venenoDanioPorTurno = 0;
    private int defensaBuffTurnosRestantes = 0;
    private int defensaBuffExtra = 0;

    protected final Random rnd = new Random();

    // Lista de todas las armas invocadas (para el reporte)
    protected ArrayList<Arma> armasInvocadas = new ArrayList<>();

    // Contador de ataques supremos ejecutados
    private int supremosUsados = 0;

    public Personaje(String nombre, int vida, int fuerza, int defensa,
                     Bendicion fuente, int porcentajeBendicion) {
        this.nombre = nombre;
        this.vida = vida;
        this.fuerza = fuerza;
        this.defensaBase = defensa;
        this.fuenteDePoder = fuente;
        this.porcentajeBendicion = Math.max(0, Math.min(100, porcentajeBendicion));
    }

    public boolean estaVivo() { return vida > 0; }

    public int getDefensaActual() { return defensaBase + defensaBuffExtra; }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public Arma getArmaActual() { return armaActual; }
    public ArrayList<Arma> getArmasInvocadas() { return armasInvocadas; }

    // ===== Supremos =====
    public void registrarSupremoUsado() {
        supremosUsados++;
    }

    public int getSupremosUsados() {
        return supremosUsados;
    }

    public void aplicarEstadosAlInicioDelTurno() {
        if (venenoTurnosRestantes > 0) {
            vida -= venenoDanioPorTurno;
            venenoTurnosRestantes--;
            if (vida < 0) vida = 0;
        }
        if (defensaBuffTurnosRestantes > 0) {
            defensaBuffTurnosRestantes--;
            if (defensaBuffTurnosRestantes == 0) {
                defensaBuffExtra = 0;
            }
        }

        // Aumenta bendición/maldición cada turno hasta 100
        if (porcentajeBendicion < 100) {
            porcentajeBendicion += 10; // podés ajustar la velocidad
            if (porcentajeBendicion > 100) {
                porcentajeBendicion = 100;
            }
        }
    }

    public void recibirDanio(int danio) {
        int danioReal = Math.max(0, danio - getDefensaActual());
        vida -= danioReal;
        if (vida < 0) vida = 0;
    }

    public void recibirDanioDirecto(int danio) {
        vida -= danio;
        if (vida < 0) vida = 0;
    }

    public void curar(int puntos) {
        if (puntos > 0) {
            vida += puntos;
        }
    }

    public void aplicarVeneno(int danioPorTurno, int turnos) {
        venenoDanioPorTurno = Math.max(venenoDanioPorTurno, danioPorTurno);
        venenoTurnosRestantes = Math.max(venenoTurnosRestantes, turnos);
    }

    public void aplicarBuffDefensa(int extra, int turnos) {
        defensaBuffExtra += extra;
        defensaBuffTurnosRestantes = Math.max(defensaBuffTurnosRestantes, turnos);
    }

    public void atacar(Personaje enemigo) {
        int base = fuerza + (armaActual != null ? armaActual.getDanioExtra() : 0);
        enemigo.recibirDanio(base);
        if (armaActual != null) {
            armaActual.usarEfectoEspecial(enemigo);
        }
    }

    // Invocar arma: permite múltiples invocaciones y las guarda para el reporte
    public void invocarArma() {
        Arma nueva = fuenteDePoder.decidirArma(porcentajeBendicion);
        if (nueva != null) {
            nueva.setPortador(this);
            armaActual = nueva;                 // se equipa el arma nueva
            armasInvocadas.add(nueva);          // se guarda para el reporte
         
        } else {
            System.out.println(nombre + " no pudo invocar un arma.");
        }
    }

    // Cada subclase define su estrategia de turno
    public abstract void decidirAccion(Personaje enemigo);

    @Override
    public String toString() {
        return nombre + " [vida=" + vida + ", fuerza=" + fuerza
                + ", defensa=" + getDefensaActual()
                + ", arma=" + (armaActual != null ? armaActual.getNombre() : "-")
                + ", %bend/mald=" + porcentajeBendicion
                + ", supremosUsados=" + supremosUsados + "]";
    }
}