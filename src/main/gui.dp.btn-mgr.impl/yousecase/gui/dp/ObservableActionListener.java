package yousecase.gui.dp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

class ObservableActionListener extends ObservableListener<ActionEvent> implements ActionListener {
    private ActionListener actionListener;

    public ObservableActionListener(ActionListener actionListener) {
        this.actionListener = Objects.requireNonNull(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        execute(event, actionListener::actionPerformed);
    }
}
