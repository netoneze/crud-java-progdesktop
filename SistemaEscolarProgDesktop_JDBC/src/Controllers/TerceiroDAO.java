/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.TerceiroDAO.list;
import Models.Terceiro;
import Utils.JDBCUtil;
import java.sql.Connection;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nelson Toneze
 */
public class TerceiroDAO {
    private static final String caminho = System.getProperty("user.dir");
    private static final File configArquivo = new File(caminho + "/src/Utils/configuracaobd.properties");
    private static Connection conexao = null;
    static ArrayList<Terceiro> list = new ArrayList<>();
    
    public static boolean SalvarTerceiro(String Funcao, String Nome, String Email) {
        Terceiro t = new Terceiro();
        
        t.setFuncao(Funcao);
        t.setNome(Nome);
        t.setEmail(Email);
        
        return criaTerceiro(t);
    }
    
    public static boolean ExcluirTerceiro(int indice){
        return deletarTerceiro(indice);
    }
    
    public static boolean ModificarTerceiro(int indice, String Funcao, String Nome, String Email) {
        Terceiro t = new Terceiro(Funcao, Nome, Email);
        
        t.setFuncao(Funcao);
        t.setNome(Nome);
        t.setEmail(Email);
        
        return alterarTerceiro(t,indice);
    }
    
    public static boolean criaTerceiro(Terceiro terceiro) {
        if(terceiro.getNome() != null && terceiro.getEmail() != null){
            try {
                JDBCUtil.init(configArquivo);
                conexao = JDBCUtil.getConnection();
                String sql = "insert into terceiro (nome, email, funcao) values(?,?,?)";
                PreparedStatement pstm = conexao.prepareStatement(sql);
                pstm.setString(1, terceiro.getNome());
                pstm.setString(2, terceiro.getEmail());
                pstm.setString(3, terceiro.getFuncao());

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
    
    public static ArrayList<Terceiro> listaTerceiro() {
        String sql = "select * from terceiro";
        try {
            JDBCUtil.init(configArquivo);
            conexao = JDBCUtil.getConnection();         
            conexao.setAutoCommit(false);

            PreparedStatement pstm = conexao.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                Terceiro terceiro = new Terceiro();
                terceiro.setId(resultSet.getInt("id_terceiro"));
                terceiro.setNome(resultSet.getString("nome"));
                terceiro.setEmail(resultSet.getString("email"));
                terceiro.setFuncao(resultSet.getString("funcao"));

                list.add(terceiro);
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
    
    public static boolean alterarTerceiro(Terceiro terceiro, int indice) {
        if(terceiro.getNome() != null && terceiro.getEmail() != null){
            try {
                JDBCUtil.init(configArquivo);
                conexao = JDBCUtil.getConnection();
                String sql = "update terceiro set nome = ?, email = ?, funcao = ? where id_terceiro = ?";
                PreparedStatement pstm = conexao.prepareStatement(sql);
                pstm.setString(1, terceiro.getNome());
                pstm.setString(2, terceiro.getEmail());
                pstm.setString(3, terceiro.getFuncao());
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
    
    public static boolean deletarTerceiro(int indice) {
        try {
            JDBCUtil.init(configArquivo);
            conexao = JDBCUtil.getConnection();
            String sql = "delete from terceiro where id_terceiro = ?";
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
