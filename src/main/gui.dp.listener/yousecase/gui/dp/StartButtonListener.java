package yousecase.gui.dp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

class StartButtonListener<D, R> implements ActionListener {
    private TableModel tableModel;
    private ProcessingDevice<D, R> processingDevice;
    private AtomicInteger processingStartRow;
    private AtomicBoolean processingStopSwitch;

    public StartButtonListener(TableModel tableModel, ProcessingDevice<D, R> processingDevice,
            AtomicInteger processingStartRow, AtomicBoolean processingStopSwitch) {
        this.tableModel = Objects.requireNonNull(tableModel);
        this.processingDevice = Objects.requireNonNull(processingDevice);
        this.processingStartRow = Objects.requireNonNull(processingStartRow);
        this.processingStopSwitch = Objects.requireNonNull(processingStopSwitch);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        processingStopSwitch.set(false);

        int currentRow = processingStartRow.get();
        int tableRowCount = tableModel.getRowCount();
        try {
            while (currentRow < tableRowCount) {
                DataAndResult<D, R> dataAndResult = new DataAndResultImplWithTableModel<>(tableModel, currentRow);
                ProcessingCommand processingCommand = processingDevice.process(dataAndResult);

                if (processingCommand == null) {
                    showErrorMessage();
                    break;
                } else if (processingCommand == ProcessingCommand.STOP_AT_CURRENT_ROW) {
                    break;
                }

                currentRow++;

                if (processingCommand == ProcessingCommand.STOP || processingStopSwitch.get()) {
                    break;
                }
            }
        } catch (Throwable e) {
            showErrorMessage();
            throw e;
        } finally {
            processingStartRow.set(currentRow);
        }
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(null, "Failed to process.", null, JOptionPane.ERROR_MESSAGE);
    }
}
