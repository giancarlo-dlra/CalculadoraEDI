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
public class EmptyCollectionException extends RuntimeException{
      public EmptyCollectionException()  {
    }

   public EmptyCollectionException(String string){
       super(string);
   }
    
}
