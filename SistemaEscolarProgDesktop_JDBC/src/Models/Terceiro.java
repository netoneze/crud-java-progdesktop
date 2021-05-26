/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Nelson Toneze
 */
public class Terceiro {
    private String Funcao;
    private String Nome;
    private String Email;
    private int id;
    
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
   
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
