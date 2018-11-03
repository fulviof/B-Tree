/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b.tree;

/**
 *
 * @author fulviofanelli
 */
public class Main {
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        BplusTree arvre = new BplusTree();
        
        arvre.inserir(1, 0);
        arvre.inserir(4, 0);
        arvre.inserir(7, 0);
        arvre.inserir(10, 0);
        arvre.inserir(17, 0);
        arvre.inserir(21, 0);
        arvre.inserir(31, 0);
        arvre.inserir(32, 0);
        arvre.inserir(33, 0);
        /*arvre.inserir(25, 0);
        arvre.inserir(19, 0);
        arvre.inserir(20, 0);
        arvre.inserir(28, 0);
        arvre.inserir(42, 0);*/
        
        arvre.inOrdem(arvre.getRaiz());
        
        System.out.println("");
        
        
    }
    
    
}
