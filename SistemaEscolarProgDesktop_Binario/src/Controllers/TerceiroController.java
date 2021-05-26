/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Terceiro;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Nelson Toneze
 */
public class TerceiroController {
    public static boolean SalvarTerceiro(String Funcao, String Nome, String Email) throws IOException{
        Terceiro a = new Terceiro();
        return a.Persistir(Funcao, Nome, Email);
    }
    
    public static boolean ExcluirTerceiro(int indice) throws IOException, FileNotFoundException, ClassNotFoundException{
        Terceiro a = new Terceiro();
        return a.Remover(indice);
    }
    
    public static boolean ModificarTerceiro(int indice, String Funcao, String Nome, String Email) throws IOException, FileNotFoundException, ClassNotFoundException{
        Terceiro a = new Terceiro(Funcao, Nome, Email);
        return a.Modificar(indice);
    }
    
    public static ArrayList<String[]> getTerceiros() throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<String[]> Terceiros = new ArrayList();
        
        ArrayList<Terceiro> A = Terceiro.getTerceiros();
        if(A!=null){
            for(int i=0;i<A.size();i++){
                String a[] = new String[3];
                a[0] = A.get(i).getFuncao();
                a[1] = A.get(i).getNome();
                a[2] = A.get(i).getEmail();
                Terceiros.add(a);
            }
        }
        
        return Terceiros;
    }
}
