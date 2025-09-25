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
     protected AtaqueSupremo ataqueSupremo;
    public Heroe(String nombre, int vida, int fuerza, int defensa, int porcentajeBendicion) {
        super(nombre, vida, fuerza, defensa, new BendicionCelestial(), porcentajeBendicion);
        if (porcentajeBendicion == 100) {
            this.ataqueSupremo = new CastigoBendito(this);
        }
    }

    @Override
    public void decidirAccion(Personaje enemigo) {
        
        if (ataqueSupremo != null) {
            ataqueSupremo.ejecutar(enemigo);
            ataqueSupremo = null;
            
        } else if (!yaInvocoArma) {
            invocarArma();
            
        } else {
            atacar(enemigo);
        }
    }
}
