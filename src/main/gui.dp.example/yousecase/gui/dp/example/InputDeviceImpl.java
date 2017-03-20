package yousecase.gui.dp.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import yousecase.gui.dp.InputDevice;

class InputDeviceImpl implements InputDevice<Integer> {
    @Override
    public List<Integer> input() {

        // 1から10のリスト
        List<Integer> dataList = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        // 2倍するとオーバーフローする値を設定し、ProcessingDeviceImpl#process内で意図的に停止する
        dataList.set(7, Integer.MAX_VALUE / 2 + 1);

        return dataList;
    }
}
