package yousecase.gui.dp;

import java.util.List;

/**
 * データの入力を行う{@link DataProcessorBuilder}専用のオブジェクトです。
 * 
 * @param <D>
 *            データの型
 */
public interface InputDevice<D> {
    /**
     * 「Input」ボタンが押されるとこのメソッドが実行され、戻り値の{@link List}内のデータが「Data」列に表示されます。
     * 「Result」列には{@link DataProcessorBuilder#initialResultValue}で設定されている値が表示されます。
     * 戻り値がnullもしくは空の場合テーブルの更新は行われません。
     * 
     * @return データを保持する{@link List}
     */
    List<D> input();
}
