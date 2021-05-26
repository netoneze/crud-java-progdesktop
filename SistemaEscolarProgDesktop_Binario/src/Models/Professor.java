/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author Nelson Toneze
 */
public class Professor implements Serializable {
    private static final long serialVersionUID = 0x5a9e1a0049694c7aL;
    private String disciplina;
    private String Nome;
    private String Email;
    private static FileInputStream fileIS;
    private static String nomeArquivo = "Professor.bin";
    
    public Professor(){
    }
    
    public Professor(String disciplina, String nome, String email){
        this.disciplina = disciplina;
        this.Nome = nome;
        this.Email = email;
    }
    
    /**
     * @return the disciplina
     */
    public String getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.Nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.Email = email;
    }
    
    public boolean Persistir(String Disciplina, String Nome, String Email) throws FileNotFoundException, IOException{
        Professor professor = new Professor();
        professor.setDisciplina(Disciplina);
        professor.setNome(Nome);
        professor.setEmail(Email);
       
        try (ObjectOutputStream escreve = new ObjectOutputStream(new FileOutputStream(nomeArquivo, true))) {
            escreve.writeObject(professor);
        }
       
        return true;
    }
    
    public boolean Remover(int indice) throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<Professor> base = getProfessores();
        
        base.remove(indice);
        
        //Limpa o arquivo para reescrever os objetos 
        new FileOutputStream("Professor.bin").close();
        
        //Reescreve os objetos 
        int tamanhoBase = base.size();
        for(int i=0 ; i < tamanhoBase ; i++){
            Persistir(base.get(i).getDisciplina(), base.get(i).getNome(), base.get(i).getEmail());
        }      
       
        return true;
    }
    
    public boolean Modificar(int indice) throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<Professor> base = getProfessores();
        
        base.set(indice, this);
        
        //Limpa o arquivo para reescrever os objetos 
        new FileOutputStream("Professor.bin").close();
        
        //Reescreve os objetos 
        int tamanhoBase = base.size();
        for(int i=0 ; i < tamanhoBase ; i++){
            Persistir(base.get(i).getDisciplina(), base.get(i).getNome(), base.get(i).getEmail());
        }      
       
        return true;
    }
    
    public static ArrayList<Professor> getProfessores() throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Professor> professores = new ArrayList();
        
        try {
            fileIS = new FileInputStream("Professor.bin");
        } catch (FileNotFoundException ex){
            // Cria um arquivo vazio, caso não existir
            File file = new File("Professor.bin");
            file.createNewFile();
            fileIS = new FileInputStream("Professor.bin");
        }
                
        while(fileIS.available()>0){
            ObjectInputStream ois = new ObjectInputStream(fileIS);
            Object object = ois.readObject();
            Professor professor = (Professor) object;
            professores.add(professor);
        }
        
        return professores;
    }
}
