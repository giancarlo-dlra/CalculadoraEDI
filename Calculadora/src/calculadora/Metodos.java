/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocalculadora;

import java.util.StringTokenizer;

/**
 *
 * @author miguela.monreal
 */
public abstract class Metodos extends Pila {
     // FASE 1: Validación de la expresión  
    public static boolean validacion(String expresion) {
        boolean res = true;
        String aux1, aux2;
        StringTokenizer token = new StringTokenizer(expresion);

        // Condiciones mínimas 
        if (token.countTokens() < 3 || !balanceoParentesis(expresion)) {
            res = false;
        } else {
            aux1 = token.nextToken();
            if (!number(aux1) && !pIzquierdo(aux1)) {
                res = false;
            } else {

                // ANALISIS
                while (token.hasMoreTokens() && res) {

                    aux2 = token.nextToken();

                    if (mathSymbol(aux1)) {// Si +  - / * 

                        if (pDerecho(aux2) || mathSymbol(aux2)) {
                            res = false;
                        }

                    } else {

                        if (number(aux1)) {// si es número 

                            if (pIzquierdo(aux2) || number(aux2)) {
                                res = false;
                            }

                        } else {

                            if (pIzquierdo(aux1)) {// si es paréntesis izq 

                                if (mathSymbol(aux2) || pDerecho(aux2)) {
                                    res = false;
                                }

                            } else {// si es parentesis derecho 

                                if (pIzquierdo(aux2) || number(aux2)) {
                                    res = false;
                                }

                            }

                        }

                    }

                    if (token.hasMoreTokens()) {
                        aux1 = token.nextToken();
                        if (!token.hasMoreTokens()) {

                            if (pIzquierdo(aux1) || mathSymbol(aux1)) {
                                res = false;
                            }

                        }
                    } else // ya acabaste con los tokens 
                    if (!pDerecho(aux2)) {
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
        Pila<Character> stack = new Pila<Character>();
        int i = 0;
        while (i < cad.length() && res) {
            if (cad.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (cad.charAt(i) == ')') {
                    if (stack.isEmpty()) {
                        res = false;
                    } else {
                        stack.pop();
                    }
                }
            }
            i++;
        }
        if (!stack.isEmpty()) {
            res = false;
        }
        return res;
    }

    // Me dice si un String es número o no 
    private static boolean number(String cad) {
        boolean res;
        try {
            Double.parseDouble(cad);
            res = true;
        } catch (Exception e) {
            res = false;
        }
        return res;
    }

    // Me dice si un String es operador o no 
    private static boolean mathSymbol(String cad) {
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

    // Me dice si es paréntesis derecho 
    private static boolean pDerecho(String cad) {
        boolean res = false;
        if (cad.charAt(0) == ')') {
            res = true;
        }
        return res;
    }

    // Me dice si es paréntesis izquierdo 
    private static boolean pIzquierdo(String cad) {
        boolean res = false;
        if (cad.charAt(0) == '(') {
            res = true;
        }
        return res;
    }
}
