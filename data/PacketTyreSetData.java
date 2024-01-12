package data;

import java.util.List;

import data.elements.TyreSetData;

public class PacketTyreSetData {
    private List<TyreSetData> tyreSetDataList;
    private int carIdx;
    private int fittedIdx;

    public void setTyreSetData(List<TyreSetData> tyreSetDataList) {
        this.tyreSetDataList = tyreSetDataList;
    }

    public List<TyreSetData> getTyreSetDataList() {
        return tyreSetDataList;
    }

    public void setCarIdx(int carIdx) {
        this.carIdx = carIdx;
    }

    public int getCarIdx() {
        return carIdx;
    }

    public void setFittedIdx(int fittedIdx) {
        this.fittedIdx = fittedIdx;
    }

    public int getFittedIdx() {
        return fittedIdx;
    }

    public String toString() {}

}
