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
    protected AtaqueSupremo ataqueSupremo;
    public Villano(String nombre, int vida, int fuerza, int defensa, int porcentajeMaldicion) {
        super(nombre, vida, fuerza, defensa, new BendicionDelVacio(), porcentajeMaldicion);
        if(porcentajeMaldicion == 100){
            
            this.ataqueSupremo= new LeviatanDelVacio(this);
        }
    }

    @Override
    public void decidirAccion(Personaje enemigo) {
        if(ataqueSupremo != null && !ataqueSupremo.yaUsado()){
            ataqueSupremo.ejecutar(enemigo);
            return;
        }
        if(ataqueSupremo != null && ataqueSupremo.yaUsado()){
            ataqueSupremo=null;
        }
        if (!yaInvocoArma) {
            invocarArma();
        } else {
            atacar(enemigo);
        }
    }
}
