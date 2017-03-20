package yousecase.gui.dp;

import static yousecase.gui.dp.DataResultTablePanel.*;

import javax.swing.table.TableModel;

class DataAndResultImplWithTableModel<D, R> implements DataAndResult<D, R> {
    private TableModel tableModel;
    private int rowIndex;

    public DataAndResultImplWithTableModel(TableModel tableModel, int rowIndex) {
        this.tableModel = tableModel;
        this.rowIndex = rowIndex;
    }

    @SuppressWarnings("unchecked")
    @Override
    public D getData() {
        return (D) tableModel.getValueAt(rowIndex, DATA_COLUMN_INDEX);
    }

    @Override
    public void setData(D data) {
        tableModel.setValueAt(data, rowIndex, DATA_COLUMN_INDEX);
    }

    @SuppressWarnings("unchecked")
    @Override
    public R getResult() {
        return (R) tableModel.getValueAt(rowIndex, RESULT_COLUMN_INDEX);
    }

    @Override
    public void setResult(R result) {
        tableModel.setValueAt(result, rowIndex, RESULT_COLUMN_INDEX);
    }

    @Override
    public String toString() {
        return "DataAndResultImplWithTableModel [data=" + getData() + ", result=" + getResult() + "]";
    }
}
