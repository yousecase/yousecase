package yousecase.gui.dp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

class InputButtonListener<D, R> implements ActionListener {
    private DefaultTableModel tableModel;
    private InputDevice<D> inputDevice;
    R initialResultValue;
    private AtomicInteger processingStartRow;

    public InputButtonListener(DefaultTableModel tableModel, InputDevice<D> inputDevice, //
            R initialResultValue, AtomicInteger processingStartRow) {
        this.tableModel = Objects.requireNonNull(tableModel);
        this.inputDevice = Objects.requireNonNull(inputDevice);
        this.initialResultValue = initialResultValue;// nullは許可
        this.processingStartRow = Objects.requireNonNull(processingStartRow);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            List<D> dataList = inputDevice.input();
            if (dataList == null || dataList.isEmpty()) {
                return;
            }

            tableModel.setRowCount(0);// テーブル初期化
            processingStartRow.set(0);// 処理開始行の初期化

            for (D data : dataList) {
                tableModel.addRow(new Object[] { data, initialResultValue });
            }
        } catch (Throwable e) {
            JOptionPane.showMessageDialog(null, "Failed to input.", null, JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }
}
