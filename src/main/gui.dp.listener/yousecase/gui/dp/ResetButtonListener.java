package yousecase.gui.dp;

import static yousecase.gui.dp.DataResultTablePanel.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.table.TableModel;

class ResetButtonListener<R> implements ActionListener {
    private TableModel tableModel;
    private R resetValue;
    private AtomicInteger processingStartRow;

    public ResetButtonListener(TableModel tableModel, R resetValue, AtomicInteger processingStartRow) {
        this.tableModel = Objects.requireNonNull(tableModel);
        this.resetValue = resetValue;
        this.processingStartRow = Objects.requireNonNull(processingStartRow);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // 「Result」列の初期化
        int tableRowCount = tableModel.getRowCount();
        for (int row = 0; row < tableRowCount; row++) {
            tableModel.setValueAt(resetValue, row, RESULT_COLUMN_INDEX);
        }

        // 処理開始行の初期化
        processingStartRow.set(0);
    }
}
