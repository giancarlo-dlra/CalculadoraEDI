/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;

/**
 *
 * @author junca
 */
public class ExcepcionColeccionVacia extends RuntimeException {
        public ExcepcionColeccionVacia(){
        super("Coleccion Vacia");
    }
    
    public ExcepcionColeccionVacia(String mensaje){
        super(mensaje);
    }
}
