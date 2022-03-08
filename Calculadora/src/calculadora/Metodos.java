/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *@version 2
 * @author Juan Carlos Uscanga, Giancarlo De la Rosa, Abrahan Martinez, Pedro Yosue, Raul Lopez 
 */


/**
<pre>
* Clase Metodos
* 
* Coleccion de metodos utilizados para verificar y confirmar el uso correcto de la sintaxis en la operacion.
* </pre>
*/

public abstract class Metodos extends PilaA {

    /**
     * Metodo que revisa si la sintaxis de la expresion es correcta
     * Regresa true si la expresion esta correctamente escrita
     * Regresa false si la expresion tiene un error de sintaxis
     * @param expresion
     * @return true o false
     */
    public static boolean evaluaSintaxis(String expresion) {
        boolean res = true;
        String aux, aux2;
        StringTokenizer token = new StringTokenizer(expresion);

        // Condiciones mínimas 
        if (token.countTokens() < 3 || !balanceoParentesis(expresion)) {
            res = false;
        } else {
            aux = token.nextToken();
            if (!numero(aux) && !parentesisIzq(aux)) {
                res = false;
            } else {

                // analisis
                while (token.hasMoreTokens() && res == true) {

                    aux2 = token.nextToken();

                    if (operador(aux)) {// Si +  - / * 

                        if (parentesisDer(aux2) || operador(aux2)) {
                            res = false;
                        }

                    } else {

                        if (numero(aux)) {// si es número 

                            if (parentesisIzq(aux2) || numero(aux2)) {
                                res = false;
                            }

                        } else {

                            if (parentesisIzq(aux)) {// si es paréntesis izq 

                                if (operador(aux2) || parentesisDer(aux2)) {
                                    res = false;
                                }

                            } else {// si es parentesis derecho 

                                if (parentesisIzq(aux2) || numero(aux2)) {
                                    res = false;
                                }

                            }

                        }

                    }

                    if (token.hasMoreTokens()) {
                        aux = token.nextToken();
                        if (!token.hasMoreTokens()) {

                            if (parentesisIzq(aux) || operador(aux)) {
                                res = false;
                            }

                        }
                    } else // ya acabaste con los tokens 
                    if (!parentesisDer(aux2)) {
                        res = false;
                    }

                }

            }

        }
        return res;
    }
    
    /**
     * Metodo que evalua el posicionamiento correcto de parentesis en la operacion
     * @param cad
     * @return true o false
     */
    // Balanceo de paréntesis 
    private static boolean balanceoParentesis(String cad) {
        boolean res = true;
        PilaA<Character> pila = new PilaA<Character>();
        int i = 0;
        while (i < cad.length() && res) {
            if (cad.charAt(i) == '(') {
                pila.push('(');
            } else {
                if (cad.charAt(i) == ')') {
                    if (pila.isEmpty()) {
                        res = false;
                    } else {
                        pila.pop();
                    }
                }
            }
            i++;
        }
        if (!pila.isEmpty()) {
            res = false;
        }
        return res;
    }
    
    /**
     * Metodo que revisa si existen dos o mas puntos seguidos en la operacion
     * @param cadena
     * @return true o false
     */
   
    public static boolean checaPunto(String cadena){
        boolean resp=false;
        int i;
        ArrayList<Character> c = new ArrayList();
        PilaA<Character> pila = new PilaA();
        for(i=0;i<cadena.length();i++){ 
            if(cadena.charAt(i)=='.'){
                pila.push(cadena.charAt(i));
            }
        }
        while(!pila.isEmpty()){
            c.add(pila.pop());
        }
        if(c.size()>1)
            resp=false;
        else
            resp=true;
        return resp;
    }

    /**
     * Metodo que revisa si el elemtno es un numero tipo double 
     * @param cad
     * @return true o false
     */
    // Revisa un String y regresa si es un double o no.
    private static boolean numero(String cad) {
        boolean res;
        try {
            Double.parseDouble(cad);
            res = true;
        } catch (Exception e) {
            res = false;
        }
        return res;
    }

    /**
     * Metodo que revisa si el elemento es un operador matematico
     * @param cad
     * @return true o false
     */
    private static boolean operador(String cad) {
        boolean res;
        char intento = cad.charAt(0);
        switch (intento) {

            case '+':
            case '-':
            case '*':
            case '/':
                res = true;
                break;
            default:
                res = false;
        }
        return res;
    }

    /**
     * Metodo que revisa si el elemento es un parentesis derecho
     * @param cad
     * @return true o false
     */
     
    // Revisa un String y regresa si es un parentesis derecho
    private static boolean parentesisDer(String cad) {
        boolean res = false;
        if (cad.charAt(0) == ')') {
            res = true;
        }
        return res;
    }

    /**
     * Metodo que revisa si el elemento es un parentesis izquierdo

     * @param cad
     * @return true o false
     */
    // Revisa un String y regresa si es un parentesis izquierdo
    private static boolean parentesisIzq(String cad) {
        boolean res = false;
        if (cad.charAt(0) == '(') {
            res = true;
        }
        return res;
    }
}