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
    protected boolean usado=false;

    public AtaqueSupremo(String nombre, Personaje lanzador) {
        this.nombre = nombre;
        this.lanzador = lanzador;
    }
    
    public boolean yaUsado(){return usado;}
    public String getNombre() { return nombre; }

    public abstract void ejecutar(Personaje objetivo);

}
