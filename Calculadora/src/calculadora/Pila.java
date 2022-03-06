/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocalculadora;

/**
 *
 * @author miguela.monreal
 */
public class Pila<T> implements PilaADT<T>  {
    private final int CAPACIDAD_INICIAL = 100;
    private int tope;
    private T[] pila;

    public Pila() {
        tope = 0;
        pila = (T[]) (new Object[CAPACIDAD_INICIAL]);
    }

    public Pila(int capacidadInicial) {
        tope = 0;
        pila = (T[]) (new Object[capacidadInicial]);
    }

    public void push(T elemento) {
        if (tope == pila.length) {
            expandirCapacidad();
        }
        pila[tope] = elemento;
        tope++;
    }
    
    private void expandirCapacidad() {
        T[] masGrande = (T[]) (new Object[pila.length * 2]);
        int i;
        for (i = 0; i < pila.length; i++) {
            masGrande[i] = pila[i];
        }
        pila = masGrande;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyCollectionException("Pila vacía");
        } else {
            tope--;
            T res = pila[tope];
            pila[tope] = null;
            return res;
        }
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyCollectionException("Pila vacía");
        } else {
            
        }
        return pila[tope-1]; 

    }
    
    public boolean isEmpty() {
        boolean res = false;
        if (tope == 0) {
            res = true;
        }
        return res;
    }
}
