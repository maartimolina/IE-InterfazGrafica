/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Mar
 */
public class LeviatanDelVacio extends AtaqueSupremo {
    public LeviatanDelVacio(Personaje lanzador) {
        super("Leviatan del Vacio", lanzador);
    }

    @Override
    public void ejecutar(Personaje objetivo) {
        int danio = lanzador.getVida() / 2;
        objetivo.recibirDanio(danio);
        registrarUso(danio); // 👈 solo esto, ya se encarga de registrar evento y contador
    }
}
