/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Util.ArquivoUtil;
import java.util.ArrayList;
import json.JSONArray;
import json.JSONObject;

/**
 *
 * @author Nelson Toneze
 */
public class Aluno {
    private String registroAcademico;
    private String Nome;
    private String Email;

    public Aluno() {
    }

    public Aluno(String RegistroAcademico, String Nome, String Email) {
        this.registroAcademico = RegistroAcademico;
        this.Nome = Nome;
        this.Email = Email;
    }
    
    public Aluno(JSONObject json) {
        this.registroAcademico = json.getString("registroAcademico");
        this.Nome = json.getString("nome");
        this.Email = json.getString("email");
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
    
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("registroAcademico",this.registroAcademico);
        json.put("nome",this.Nome);
        json.put("email",this.Email);
        return json;
    }
    
    public boolean Persistir(){
        JSONObject json = this.toJson();
        
        String base = ArquivoUtil.Ler("Aluno.txt");
        JSONArray jA = new JSONArray();
        if(!base.isEmpty() && base.length()>5)
            jA = new JSONArray(base);
        
        jA.put(json);
        ArquivoUtil.Escrever(jA.toString(),"Aluno.txt");
       
        return true;
    }
    
    public boolean Remover(int indice){
        String base = ArquivoUtil.Ler("Aluno.txt");
        JSONArray jA = new JSONArray();
        if(!base.isEmpty() && base.length()>5)
            jA = new JSONArray(base);
        
        jA.remove(indice);
        ArquivoUtil.Escrever(jA.toString(),"Aluno.txt");
       
        return true;
    }
    
    public boolean Modificar(int indice){
        JSONObject json = this.toJson();
        
        String base = ArquivoUtil.Ler("Aluno.txt");
        JSONArray jA = new JSONArray();
        if(!base.isEmpty() && base.length()>5)
            jA = new JSONArray(base);
        
        jA.put(indice,json);
        ArquivoUtil.Escrever(jA.toString(),"Aluno.txt");
       
        return true;
    }
    
    public static ArrayList<Aluno> getAlunos(){
        ArrayList<Aluno> alunos = new ArrayList();
        String base = ArquivoUtil.Ler("Aluno.txt");
        if(base.isEmpty() || base.length()<5)
            return null;
        
        JSONArray jA = new JSONArray(base);
        for(int i=0;i<jA.length();i++){
            Aluno A = new Aluno(jA.getJSONObject(i));
            alunos.add(A);
        }
        return alunos;
    }
}



