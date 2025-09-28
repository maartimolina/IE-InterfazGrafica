/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Mar
 */
public abstract class AtaqueSupremo {
        protected String nombre;
    protected Personaje lanzador;
    protected boolean usado = false; // para controlar si ya se ejecutó

    public AtaqueSupremo(String nombre, Personaje lanzador) {
        this.nombre = nombre;
        this.lanzador = lanzador;
    }

    public abstract void ejecutar(Personaje objetivo);

    // Marcar como usado
    protected void registrarUso(int danio) {
        lanzador.registrarSupremoUsado();
        usado = true; // importante: marcarlo al usar
        IEInterfazGrafica.registrarEventoEspecial(
            lanzador.getNombre() + " activo \"" + nombre + "\" --> " + danio + " de danio"
        );
    }

    // Getter para que Villano pueda consultar
    public boolean yaUsado() {
        return usado;
    }
}
