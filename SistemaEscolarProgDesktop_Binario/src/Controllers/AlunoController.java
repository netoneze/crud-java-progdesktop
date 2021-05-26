/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Aluno;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Nelson Toneze
 */
public class AlunoController {
    private static final long serialVersionUID = 0x5a9e1a0049694c7aL;
    
    public static boolean SalvarAluno(String registroAcademico, String Nome, String Email) throws IOException{
        Aluno a = new Aluno();
        return a.Persistir(registroAcademico, Nome, Email);
    }
    
    public static boolean ExcluirAluno(int indice) throws IOException, FileNotFoundException, ClassNotFoundException{
        Aluno a = new Aluno();
        return a.Remover(indice);
    }
   
    public static boolean ModificarAluno(int indice, String registroAcademico, String Nome, String Email) throws IOException, FileNotFoundException, ClassNotFoundException{
        Aluno a = new Aluno(registroAcademico, Nome, Email);
        return a.Modificar(indice);
    }
    
    public static ArrayList<String[]> getAlunos() throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<String[]> Alunos = new ArrayList();
        
        ArrayList<Aluno> A = Aluno.getAlunos();
        if(A!=null){
            for(int i=0;i<A.size();i++){
                String a[] = new String[3];
                a[0] = A.get(i).getRegistroAcademico();
                a[1] = A.get(i).getNome();
                a[2] = A.get(i).getEmail();
                Alunos.add(a);
            }
        }
        
        return Alunos;
    }
}
