/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

public class Heroe extends Personaje {

    public Heroe(String nombre, int vida, int fuerza, int defensa, int porcentajeBendicion) {
        super(nombre, vida, fuerza, defensa, new BendicionCelestial(), porcentajeBendicion);
    }

    @Override
    public void decidirAccion(Personaje enemigo) {
        // Si no tiene arma → invoca sí o sí
        if (armaActual == null) {
            invocarArma();
            return;
        }

        // Si llegó a 100% bendición y no usó supremo → lanza Castigo Bendito
        if (porcentajeBendicion == 100 && getSupremosUsados() == 0) {
            new CastigoBendito(this).ejecutar(enemigo);
            return;
        }

        // 20% de probabilidad de invocar un arma nueva, si no ataca
        if (rnd.nextDouble() < 0.2) {
            invocarArma();
        } else {
            atacar(enemigo);
        }
    }
}
