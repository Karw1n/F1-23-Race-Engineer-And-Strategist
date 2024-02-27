import java.util.List;

public class PacketCarDamageData extends Packet {
    
    private List<CarDamageData> CarDamageDataList;

    public void setCarDamageData(List<CarDamageData> carDamageDatas) {
        this.CarDamageDataList = carDamageDatas;
    }

    public List<CarDamageData> getCarDamageDataList() {
        return CarDamageDataList;
    }

    public String toString() {
        String string = "Car Damage Data Packet:";
        for (CarDamageData carDamageData : CarDamageDataList) {
            string += "\n-------------------\n";
            string += carDamageData.toString();
            string += "\n-------------------\n";
        }
        return string;
    }
}
