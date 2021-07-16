/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Professor;
import java.util.ArrayList;
/**
 *
 * @author Nelson Toneze
 */
public class ProfessorController {
    public static boolean SalvarProfessor(String Disciplina, String Nome, String Email){
        Professor a = new Professor(Disciplina, Nome, Email);
        return a.Persistir();
    }
    
    public static boolean ExcluirProfessor(int indice){
        Professor a = new Professor();
        return a.Remover(indice);
    }
    
    public static boolean ModificarProfessor(int indice, String Disciplina, String Nome, String Email){
        Professor a = new Professor(Disciplina, Nome, Email);
        return a.Modificar(indice);
    }
    
    public static ArrayList<String[]> getProfessores(){
        ArrayList<String[]> Professores = new ArrayList();
        
        ArrayList<Professor> A = Professor.getProfessores();
        if(A!=null){
            for(int i=0;i<A.size();i++){
                String a[] = new String[3];
                a[0] = A.get(i).getDisciplina();
                a[1] = A.get(i).getNome();
                a[2] = A.get(i).getEmail();
                Professores.add(a);
            }
        }
        
        return Professores;
    }
}
