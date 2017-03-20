package yousecase.gui.dp;

/**
 * データの処理を行う{@link DataProcessorBuilder}専用のオブジェクトです。
 * 
 * @param <D>
 *            データの型
 * @param <R>
 *            処理結果の型
 */
public interface ProcessingDevice<D, R> {
    /**
     * 「Start」ボタンが押されると処理が開始され、テーブルの行ごとにこのメソッドが実行されます。
     * 引数の{@link DataAndResult}には、「Data」列のデータと「Result」列の処理結果が保持されています。
     * {@link DataAndResult#setData}メソッドで指定した値が「Data」列に、
     * {@link DataAndResult#setResult}メソッドで指定した値が「Result」列に上書きされます。
     * 戻り値の{@link ProcessingCommand}で処理の継続の有無を指定します。
     * 
     * @param dataAndResult
     *            データと処理結果を保持するオブジェクト
     * @return 処理の命令
     */
    ProcessingCommand process(DataAndResult<D, R> dataAndResult);
}
