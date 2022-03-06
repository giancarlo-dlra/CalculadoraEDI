/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.StringTokenizer;

/**
 *
 * @author junca
 */
public class Controlador extends PilaA {
    
    //clase que 
public static String infijaAPostfija(String expresion){
        StringBuilder postfija= new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(expresion);
        PilaADT<Character> aux= new PilaA();
        //PilaADT<Double> aux1= new Pila();
        
        //cadnum para numero positivos y cadNumN para negativos
        String cadnum, cadNumN;
        double numero;
        
        for(int i=0; i< expresion.length();i++){
            //Si es un numero positivo
            if(Character.isDigit(expresion.charAt(i))) {
                cadnum=expresion.charAt(i)+"";
                while(i!=expresion.length()-1 && (expresion.charAt(i+1)=='.' || Character.isDigit(expresion.charAt(i+1))) ) {
                    cadnum = cadnum + expresion.charAt(i+1);
                    i++;
                }
                //numero=Double.parseDouble(cadnum);
                postfija.append(cadnum+" ");    
            }
            else { //Si es un número negativo
                if(expresion.charAt(i)=='¨' && Character.isDigit(expresion.charAt(i+1))) {
                                cadNumN=expresion.charAt(i)+"";
                                while(i!=expresion.length()-1 && (expresion.charAt(i+1)=='.' || Character.isDigit(expresion.charAt(i+1))) ) {
                                    cadNumN = cadNumN + expresion.charAt(i+1);
                                    i++;
                                }
                                //numero=Double.parseDouble(cadnum);
                                postfija.append(cadNumN+" "); 
                }
                else { //si es un (
                    if(expresion.charAt(i)=='(') {
                    aux.push('(');
                    }
                    else { //si es un )
                        if(expresion.charAt(i)==')') {
                            while(!aux.isEmpty() && !aux.peek().equals('(')) {
                                postfija.append(aux.pop());
                            }
                            if(aux.peek().equals('('))
                                aux.pop();
                        }
                        else { //si es multiplicación o división
                            if(expresion.charAt(i)=='/' || expresion.charAt(i)=='*') {
                                if(!aux.isEmpty()) 
                                    while(!aux.isEmpty() && !aux.peek().equals('(') && !aux.peek().equals('+') && !aux.peek().equals('-'))
                                        postfija.append(aux.pop());
                                aux.push(expresion.charAt(i));
                            }
                            else { //si es suma o resta
                                if(expresion.charAt(i)=='+' || expresion.charAt(i)=='-') {
                                    if(!aux.isEmpty()) 
                                        while(!aux.isEmpty() && !aux.peek().equals('(')) 
                                            postfija.append(aux.pop());
                                    aux.push(expresion.charAt(i));
                                }
                            }
                        }
                    }
                }
                
            }
            
        }
        while(!aux.isEmpty()) { //Por si quedaron elementos en la pila
            if(aux.peek().equals('('))
                aux.pop();
            else 
                postfija.append(aux.pop()); 
        }
        
        return postfija.toString();
    }
    
    //Metodo para hacer operaciones
    public static double calcula(double var1, double var2, char operador) {
        double resp=-1;
        switch(operador) {
            case '+':
                resp=var1+var2;
                break;
            case '-':
                resp=var2-var1;
                break;
            case '*':
                resp=var1*(var2);
                break;
            case '/':
                resp=var2/var1;
                break;
        }
        return resp;
    }
    
   /*
    public static String infijaAPostfija2(String expresion){
        StringBuilder postfija= new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(expresion);
        PilaADT<Character> aux= new PilaA();
        //PilaADT<Double> aux1= new Pila();
        String cadnum, cadNumN;
        double numero;
        
        for(int i=0; i< expresion.length();i++){
            //Si es un numero positivo
            if(Character.isDigit(expresion.charAt(i))) {
                cadnum=expresion.charAt(i)+"";
                while(i!=expresion.length()-1 && (expresion.charAt(i+1)=='.' || Character.isDigit(expresion.charAt(i+1))) ) {
                    cadnum+=expresion.charAt(i+1);
                    i++;
                }
                //numero=Double.parseDouble(cadnum);
                postfija.append(cadnum+" ");    
            }
            else {
                if(expresion.charAt(i)=='(') {
                    aux.push('(');
                }
                else {
                    if(expresion.charAt(i)==')') {
                        while(!aux.isEmpty() && !aux.peek().equals('(')) {
                            postfija.append(aux.pop());
                        }
                        if(aux.peek().equals('('))
                            aux.pop();
                    }
                    else {
                        if(expresion.charAt(i)=='/' || expresion.charAt(i)=='*') {
                            if(!aux.isEmpty()) 
                                while(!aux.isEmpty() && !aux.peek().equals('(') && !aux.peek().equals('+') && !aux.peek().equals('-'))
                                    postfija.append(aux.pop());
                            aux.push(expresion.charAt(i));
                        }
                        else {
                            if(expresion.charAt(i)=='+' || expresion.charAt(i)=='-') {
                                if(!aux.isEmpty()) 
                                    while(!aux.isEmpty() && !aux.peek().equals('(')) 
                                        postfija.append(aux.pop());
                                aux.push(expresion.charAt(i));
                            }
                            //Negativos
                            if(expresion.charAt(i)=='¨' && Character.isDigit(expresion.charAt(i+1))) {
                                cadNumN=expresion.charAt(i)+"";
                                while(i!=expresion.length()-1 && (expresion.charAt(i+1)=='.' || Character.isDigit(expresion.charAt(i+1))) ) {
                                    cadNumN+=expresion.charAt(i+1);
                                    i++;
                                }
                                //numero=Double.parseDouble(cadnum);
                                postfija.append(cadNumN+" "); 
                            }  
                        }
                    }
                }
            }
            
        }
        while(!aux.isEmpty()) {
            if(aux.peek().equals('('))
                aux.pop();
            else 
                postfija.append(aux.pop()); 
        }
        
        return postfija.toString();
    

    }
    */
        //evaluar en postfija
    public static String evaluaPostfija(String expresion){
        PilaADT <String> pila = new PilaA();
        for (int i = 0; i < expresion.length(); i++) {
            //si es un operador positivo
            if(Character.isDigit(expresion.charAt(i))){
                String token = expresion.charAt(i)+"";
                while( i!= expresion.length()-1 && expresion.charAt(i+1)!=' '){
                    token+=expresion.charAt(i+1);
                    i++;
                }
                pila.push(token.toString());
            }
            else{
                //si es un operador negativo
                if(expresion.charAt(i)=='~' && Character.isDigit(expresion.charAt(i+1))){
                        String tokenNeg=("~"+expresion.charAt(i+1)+"");
                            i++;
                        while( i!= expresion.length()-1 && (Character.isDigit(expresion.charAt(i+1)) || expresion.charAt(i+1)=='.')){
                            tokenNeg=tokenNeg+(expresion.charAt(i+1));
                            i++;
                        }
                        String tokenNegS=tokenNeg.substring(1);
                        double tokenDouble= (Double.parseDouble(tokenNegS))*(-1);
                        pila.push(tokenDouble+"");
                }
                else{
                    //si incide en el espacio
                    if(expresion.charAt(i)==' '){
                    }
                    //calcular con los operando
                    else{
                        double resp,op1,op2;
                        op1=0;
                        op2=0;
                        if(!pila.isEmpty()){
                            op1=Double.parseDouble(pila.pop());
                            if(!pila.isEmpty()){
                                op2=Double.parseDouble(pila.pop());
                            }
                        }
                        resp=calcula(op1,op2,expresion.charAt(i));
                        pila.push(resp+"");
                    } 
                }    
            }        
    }
        return new java.text.DecimalFormat("0.000").format(Double.parseDouble(pila.pop()));
    } 
    
    

    public static void main(String[] args) {
        String expresion="(23+¨45)*6.3/70";
        System.out.println("Infija: "+expresion);
        System.out.println("Postfija: "+infijaAPostfija(expresion));
        expresion="45*(56/¨36)+95";
        System.out.println("Infija: "+expresion);
        System.out.println("Postfija: "+infijaAPostfija(expresion));
        System.out.println(" ");
        
    }
    
    
    }

    


