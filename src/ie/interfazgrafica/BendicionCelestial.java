/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author Mar
 */
 class BendicionCelestial implements Bendicion {
    @Override
    public Arma decidirArma(int p) {
        if (p >= 80) return new EspadaCelestial();
        if (p >= 40) return new EspadaSagrada();
        return new EspadaSimple();
    }
    @Override public String getNombre() { return "Bendicion Celestial"; }
}
