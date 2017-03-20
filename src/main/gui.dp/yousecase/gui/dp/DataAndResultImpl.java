package yousecase.gui.dp;

class DataAndResultImpl<D, R> implements DataAndResult<D, R> {
    private D data;
    private R result;

    public DataAndResultImpl(D data, R result) {
        this.data = data;
        this.result = result;
    }

    @Override
    public D getData() {
        return data;
    }

    @Override
    public void setData(D data) {
        this.data = data;
    }

    @Override
    public R getResult() {
        return result;
    }

    @Override
    public void setResult(R result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DataAndResultImpl [data=" + data + ", result=" + result + "]";
    }
}
