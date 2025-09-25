/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Mar
 */
 class HozMortifera extends Arma{
    public HozMortifera() { super("Hoz Mortifera", 18); }
    @Override
    public void usarEfectoEspecial(Personaje objetivo) {
        objetivo.aplicarVeneno(8, 3);
        if (portador != null) portador.curar(8);
    }
}
