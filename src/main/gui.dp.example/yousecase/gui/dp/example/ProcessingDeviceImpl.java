package yousecase.gui.dp.example;

import yousecase.gui.dp.DataAndResult;
import yousecase.gui.dp.ProcessingCommand;
import yousecase.gui.dp.ProcessingDevice;

class ProcessingDeviceImpl implements ProcessingDevice<Integer, String> {
    @Override
    public ProcessingCommand process(DataAndResult<Integer, String> dataAndResult) {
        // 処理するデータの取得
        int data = dataAndResult.getData();

        // 視覚的演出のため一時停止
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // dataがオーバーフローする値の場合
        if (data > Integer.MAX_VALUE / 2) {
            // 処理結果の更新
            dataAndResult.setResult("Overflow");

            // 処理の停止
            return ProcessingCommand.STOP;
        }

        // 処理結果の更新
        dataAndResult.setResult(String.valueOf(data * 2));

        // 処理の継続
        return ProcessingCommand.CONTINUE;
    }
}
