/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Mar
 */
 class Heroe extends Personaje {
    public Heroe(String nombre, int vida, int fuerza, int defensa, int porcentajeBendicion) {
        super(nombre, vida, fuerza, defensa, new BendicionCelestial(), porcentajeBendicion);
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
