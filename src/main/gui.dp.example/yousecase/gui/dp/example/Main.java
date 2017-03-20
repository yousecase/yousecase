package yousecase.gui.dp.example;

import yousecase.gui.dp.DataProcessorBuilder;
import yousecase.gui.dp.InputDevice;
import yousecase.gui.dp.ProcessingDevice;

// 1から10をデータとして入力し、データを2倍した値を処理結果として表示する
// 意図的に処理を停止するために8の代わりに不正な値を入力する
class Main {
    public static void main(String[] args) {

        // データを入力するオブジェクト
        InputDevice<Integer> inputDevice = new InputDeviceImpl();

        // データを処理するオブジェクト
        ProcessingDevice<Integer, String> processingDevice = new ProcessingDeviceImpl();

        // 必須パラメータを指定してビルダーを生成
        DataProcessorBuilder.newInstance(inputDevice, processingDevice)

                // オプションパラメータ指定
                .inputButtonName("input")//
                .startButtonName("start")//
                .stopButtonName("stop")//
                .resetButtonName("reset")//
                .dataColumnName("data")//
                .resultColumnName("result")//
                .initialResultValue("-")//
                .preferredSize(350, 350)//
                .title("example")

                // アプリケーション生成
                .build();
    }
}
