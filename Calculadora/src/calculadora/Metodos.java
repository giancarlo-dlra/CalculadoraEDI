/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author junca
 */
public abstract class Metodos extends PilaA {

    
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
    
    // Revisa un String y regresa si hay dos o mas puntos.
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

    // Revisa un String y regresa si es un operador o no 
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

    // Revisa un String y regresa si es un parentesis derecho
    private static boolean parentesisDer(String cad) {
        boolean res = false;
        if (cad.charAt(0) == ')') {
            res = true;
        }
        return res;
    }

    // Revisa un String y regresa si es un parentesis izquierdo
    private static boolean parentesisIzq(String cad) {
        boolean res = false;
        if (cad.charAt(0) == '(') {
            res = true;
        }
        return res;
    }
}
