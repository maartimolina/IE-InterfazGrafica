/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Valentina
 */
public class CastigoBendito extends AtaqueSupremo{
     public CastigoBendito(Personaje lanzador) {
        super("Castigo Bendito", lanzador);
    }

    @Override
    public void ejecutar(Personaje objetivo) {
        int danio = lanzador.getVida() / 2;
        System.out.println(lanzador.getNombre() + " activa " + nombre +  "hace " + danio + " de daño");
        objetivo.recibirDanio(danio);
    }
}
