package yousecase.gui.dp;

import java.util.EventObject;
import java.util.Objects;

import javax.swing.JButton;

// JButtonの有効無効を管理する
class ButtonManager implements EventObserver<EventObject> {
    private JButton inputButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    public ButtonManager(JButton inputButton, JButton startButton, JButton stopButton, JButton resetButton) {
        this.inputButton = Objects.requireNonNull(inputButton);
        this.startButton = Objects.requireNonNull(startButton);
        this.stopButton = Objects.requireNonNull(stopButton);
        this.resetButton = Objects.requireNonNull(resetButton);
    }

    @Override
    public void preprocess(EventObject event) {
        Object source = event.getSource();
        if (source == inputButton) {
            setEnabled(false, false, false, false);
        } else if (source == startButton) {
            setEnabled(false, false, true, false);
        } else if (source == stopButton) {
            // ボタンのデッドロック回避のためここで何もしない
        } else if (source == resetButton) {
            setEnabled(false, false, false, false);
        }
    }

    @Override
    public void postprocess(EventObject event) {
        Object source = event.getSource();
        if (source == inputButton) {
            setEnabled(true, true, false, false);
        } else if (source == startButton) {
            setEnabled(true, true, false, true);
        } else if (source == stopButton) {
            // 処理が終了するとstartButtonのpostprocessでボタンが有効化されるためここでは何もしない
        } else if (source == resetButton) {
            setEnabled(true, true, false, false);
        }
    }

    private synchronized void setEnabled(boolean inputButtonValue, boolean startButtonValue, //
            boolean stopButtonValue, boolean resetButtonValue) {
        inputButton.setEnabled(inputButtonValue);
        startButton.setEnabled(startButtonValue);
        stopButton.setEnabled(stopButtonValue);
        resetButton.setEnabled(resetButtonValue);
    }
}
