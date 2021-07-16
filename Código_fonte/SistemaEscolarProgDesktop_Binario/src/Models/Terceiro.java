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
public class Terceiro implements Serializable {
    private static final long serialVersionUID = 0x5a9e1a0049694c7aL;
    private String Funcao;
    private String Nome;
    private String Email;
    private static FileInputStream fileIS;
    private static String nomeArquivo = "Terceiro.bin";
    
    public Terceiro(){
    }
    
    public Terceiro(String funcao, String nome, String email){
        this.Funcao = funcao;
        this.Nome = nome;
        this.Email = email;
    }
    
    /**
     * @return the funcao
     */
    public String getFuncao() {
        return Funcao;
    }

    /**
     * @param funcao the funcao to set
     */
    public void setFuncao(String funcao) {
        this.Funcao = funcao;
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
    
    public boolean Persistir(String Funcao, String Nome, String Email) throws FileNotFoundException, IOException{
        Terceiro terceiro = new Terceiro();
        terceiro.setFuncao(Funcao);
        terceiro.setNome(Nome);
        terceiro.setEmail(Email);
       
        try (ObjectOutputStream escreve = new ObjectOutputStream(new FileOutputStream(nomeArquivo, true))) {
            escreve.writeObject(terceiro);
        }
       
        return true;
    }
    
    public boolean Remover(int indice) throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<Terceiro> base = getTerceiros();
        
        base.remove(indice);
        
        //Limpa o arquivo para reescrever os objetos 
        new FileOutputStream("Terceiro.bin").close();
        
        //Reescreve os objetos 
        int tamanhoBase = base.size();
        for(int i=0 ; i < tamanhoBase ; i++){
            Persistir(base.get(i).getFuncao(), base.get(i).getNome(), base.get(i).getEmail());
        }      
       
        return true;
    }
    
    public boolean Modificar(int indice) throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<Terceiro> base = getTerceiros();
        
        base.set(indice, this);
        
        //Limpa o arquivo para reescrever os objetos 
        new FileOutputStream("Terceiro.bin").close();
        
        //Reescreve os objetos 
        int tamanhoBase = base.size();
        for(int i=0 ; i < tamanhoBase ; i++){
            Persistir(base.get(i).getFuncao(), base.get(i).getNome(), base.get(i).getEmail());
        }      
       
        return true;
    }
    
    public static ArrayList<Terceiro> getTerceiros() throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Terceiro> terceiros = new ArrayList();
        
        try {
            fileIS = new FileInputStream("Terceiro.bin");
        } catch (FileNotFoundException ex){
            // Cria um arquivo vazio, caso não existir
            File file = new File("Terceiro.bin");
            file.createNewFile();
            fileIS = new FileInputStream("Terceiro.bin");
        }
                
        while(fileIS.available()>0){
            ObjectInputStream ois = new ObjectInputStream(fileIS);
            Object object = ois.readObject();
            Terceiro terceiro = (Terceiro) object;
            terceiros.add(terceiro);
        }
        
        return terceiros;
    }
    
}
