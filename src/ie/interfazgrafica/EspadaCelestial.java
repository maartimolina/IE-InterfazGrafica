/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Mar
 */
class EspadaCelestial extends Arma {
    public EspadaCelestial() { super("Espada Celestial", 20); }
    @Override
    public void usarEfectoEspecial(Personaje objetivo) {
        if (portador != null) {
            portador.curar(15);
            portador.aplicarBuffDefensa(5, 3);
        }
    }
}
