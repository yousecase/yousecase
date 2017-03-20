package yousecase.gui.dp;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

abstract class ObservableListener<T1 extends EventObject> {
    private ExecutorService executor = Executors.newFixedThreadPool(1);
    private List<EventObserver<? super T1>> observers = new ArrayList<>();

    protected <T2 extends T1> void execute(T2 event, Consumer<T2> consumer) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(consumer);

        for (EventObserver<? super T1> observer : observers) {
            observer.preprocess(event);
        }
        executor.execute(() -> {
            try {
                consumer.accept(event);
            } finally {
                for (EventObserver<? super T1> observer : observers) {
                    observer.postprocess(event);
                }
            }
        });
    }

    public void addObserver(EventObserver<? super T1> observer) {
        Objects.requireNonNull(observer);
        observers.add(observer);
    }

    public void removeObserver(EventObserver<? super T1> observer) {
        Objects.requireNonNull(observer);
        observers.remove(observer);
    }
}
