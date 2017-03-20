package yousecase.gui.dp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

class StopButtonListener implements ActionListener {
    private AtomicBoolean processingStopSwitch;

    public StopButtonListener(AtomicBoolean processingStopSwitch) {
        this.processingStopSwitch = Objects.requireNonNull(processingStopSwitch);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        processingStopSwitch.set(true);
    }
}
