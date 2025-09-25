/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Mar
 */
 class EspadaSagrada extends Arma {
    public EspadaSagrada() { super("Espada Sagrada", 12); }
    @Override
    public void usarEfectoEspecial(Personaje objetivo) {
        if (portador != null) portador.curar(10);
    }
}
