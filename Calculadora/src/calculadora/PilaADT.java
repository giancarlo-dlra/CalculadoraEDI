/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package calculadora;

/**
 *@version 2
 * @author Juan Carlos Uscanga, Giancarlo De la Rosa, Abrahan Martinez, Pedro Yosue, Raul Lopez 
 */

/**
 * 
 * Interface de pila 
 * @param <T> 
 */
public interface PilaADT <T> {
    
    public void push(T dato);
    public T pop();
    public boolean isEmpty();
    public T peek();
    public void multiPop(int n);
    
}
