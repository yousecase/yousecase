package yousecase.gui.dp;

/**
 * データと処理結果を保持するオブジェクトです。
 * 
 * @param <D>
 *            データの型
 * @param <R>
 *            処理結果の型
 */
public interface DataAndResult<D, R> {

    /**
     * データを返します。
     * 
     * @return データ
     */
    D getData();

    /**
     * データを指定された値に設定します。
     * 
     * @param data
     *            データ
     */
    void setData(D data);

    /**
     * 処理結果を返します。
     * 
     * @return 処理結果
     */
    R getResult();

    /**
     * 処理結果を指定された値に設定します。
     * 
     * @param result
     *            処理結果
     */
    void setResult(R result);
}
