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
public class Aluno implements Serializable {
    private static final long serialVersionUID = 0x5a9e1a0049694c7aL;
    private String registroAcademico;
    private String Nome;
    private String Email;
    private static FileInputStream fileIS;
    private static String nomeArquivo = "Aluno.bin";
    public Aluno() {
    }

    public Aluno(String RegistroAcademico, String Nome, String Email) {
        this.registroAcademico = RegistroAcademico;
        this.Nome = Nome;
        this.Email = Email;
    }
    
    /**
     * @return the registroAcademico
     */
    public String getRegistroAcademico() {
        return registroAcademico;
    }

    /**
     * @param registroAcademico the registroAcademico to set
     */
    public void setRegistroAcademico(String registroAcademico) {
        this.registroAcademico = registroAcademico;
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
    
    public boolean Persistir(String RegistroAcademico, String Nome, String Email) throws FileNotFoundException, IOException{
        Aluno aluno = new Aluno();
        aluno.setRegistroAcademico(RegistroAcademico);
        aluno.setNome(Nome);
        aluno.setEmail(Email);
       
        try (ObjectOutputStream escreve = new ObjectOutputStream(new FileOutputStream(nomeArquivo, true))) {
            escreve.writeObject(aluno);
        }
       
        return true;
    }
    
    public boolean Remover(int indice) throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<Aluno> base = getAlunos();
        
        base.remove(indice);
        
        //Limpa o arquivo para reescrever os objetos 
        new FileOutputStream("Aluno.bin").close();
        
        //Reescreve os objetos 
        int tamanhoBase = base.size();
        for(int i=0 ; i < tamanhoBase ; i++){
            Persistir(base.get(i).getRegistroAcademico(), base.get(i).getNome(), base.get(i).getEmail());
        }      
       
        return true;
    }
    
    public boolean Modificar(int indice) throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<Aluno> base = getAlunos();
        
        base.set(indice, this);
        
        //Limpa o arquivo para reescrever os objetos 
        new FileOutputStream("Aluno.bin").close();
        
        //Reescreve os objetos 
        int tamanhoBase = base.size();
        for(int i=0 ; i < tamanhoBase ; i++){
            Persistir(base.get(i).getRegistroAcademico(), base.get(i).getNome(), base.get(i).getEmail());
        }      
       
        return true;
    }
    
    public static ArrayList<Aluno> getAlunos() throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Aluno> alunos = new ArrayList();
        
        try {
            fileIS = new FileInputStream("Aluno.bin");
        } catch (FileNotFoundException ex){
            // Cria um arquivo vazio, caso não existir
            File file = new File("Aluno.bin");
            file.createNewFile();
            fileIS = new FileInputStream("Aluno.bin");
        }
                
        while(fileIS.available()>0){
            ObjectInputStream ois = new ObjectInputStream(fileIS);
            Object object = ois.readObject();
            Aluno aluno = (Aluno) object;
            alunos.add(aluno);
        }
        
        return alunos;
    }
}



