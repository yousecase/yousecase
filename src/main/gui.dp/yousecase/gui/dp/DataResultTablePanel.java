package yousecase.gui.dp;

import java.awt.BorderLayout;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class DataResultTablePanel extends JPanel {
    private static final long serialVersionUID = -1021659979912858104L;
    public static final int DATA_COLUMN_INDEX = 0;
    public static final int RESULT_COLUMN_INDEX = 1;

    // north
    private JPanel northPanel;
    private JButton inputButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    // center
    private JScrollPane tableScrollPane;
    private JTable table;
    private DefaultTableModel tableModel;

    private DataResultTablePanel(Builder builder) {

        // north
        northPanel = new JPanel();
        inputButton = new JButton(builder.inputButtonName);
        northPanel.add(inputButton);
        startButton = new JButton(builder.startButtonName);
        northPanel.add(startButton);
        stopButton = new JButton(builder.stopButtonName);
        northPanel.add(stopButton);
        resetButton = new JButton(builder.resetButtonName);
        northPanel.add(resetButton);

        // center
        tableModel = new DefaultTableModel(new Object[] { builder.dataColumnName, builder.resultColumnName }, 0);
        table = new JTable(tableModel);
        tableScrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    // getter
    public JPanel getNorthPanel() {
        return northPanel;
    }

    public JButton getInputButton() {
        return inputButton;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public static class Builder {
        private String inputButtonName;
        private String startButtonName;
        private String stopButtonName;
        private String resetButtonName;
        private String dataColumnName;
        private String resultColumnName;

        public Builder() {
            this.inputButtonName = "Input";
            this.startButtonName = "Start";
            this.stopButtonName = "Stop";
            this.resetButtonName = "Reset";
            this.dataColumnName = "Data";
            this.resultColumnName = "Result";
        }

        public Builder inputButtonName(String inputButtonName) {
            this.inputButtonName = Objects.requireNonNull(inputButtonName);
            return this;
        }

        public Builder startButtonName(String startButtonName) {
            this.startButtonName = Objects.requireNonNull(startButtonName);
            return this;
        }

        public Builder stopButtonName(String stopButtonName) {
            this.stopButtonName = Objects.requireNonNull(stopButtonName);
            return this;
        }

        public Builder resetButtonName(String resetButtonName) {
            this.resetButtonName = Objects.requireNonNull(resetButtonName);
            return this;
        }

        public Builder dataColumnName(String dataColumnName) {
            this.dataColumnName = Objects.requireNonNull(dataColumnName);
            return this;
        }

        public Builder resultColumnName(String resultColumnName) {
            this.resultColumnName = Objects.requireNonNull(resultColumnName);
            return this;
        }

        public DataResultTablePanel build() {
            return new DataResultTablePanel(this);
        }
    }

    // check layout
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new Builder().build();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
