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
public class Professor {
    private String disciplina;
    private String Nome;
    private String Email;

    public Professor(){
    }
    
    public Professor(String disciplina, String nome, String email){
        this.disciplina = disciplina;
        this.Nome = nome;
        this.Email = email;
    }
    
    public Professor(JSONObject json) {
        this.disciplina = json.getString("disciplina");
        this.Nome = json.getString("nome");
        this.Email = json.getString("email");
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
    
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("disciplina",this.disciplina);
        json.put("nome",this.Nome);
        json.put("email",this.Email);
        return json;
    }
    
    public boolean Persistir(){
        JSONObject json = this.toJson();
        
        String base = ArquivoUtil.Ler("Professor.txt");
        JSONArray jA = new JSONArray();
        if(!base.isEmpty() && base.length()>5)
            jA = new JSONArray(base);
        
        jA.put(json);
        ArquivoUtil.Escrever(jA.toString(),"Professor.txt");
       
        return true;
    }
    
    public boolean Remover(int indice){
        String base = ArquivoUtil.Ler("Professor.txt");
        JSONArray jA = new JSONArray();
        if(!base.isEmpty() && base.length()>5)
            jA = new JSONArray(base);
        
        jA.remove(indice);
        ArquivoUtil.Escrever(jA.toString(),"Professor.txt");
       
        return true;
    }
    
    public boolean Modificar(int indice){
        JSONObject json = this.toJson();
        
        String base = ArquivoUtil.Ler("Professor.txt");
        JSONArray jA = new JSONArray();
        if(!base.isEmpty() && base.length()>5)
            jA = new JSONArray(base);
        
        jA.put(indice,json);
        ArquivoUtil.Escrever(jA.toString(),"Professor.txt");
       
        return true;
    }
    
    public static ArrayList<Professor> getProfessores(){
        ArrayList<Professor> professores = new ArrayList();
        String base = ArquivoUtil.Ler("Professor.txt");
        if(base.isEmpty() || base.length()<5)
            return null;
        
        JSONArray jA = new JSONArray(base);
        for(int i=0;i<jA.length();i++){
            Professor A = new Professor(jA.getJSONObject(i));
            professores.add(A);
        }
        return professores;
    }
    
}
