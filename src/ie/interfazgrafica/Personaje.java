/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;
import java.util.Random;
/**
 *
 * @author Mar
 */
public abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected int fuerza;
    protected int defensaBase;

    protected Arma armaActual; // opcional
    protected Bendicion fuenteDePoder; // BendicionCelestial o BendicionDelVacio
    protected int porcentajeBendicion; // 0..100 (o "maldicion" para villanos)

    // Estados por turnos
    private int venenoTurnosRestantes = 0;
    private int venenoDanioPorTurno = 0;
    private int defensaBuffTurnosRestantes = 0;
    private int defensaBuffExtra = 0;

    protected boolean yaInvocoArma = false; // solo 1 arma activa por batalla
    protected final Random rnd = new Random();


    // ArryList para guardar las armas invocadas
    protected ArrayList<Arma> armasInvocadas = new ArrayList<>();

    // ===== NUEVO: contador de ataques supremos ejecutados =====
    private int supremosUsados = 0;


    public Personaje(String nombre, int vida, int fuerza, int defensa, Bendicion fuente, int porcentajeBendicion) {
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

    public void aplicarEstadosAlInicioDelTurno() {
        if (venenoTurnosRestantes > 0) {
            vida -= venenoDanioPorTurno;
            venenoTurnosRestantes--;
            System.out.println(nombre + " sufre " + venenoDanioPorTurno + " de veneno. Vida: " + vida + " (" + venenoTurnosRestantes + " turnos de veneno restantes)");
        }
        if (defensaBuffTurnosRestantes > 0) {
            defensaBuffTurnosRestantes--;
            if (defensaBuffTurnosRestantes == 0 && defensaBuffExtra != 0) {
                System.out.println("El buff de defensa de " + nombre + " termino (-" + defensaBuffExtra + ").");
                defensaBuffExtra = 0;
            }
        }
    }

    public void recibirDanio(int danio) {
        int danioReal = Math.max(0, danio - getDefensaActual());
        vida -= danioReal;
        System.out.println(nombre + " recibe " + danioReal + " de danio. Vida: " + vida);
    }

    public void curar(int puntos) {
        if (puntos <= 0) return;
        vida += puntos;
        System.out.println(nombre + " se cura " + puntos + ". Vida: " + vida);
    }

    public void aplicarVeneno(int danioPorTurno, int turnos) {
        venenoDanioPorTurno = Math.max(venenoDanioPorTurno, danioPorTurno);
        venenoTurnosRestantes = Math.max(venenoTurnosRestantes, turnos);
        System.out.println(nombre + " fue envenenado: " + danioPorTurno + "/turno por " + turnos + " turnos.");
    }

    public void aplicarBuffDefensa(int extra, int turnos) {
        defensaBuffExtra += extra;
        defensaBuffTurnosRestantes = Math.max(defensaBuffTurnosRestantes, turnos);
        System.out.println(nombre + " gana +" + extra + " defensa por " + turnos + " turnos. Defensa actual: " + getDefensaActual());
    }

    public void atacar(Personaje enemigo) {
        int base = fuerza + (armaActual != null ? armaActual.getDanioExtra() : 0);
        System.out.println(nombre + " ataca a " + enemigo.nombre + (armaActual != null ? " con " + armaActual.getNombre() : " a puno limpio") + ".");
        enemigo.recibirDanio(base);
        if (armaActual != null) {
            armaActual.usarEfectoEspecial(enemigo);
        }
    }

    public void invocarArma() {
        if (yaInvocoArma) {
            System.out.println(nombre + " ya tiene un arma activa: " + armaActual.getNombre());
            return;
        }
        armaActual = fuenteDePoder.decidirArma(porcentajeBendicion);
        if (armaActual != null) {
            armaActual.setPortador(this);
            yaInvocoArma = true;
            System.out.println(nombre + " invoca " + armaActual.getNombre() + " (bendicion " + porcentajeBendicion + "%).");
        } else {
            System.out.println(nombre + " no pudo invocar un arma.");
        }
    }

    public abstract void decidirAccion(Personaje enemigo);

    @Override
    public String toString() {
        return nombre + " [vida=" + vida + ", fuerza=" + fuerza + ", defensa=" + getDefensaActual() + ", arma=" + (armaActual!=null? armaActual.getNombre():"-") + ", %bend/mald=" + porcentajeBendicion + "]";

        return nombre + " [vida=" + vida + ", fuerza=" + fuerza + ", defensa=" + getDefensaActual()
                + ", arma=" + (armaActual!=null? armaActual.getNombre():"-")
                + ", %bend/mald=" + porcentajeBendicion
                + ", supremosUsados=" + supremosUsados + "]";

    }

}
