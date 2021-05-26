/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ProfessorDAO.list;
import Models.Professor;
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
public class ProfessorDAO {
    private static final String caminho = System.getProperty("user.dir");
    private static final File configArquivo = new File(caminho + "/src/Utils/configuracaobd.properties");
    private static Connection conexao = null;
    static ArrayList<Professor> list = new ArrayList<>();
    
    public static boolean SalvarProfessor(String Disciplina, String Nome, String Email) {
        Professor p = new Professor();
        
        p.setNome(Nome);
        p.setEmail(Email);
        p.setDisciplina(Disciplina);
        
        return criaProfessor(p);
    }
    
    public static boolean ExcluirProfessor(int indice){
        return deletarProfessor(indice);
    }
    
    public static boolean ModificarProfessor(int indice, String Disciplina, String Nome, String Email){
        Professor p = new Professor();
        
        p.setNome(Nome);
        p.setEmail(Email);
        p.setDisciplina(Disciplina);
        
        return alterarProfessor(p, indice);
    }
    
    public static boolean criaProfessor(Professor professor) {
        if(professor.getNome() != null && professor.getEmail() != null){
            try {
                JDBCUtil.init(configArquivo);
                conexao = JDBCUtil.getConnection();
                String sql = "insert into professor (nome, email, disciplina) values(?,?,?)";
                PreparedStatement pstm = conexao.prepareStatement(sql);
                pstm.setString(1, professor.getNome());
                pstm.setString(2, professor.getEmail());
                pstm.setString(3, professor.getDisciplina());

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
    
    public static ArrayList<Professor> listaProfessor() {
        String sql = "select * from professor";
        try {
            JDBCUtil.init(configArquivo);
            conexao = JDBCUtil.getConnection();         
            conexao.setAutoCommit(false);

            PreparedStatement pstm = conexao.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                Professor professor = new Professor();
                professor.setId(resultSet.getInt("id_professor"));
                professor.setNome(resultSet.getString("nome"));
                professor.setEmail(resultSet.getString("email"));
                professor.setDisciplina(resultSet.getString("disciplina"));

                list.add(professor);
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
    
    public static boolean alterarProfessor(Professor professor, int indice) {
        if(professor.getNome() != null && professor.getEmail() != null){
            try {
                JDBCUtil.init(configArquivo);
                conexao = JDBCUtil.getConnection();
                String sql = "update professor set nome = ?, email = ?, disciplina = ? where id_professor = ?";
                PreparedStatement pstm = conexao.prepareStatement(sql);
                pstm.setString(1, professor.getNome());
                pstm.setString(2, professor.getEmail());
                pstm.setString(3, professor.getDisciplina());
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
    
    public static boolean deletarProfessor(int indice) {
        try {
            JDBCUtil.init(configArquivo);
            conexao = JDBCUtil.getConnection();
            String sql = "delete from professor where id_professor = ?";
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
