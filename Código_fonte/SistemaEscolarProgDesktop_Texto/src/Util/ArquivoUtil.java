package Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
  * @author Nelson Toneze
 */
public class ArquivoUtil {
   
    public static String Ler(String Caminho){
        String conteudo = "";
        try {
            FileReader arquivo = new FileReader(Caminho);
            BufferedReader lerArquivo = new BufferedReader(arquivo);
            String linha="";
            try {
                linha = lerArquivo.readLine();
                while(linha!=null){
                    conteudo += linha+"\n";
                    linha = lerArquivo.readLine();
                }
                arquivo.close();
                return conteudo;
            } catch (IOException ex) {
                System.out.println("Não foi possível ler o arquivo");
                return "";
            }
        } catch (FileNotFoundException ex) {
            return "";
        }
    }
    
    public static boolean Escrever(String Texto, String Caminho){
        try {
            FileWriter arquivo = new FileWriter(Caminho);
            PrintWriter gravarArquivo = new PrintWriter(arquivo);
            gravarArquivo.println(Texto);
            gravarArquivo.close();
            return true;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
