/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Mar
 */
 abstract class Arma {
    protected String nombre;
    protected int danioExtra;
    protected Personaje portador;

    public Arma(String nombre, int danioExtra) {
        this.nombre = nombre;
        this.danioExtra = danioExtra;
    }

    public String getNombre() { return nombre; }
    public int getDanioExtra() { return danioExtra; }

    public void setPortador(Personaje p) { this.portador = p; }

    public abstract void usarEfectoEspecial(Personaje objetivo);

    @Override
    public String toString() {
        return nombre + "(extra=" + danioExtra + ")";
    }
}
