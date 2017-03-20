package yousecase.gui.dp;

/**
 * {@link ProcessingDevice}の結果型です。
 */
public enum ProcessingCommand {

    /**
     * 処理を継続します。
     */
    CONTINUE,

    /**
     * 処理を停止します。処理が再開された場合、最後に処理を行った行の次の行から処理が開始されます。
     */
    STOP,

    /**
     * 処理を停止します。処理が再開された場合、最後に処理を行った行から処理が開始されます。
     */
    STOP_AT_CURRENT_ROW
}
