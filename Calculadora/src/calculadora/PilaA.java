/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;

/**
 *
 * @author junca
 */
public class PilaA <T> implements PilaADT<T>{
    
     private T[] elementos;
    private int tope;
    private final int MAX=20;

    public PilaA() {
        elementos= (T[])new Object[MAX];//casteo que es arreglo de tipo T
        tope=-1;//indica fila vacia
    }
    
    public PilaA(int max) {//construye clase con un maximo determinado
        elementos= (T[])new Object[max];//casteo que es arreglo de tipo T
        tope=-1;//indica fila vacia
    }
    
    public boolean isEmpty(){//nos indica si esta vacia la pila o no
        return tope==-1;//nos regresa verdadero o falso
    }

    public T[] getElementos() {
        return elementos;
    }
    
    
    /*public T peek(){//nos muestra que hay en el tope sin quitarlo, es decir ultimo objeto
        T resp = null;
        if(!isEmpty())//si no esta vacia agrega lo que hay en el tope a resp
            resp=elementos[tope];
        return resp;
    }*/
    
    public T peek(){//opcion b
        
        if(isEmpty())
            throw new ExcepcionColeccionVacia("Pila vacia. No se puede consultar.");
        else
            return elementos[tope];
    }
    public T pop(){
        if(isEmpty())
            throw new ExcepcionColeccionVacia("Pila vacia. No se puede consultar.");
            else{
                    T dato= elementos[tope];
                    elementos[tope]=null;
                    tope--;
                    return dato;
                    }
    }
    public void push(T dato){
        if (tope== elementos.length-1)//esta llena la pila
            aumentaCapacidad();
        tope++;
        elementos[tope]=dato;
    }
    private void aumentaCapacidad(){
        T[] nuevo=(T[]) new Object[elementos.length*2];
        
        for(int i=0;i<=tope;i++)
            nuevo[i]=elementos[i];
        elementos=nuevo;
    }
     public void multiPop(int n){
       if(isEmpty())
           throw new ExcepcionColeccionVacia("Pila vací­a. No se puede consultar");
       else
           if(tope>=n-1){
               for(int i=0;i<n;i++){
                   elementos[tope]=null;
                   tope--;
               }
           }
       
   }
     public boolean equals(Object obj){
          boolean sonIgual;
          PilaA<T> pila, aux1, aux2;
           T objeto1, objeto2;
          
          
          aux1= new PilaA<>();
          aux2= new PilaA<>();
         
            if(obj!=null && obj instanceof PilaA){
                sonIgual= true;
                pila= (PilaA) obj;
                while( !isEmpty() && !pila.isEmpty() && sonIgual){
                    objeto1 = pop();
                    objeto2 = pila.pop();
                    aux1.push(objeto1);
                    aux2.push(objeto2);
                    sonIgual=objeto1.equals(objeto2);
                }
                while(!aux1.isEmpty() && !aux2.isEmpty()){
                    push(aux1.pop());
                    pila.push(aux2.pop());
                }
            }
            else
                sonIgual=false;
          return sonIgual;
      
      } 

    //ej 15
     
public static <T> boolean contieneALaSegunda(PilaADT <T> pila1, PilaADT <T> pila2){
    
    boolean resp =false;
    PilaA <T> aux1 = new PilaA();
    PilaA <T> aux2 = new PilaA();
    
    T x;
    if(pila2.isEmpty() && !pila1.isEmpty()){
        return false;
    }
    
    else{
        x=pila2.peek();
        while(!pila1.isEmpty() && !pila1.peek().equals(x)){
            aux1.push(pila1.pop());
        }
        if (pila1.isEmpty()){
            resp = false;
        }else{
            while(!pila1.isEmpty() && !pila2.isEmpty() && !pila1.peek().equals(pila2.peek())){
                aux1.push(pila1.pop());
                aux2.push(pila2.pop());
            }
            if(pila2.isEmpty()){
                resp = true;
            }else{
                resp = false;
            }
        }
        while(!aux1.isEmpty()){
            pila1.push(aux1.pop());
        }
        while(!aux2.isEmpty()){
            pila1.push(aux2.pop());
        }
    }
    return resp;
}

    
    
}
