/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;

/**
 * @version 2
 * @author Juan Carlos Uscanga, Giancarlo De la Rosa, Abrahan Martinez, Pedro Yosue, Raul Lopez 
 */

/**
 * <pre>
 * Clase ExcepcionColeccionVacia
 * </pre>
 */

public class ExcepcionColeccionVacia extends RuntimeException {
        public ExcepcionColeccionVacia(){
        super("Coleccion Vacia");
    }
    
    public ExcepcionColeccionVacia(String mensaje){
        super(mensaje);
    }
}
