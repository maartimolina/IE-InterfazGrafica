/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.interfazgrafica;

/**
 *
 * @author asusb
 */
public class ValidacionApodos {
    private ValidacionApodos() {} // evita instanciacion

    // Regla: 3..10 chars, solo letras y espacios
    public static boolean esValido(String apodo) {
        if (apodo == null) return false;

        int len = apodo.length();
        if (len < 3 || len > 10) {
            System.out.println("Error: el apodo debe tener entre 3 y 10 caracteres.");
            return false;
        }

        if (!apodo.matches("[a-zA-Z ]+")) {
            System.out.println("Error: el apodo solo puede contener letras y espacios.");
            return false;
        }

        return true;
    }
}
