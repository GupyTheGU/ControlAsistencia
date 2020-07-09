
package controlasistencia;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**@author BB_TLACUACHE*/
public class modeloTabla extends AbstractTableModel {
    private final ArrayList<Asistencia> data;
    String[] columnas = new String[]{"Fecha", "Hora de registro", "Entrada/Salida", "Incidencia","Biométrico"};
        public modeloTabla() {
            data = new ArrayList<>();
        }
        
        @Override
        public String getColumnName(int column) {
            String value = columnas[column];
            return value;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class value = String.class;
            return value;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Asistencia obj = data.get(rowIndex);
            Object value = null;
            switch (columnIndex) {
                case 0:
                    value = obj.getStr_fecha();
                    break;
                case 1:
                    value = obj.getStr_hora();
                    break;
                case 2:
                    value = obj.getStr_tipo();
                    break;
                case 3:
                    value = obj.getStr_incidencia();
                    break;
                case 4:
                    value = obj.getStr_biometrico();
                    break;
            }
            return value;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (columnIndex == 4) {

//                System.out.println(aValue);

                Asistencia value = data.get(rowIndex);
                if ("editar".equals(aValue)) {
//                    System.out.println("para editar");
                } else {
//                    System.out.println("para eliminar");
                }
                fireTableCellUpdated(rowIndex, columnIndex);
                //remove(value);
            }
        }

        public void add(Asistencia value) {
            int startIndex = getRowCount();
            data.add(value);
            fireTableRowsInserted(startIndex, getRowCount() - 1);
        }

        public void remove(Asistencia value) {
            int startIndex = data.indexOf(value);
            System.out.println("startIndex = " + startIndex);
            data.remove(value);
            fireTableRowsInserted(startIndex, startIndex);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
}
