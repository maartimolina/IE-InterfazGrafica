/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Mar
 */
    class Villano extends Personaje {
    public Villano(String nombre, int vida, int fuerza, int defensa, int porcentajeMaldicion) {
        super(nombre, vida, fuerza, defensa, new BendicionDelVacio(), porcentajeMaldicion);
    }

    @Override
    public void decidirAccion(Personaje enemigo) {
        if (!yaInvocoArma) {
            invocarArma();
        } else {
            atacar(enemigo);
        }
    }
}
