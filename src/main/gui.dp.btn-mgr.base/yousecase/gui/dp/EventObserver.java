package yousecase.gui.dp;

import java.util.EventObject;

// Eventが発生しListenerが処理を行う前後に処理を行う
interface EventObserver<T extends EventObject> {
    void preprocess(T event);

    void postprocess(T event);
}
