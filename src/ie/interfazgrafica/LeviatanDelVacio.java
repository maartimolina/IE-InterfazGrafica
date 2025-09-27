/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Mar
 */
public class LeviatanDelVacio extends AtaqueSupremo{
    private int turnosRestantes= 3;
    public LeviatanDelVacio(Personaje lanzador){
        super("Leviatan del Vacio", lanzador);
        
    }
    @Override
    public void ejecutar(Personaje objetivo){
        if(turnosRestantes > 0){
            System.out.println(lanzador.getNombre()+" invocó al" + nombre + " quedan" + turnosRestantes + " turnos");
            turnosRestantes--;
        }else{
            int danio= objetivo.getVida();
            System.out.println(nombre + " hace" + danio);
            objetivo.recibirDanio(danio);
            turnosRestantes=3;
        }
    }
}
