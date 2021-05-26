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
public class Aluno {
    private String registroAcademico;
    private String Nome;
    private String Email;
    private int id;
    
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



