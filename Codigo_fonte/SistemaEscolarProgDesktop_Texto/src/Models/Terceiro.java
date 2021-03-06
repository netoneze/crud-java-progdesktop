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
public class Terceiro {
    private String Funcao;
    private String Nome;
    private String Email;

    public Terceiro(){
    }
    
    public Terceiro(String funcao, String nome, String email){
        this.Funcao = funcao;
        this.Nome = nome;
        this.Email = email;
    }
    
    public Terceiro(JSONObject json) {
        this.Funcao = json.getString("funcao");
        this.Nome = json.getString("nome");
        this.Email = json.getString("email");
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
    
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("funcao",this.Funcao);
        json.put("nome",this.Nome);
        json.put("email",this.Email);
        return json;
    }
    
    public boolean Persistir(){
        JSONObject json = this.toJson();
        
        String base = ArquivoUtil.Ler("Terceiro.txt");
        JSONArray jA = new JSONArray();
        if(!base.isEmpty() && base.length()>5)
            jA = new JSONArray(base);
        
        jA.put(json);
        ArquivoUtil.Escrever(jA.toString(),"Terceiro.txt");
       
        return true;
    }
    
    public boolean Remover(int indice){
        String base = ArquivoUtil.Ler("Terceiro.txt");
        JSONArray jA = new JSONArray();
        if(!base.isEmpty() && base.length()>5)
            jA = new JSONArray(base);
        
        jA.remove(indice);
        ArquivoUtil.Escrever(jA.toString(),"Terceiro.txt");
       
        return true;
    }
    
    public boolean Modificar(int indice){
        JSONObject json = this.toJson();
        
        String base = ArquivoUtil.Ler("Terceiro.txt");
        JSONArray jA = new JSONArray();
        if(!base.isEmpty() && base.length()>5)
            jA = new JSONArray(base);
        
        jA.put(indice,json);
        ArquivoUtil.Escrever(jA.toString(),"Terceiro.txt");
       
        return true;
    }
    
    public static ArrayList<Terceiro> getTerceiros(){
        ArrayList<Terceiro> terceiros = new ArrayList();
        String base = ArquivoUtil.Ler("Terceiro.txt");
        if(base.isEmpty() || base.length()<5)
            return null;
        
        JSONArray jA = new JSONArray(base);
        for(int i=0;i<jA.length();i++){
            Terceiro A = new Terceiro(jA.getJSONObject(i));
            terceiros.add(A);
        }
        return terceiros;
    }
    
}
