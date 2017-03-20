package yousecase.gui.dp;

/**
 * <h3>概要</h3>データの処理と結果の表示を行うGUIアプリケーションを生成するビルダーオブジェクトです。
 * このオブジェクトは処理するデータと処理結果を表示するテーブルと処理等の操作を行うためのボタンから構成されるGUIを提供します。
 * データの入力と処理を行うクラスは独自に実装したクラスが利用できます。
 * 
 * <h3>仕様</h3>画面上部に「Input」、「Start」、「Stop」、「Reset」の4つのボタンあり、
 * 中央に「Data」、「Result」の2列から構成されるテーブルがあります。
 * <ul>
 * <li>「Input」ボタン<br>
 * 処理するデータを入力し、「Data」列に表示します。「Input」ボタンが押される前にテーブルに表示されていたデータと処理結果は削除されます。</li>
 * <li>「Start」ボタン<br>
 * テーブルの最初の行から最後の行まで順番に処理を行います。「Data」列のデータを処理し、処理結果を同じ行の「Result」列に表示します。
 * 事前に処理が停止されていた場合、停止されていた行から処理を再開します。</li>
 * <li>「Stop」ボタン<br>
 * 「Stop」ボタンが押された時に処理中の行の処理が終了した後に処理を停止します。</li>
 * <li>「Reset」ボタン<br>
 * {@link #initialResultValue}メソッドで設定されている値で「Result」列を初期化します。初期化後の処理は最初の行から行われます。</li>
 * <li>「Data」列<br>
 * 処理するデータを表示します。このデータはオブジェクトであるためtoStringメソッドの戻り値が表示されます。データがnullの場合は何も表示されません。</li>
 * <li>「Result」列<br>
 * 処理結果を表示します。この処理結果はオブジェクトであるためtoStringメソッドの戻り値が表示されます。処理結果がnullの場合は何も表示されません。</li>
 * </ul>
 * 
 * <h3>実装例</h3>実装例はyousecase.gui.dp.exampleパッケージにあります。
 * 
 * @param <D>
 *            データの型
 * @param <R>
 *            処理結果の型
 */
public interface DataProcessorBuilder<D, R> {

    /**
     * データの処理と結果の表示を行うGUIアプリケーションを生成するビルダーオブジェクトを生成します。
     * 
     * @param inputDevice
     *            データの入力を行うオブジェクト
     * @param processingDevice
     *            データの処理を行うオブジェクト
     * @return {@link DataProcessorBuilder}オブジェクト
     * 
     * @param <D>
     *            データの型
     * @param <R>
     *            処理結果の型
     */
    public static <D, R> DataProcessorBuilder<D, R> newInstance(InputDevice<D> inputDevice,
            ProcessingDevice<D, R> processingDevice) {
        return new DataProcessorBuilderImpl<>(inputDevice, processingDevice);
    }

    /**
     * 「Input」ボタンの名前を指定された文字列に設定します。デフォルトの名前は「Input」です。
     * 
     * @param inputButtonName
     *            「Input」ボタンの名前にする文字列
     * @return このオブジェクトへの参照
     */
    DataProcessorBuilder<D, R> inputButtonName(String inputButtonName);

    /**
     * 「Start」ボタンの名前を指定された文字列に設定します。デフォルトの名前は「Start」です。
     * 
     * @param startButtonName
     *            「Start」ボタンの名前にする文字列
     * @return このオブジェクトへの参照
     */
    DataProcessorBuilder<D, R> startButtonName(String startButtonName);

    /**
     * 「Stop」ボタンの名前を指定された文字列に設定します。デフォルトの名前は「Stop」です。
     * 
     * @param stopButtonName
     *            「Stop」ボタンの名前にする文字列
     * @return このオブジェクトへの参照
     */
    DataProcessorBuilder<D, R> stopButtonName(String stopButtonName);

    /**
     * 「Reset」ボタンの名前を指定された文字列に設定します。デフォルトの名前は「Reset」です。
     * 
     * @param resetButtonName
     *            「Reset」ボタンの名前にする文字列
     * @return このオブジェクトへの参照
     */
    DataProcessorBuilder<D, R> resetButtonName(String resetButtonName);

    /**
     * 「Data」列の名前を指定された文字列に設定します。デフォルトの名前は「Data」です。
     * 
     * @param dataColumnName
     *            「Data」列の名前にする文字列
     * @return このオブジェクトへの参照
     */
    DataProcessorBuilder<D, R> dataColumnName(String dataColumnName);

    /**
     * 「Result」列の名前を指定された文字列に設定します。デフォルトの名前は「Result」です。
     * 
     * @param resultColumnName
     *            「Result」列の名前にする文字列
     * @return このオブジェクトへの参照
     */
    DataProcessorBuilder<D, R> resultColumnName(String resultColumnName);

    /**
     * 処理結果の初期値を指定された値に設定します。「Input」ボタンもしくは「Reset」ボタンが押され「Result」列が初期化される際にこの値が利用されます。
     * デフォルトの値は「null」です。
     * 
     * @param initialResultValue
     *            「Result」列に表示される処理結果の初期値
     * @return このオブジェクトへの参照
     */
    DataProcessorBuilder<D, R> initialResultValue(R initialResultValue);

    /**
     * GUIの推奨サイズを指定されたサイズに設定します。
     * 
     * @param width
     *            幅
     * @param height
     *            高さ
     * @return このオブジェクトへの参照
     */
    DataProcessorBuilder<D, R> preferredSize(int width, int height);

    /**
     * GUIのタイトルを指定された文字列に設定します。
     * 
     * @param title
     *            GUIのタイトル
     * @return このオブジェクトへの参照
     */
    DataProcessorBuilder<D, R> title(String title);

    /**
     * データの処理と結果の表示を行うGUIアプリケーションを生成します。
     */
    void build();
}
