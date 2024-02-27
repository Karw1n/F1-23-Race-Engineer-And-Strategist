

import java.util.List;

public class PacketTyreSetData extends Packet{
    private List<TyreSetData> tyreSetDataList;
    private String driver;
    private int carIdx;
    private int fittedIdx;

    public void setTyreSetData(List<TyreSetData> tyreSetDataList) {
        this.tyreSetDataList = tyreSetDataList;
    }

    public List<TyreSetData> getTyreSetDataList() {
        return tyreSetDataList;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
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

    public String toString() {
        String string = "Tyre Set Data Packet Information: \n";
        string += "Driver Name            : " + driver + "\n";
        string += "-------------------\n";
        for (TyreSetData tyreSetData : tyreSetDataList) {
            string += tyreSetData.toString() + "\n";
            string += "-------------------\n";
        }
        string += "\n-------------------\n";
        return string;
    }

}
