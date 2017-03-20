package yousecase.gui.dp;

import java.awt.Dimension;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import yousecase.gui.dp.DataResultTablePanel.Builder;

class DataProcessorBuilderImpl<D, R> implements DataProcessorBuilder<D, R> {
    private InputDevice<D> inputDevice;
    private ProcessingDevice<D, R> processingDevice;
    private Builder panelBuilder;
    private R initialResultValue;
    private Dimension preferredSize;
    private String title;

    @Override
    public void build() {
        // コンポーネント生成
        DataResultTablePanel panel = panelBuilder.build();
        DefaultTableModel tableModel = panel.getTableModel();
        JButton inputButton = panel.getInputButton();
        JButton startButton = panel.getStartButton();
        JButton stopButton = panel.getStopButton();
        JButton resetButton = panel.getResetButton();

        // コンポーネント初期設定
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        resetButton.setEnabled(false);
        panel.getTable().setEnabled(false);

        // 制御オブジェクト生成
        ButtonManager buttonManager = new ButtonManager(inputButton, startButton, stopButton, resetButton);
        AtomicBoolean processingStopSwitch = new AtomicBoolean();
        AtomicInteger processingStartRow = new AtomicInteger();

        // リスナー登録
        ObservableActionListener inputButtonListener = new ObservableActionListener(
                new InputButtonListener<D, R>(tableModel, inputDevice, initialResultValue, processingStartRow));
        inputButtonListener.addObserver(buttonManager);
        inputButton.addActionListener(inputButtonListener);

        ObservableActionListener startButtonListener = new ObservableActionListener(
                new StartButtonListener<D, R>(tableModel, processingDevice, processingStartRow, processingStopSwitch));
        startButtonListener.addObserver(buttonManager);
        startButton.addActionListener(startButtonListener);

        ObservableActionListener stopButtonListener = new ObservableActionListener(
                new StopButtonListener(processingStopSwitch));
        stopButtonListener.addObserver(buttonManager);
        stopButton.addActionListener(stopButtonListener);

        ObservableActionListener resetButtonListener = new ObservableActionListener(
                new ResetButtonListener<R>(tableModel, initialResultValue, processingStartRow));
        resetButtonListener.addObserver(buttonManager);
        resetButton.addActionListener(resetButtonListener);

        // JFrame設定
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(preferredSize);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public DataProcessorBuilderImpl(InputDevice<D> inputDevice, ProcessingDevice<D, R> processingDevice) {
        this.inputDevice = Objects.requireNonNull(inputDevice);
        this.processingDevice = Objects.requireNonNull(processingDevice);
        this.panelBuilder = new Builder();
    }

    @Override
    public DataProcessorBuilder<D, R> inputButtonName(String inputButtonName) {
        Objects.requireNonNull(inputButtonName);
        panelBuilder.inputButtonName(inputButtonName);
        return this;
    }

    @Override
    public DataProcessorBuilder<D, R> startButtonName(String startButtonName) {
        Objects.requireNonNull(startButtonName);
        panelBuilder.startButtonName(startButtonName);
        return this;
    }

    @Override
    public DataProcessorBuilder<D, R> stopButtonName(String stopButtonName) {
        Objects.requireNonNull(stopButtonName);
        panelBuilder.stopButtonName(stopButtonName);
        return this;
    }

    @Override
    public DataProcessorBuilder<D, R> resetButtonName(String resetButtonName) {
        Objects.requireNonNull(resetButtonName);
        panelBuilder.resetButtonName(resetButtonName);
        return this;
    }

    @Override
    public DataProcessorBuilder<D, R> dataColumnName(String dataColumnName) {
        Objects.requireNonNull(dataColumnName);
        panelBuilder.dataColumnName(dataColumnName);
        return this;
    }

    @Override
    public DataProcessorBuilder<D, R> resultColumnName(String resultColumnName) {
        Objects.requireNonNull(resultColumnName);
        panelBuilder.resultColumnName(resultColumnName);
        return this;
    }

    @Override
    public DataProcessorBuilder<D, R> initialResultValue(R initialResultValue) {
        // nullは許可
        this.initialResultValue = initialResultValue;
        return this;
    }

    @Override
    public DataProcessorBuilder<D, R> preferredSize(int width, int height) {
        this.preferredSize = new Dimension(width, height);
        return this;
    }

    @Override
    public DataProcessorBuilder<D, R> title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
}
