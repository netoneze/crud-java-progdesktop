package TableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author Nelson Toneze
 */
public class TableModelAluno extends AbstractTableModel{

    private List<String[]> linhas;
    private String[] colunas = new String[]{"registroAcademico","Nome","Email"};
    
    public TableModelAluno() {
        linhas = new ArrayList<String[]>();
    }
    
    public TableModelAluno(List<String[]> lista) {
        linhas = new ArrayList<String[]>(lista);
    }
    
    @Override
    public int getColumnCount() {        
        return colunas.length;
    }
    
    @Override
    public int getRowCount() {
        return linhas.size();
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String t[] = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return t[0];
            case 1:
                return t[1];
            case 2:
                return t[2];
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
   
    public String[] getValorAluno(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

}