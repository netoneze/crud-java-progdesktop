/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Aluno;
import Utils.JDBCUtil;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nelson Toneze
 */
public class AlunoDAO {
    private static final String caminho = System.getProperty("user.dir");
    private static final File configArquivo = new File(caminho + "/src/Utils/configuracaobd.properties");
    private static Connection conexao = null;
    static ArrayList<Aluno> list = new ArrayList<>();
    
    
    public static boolean SalvarAluno(String registroAcademico, String Nome, String Email){
        Aluno a = new Aluno();
        a.setEmail(Email);
        a.setRegistroAcademico(registroAcademico);
        a.setNome(Nome);
        return criaAluno(a);
    }
    
    public static boolean ExcluirAluno(int indice){
        return deletarAluno(indice);
    }
    
    public static boolean ModificarAluno(int indice, String registroAcademico, String Nome, String Email) {
        Aluno a = new Aluno();
        a.setNome(Nome);
        a.setEmail(Email);
        a.setRegistroAcademico(registroAcademico);
        return alterarAluno(a, indice);
    }
    
    public static boolean criaAluno(Aluno aluno) {
        if(aluno.getNome() != null && aluno.getEmail() != null){
            try {
                JDBCUtil.init(configArquivo);
                conexao = JDBCUtil.getConnection();
                String sql = "insert into aluno (nome, email, registro_academico) values(?,?,?)";
                PreparedStatement pstm = conexao.prepareStatement(sql);
                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getEmail());
                pstm.setString(3, aluno.getRegistroAcademico());

                pstm.execute();
                pstm.close();

            } catch (ClassNotFoundException erro) {
                System.out.println("Falha ao carregar o driver JDBC." + erro);
            } catch (IOException erro) {
                System.out.println("Falha ao carregar o arquivo de configuração." + erro);
            } catch (SQLException erro) {
                System.out.println("Falha na conexao, comando sql = " + erro);
            }
        } else {
            return false;
        }
        return true;
    }
    
    public static ArrayList<Aluno> listaAluno() {
        String sql = "select * from aluno";
        try {
            JDBCUtil.init(configArquivo);
            conexao = JDBCUtil.getConnection();         
            conexao.setAutoCommit(false);

            PreparedStatement pstm = conexao.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(resultSet.getInt("id_aluno"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setEmail(resultSet.getString("email"));
                aluno.setRegistroAcademico(resultSet.getString("registro_academico"));

                list.add(aluno);
            }

            return list;

        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
            return null;
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
            return null;
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
            return null;
        }
    }
    
    public static boolean alterarAluno(Aluno aluno, int indice) {
        if(aluno.getNome() != null && aluno.getEmail() != null){
            try {
                JDBCUtil.init(configArquivo);
                conexao = JDBCUtil.getConnection();
                String sql = "update aluno set nome = ?, email = ?, registro_academico = ? where id_aluno = ?";
                PreparedStatement pstm = conexao.prepareStatement(sql);
                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getEmail());
                pstm.setString(3, aluno.getRegistroAcademico());
                pstm.setInt(4, indice);
                
                pstm.execute();
                pstm.close();
                
            } catch (ClassNotFoundException erro) {
                System.out.println("Falha ao carregar o driver JDBC." + erro);
            } catch (IOException erro) {
                System.out.println("Falha ao carregar o arquivo de configuração." + erro);
            } catch (SQLException erro) {
                System.out.println("Falha na conexao, comando sql = " + erro);
            }
        } else { 
            return false;
        }
        return true;
    }
    
    public static boolean deletarAluno(int indice) {
        try {
            JDBCUtil.init(configArquivo);
            conexao = JDBCUtil.getConnection();
            String sql = "delete from aluno where id_aluno = ?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, indice);

            pstm.execute();
            pstm.close();

        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha de conexao, no comando = " + erro);
        }
        return true;
    }
    
}
