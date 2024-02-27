public class CarDamageData {
    private String driverName;
    private Float[] tyresWear;
    private Integer[] tyresDamage;
    private Integer[] brakesDamage;
    private int frontLeftWingDamage;
    private int frontRightWingDamage;
    private int rearWingDamage;
    private int floorDamage;
    private int diffuserDamage;
    private int sidepodDamage;
    private int drsFault;
    private int ersFault;
    private int gearBoxDamage;
    private int engineDamage;
    private int engineMGUHWear;
    private int engineESWear;
    private int engineCEWear;
    private int engineICEWear;
    private int engineMGUKWear;
    private int engineTCWear;
    private int engineBlown;
    private int engineSeized;

    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driver) {
        this.driverName = driver;
    }
    
    public Float[] getTyresWear() {
        return tyresWear;
    }
    public void setTyresWear(Float[] floats) {
        this.tyresWear = floats;
    }
    public Integer[] getTyresDamage() {
        return tyresDamage;
    }
    public void setTyresDamage(Integer[] tyresDamage) {
        this.tyresDamage = tyresDamage;
    }
    public Integer[] getBrakesDamage() {
        return brakesDamage;
    }
    public void setBrakesDamage(Integer[] brakesDamage) {
        this.brakesDamage = brakesDamage;
    }
    public int getFrontLeftWingDamage() {
        return frontLeftWingDamage;
    }
    public void setFrontLeftWingDamage(int frontLeftWingDamage) {
        this.frontLeftWingDamage = frontLeftWingDamage;
    }
    public int getFrontRightWingDamage() {
        return frontRightWingDamage;
    }
    public void setFrontRightWingDamage(int frontRightWingDamage) {
        this.frontRightWingDamage = frontRightWingDamage;
    }
    public int getRearWingDamage() {
        return rearWingDamage;
    }
    public void setRearWingDamage(int rearWingDamage) {
        this.rearWingDamage = rearWingDamage;
    }
    public int getFloorDamage() {
        return floorDamage;
    }
    public void setFloorDamage(int floorDamage) {
        this.floorDamage = floorDamage;
    }
    public int getDiffuserDamage() {
        return diffuserDamage;
    }
    public void setDiffuserDamage(int diffuserDamage) {
        this.diffuserDamage = diffuserDamage;
    }
    public int getSidepodDamage() {
        return sidepodDamage;
    }
    public void setSidepodDamage(int sidepodDamage) {
        this.sidepodDamage = sidepodDamage;
    }
    public int getDrsFault() {
        return drsFault;
    }
    public void setDrsFault(int drsFault) {
        this.drsFault = drsFault;
    }
    public int getErsFault() {
        return ersFault;
    }
    public void setErsFault(int ersFault) {
        this.ersFault = ersFault;
    }
    public int getGearBoxDamage() {
        return gearBoxDamage;
    }
    public void setGearBoxDamage(int gearBoxDamage) {
        this.gearBoxDamage = gearBoxDamage;
    }
    public int getEngineDamage() {
        return engineDamage;
    }
    public void setEngineDamage(int engineDamage) {
        this.engineDamage = engineDamage;
    }
    public int getEngineMGUHWear() {
        return engineMGUHWear;
    }
    public void setEngineMGUHWear(int engineMGUHWear) {
        this.engineMGUHWear = engineMGUHWear;
    }
    public int getEngineESWear() {
        return engineESWear;
    }
    public void setEngineESWear(int engineESWear) {
        this.engineESWear = engineESWear;
    }
    public int getEngineCEWear() {
        return engineCEWear;
    }
    public void setEngineCEWear(int engineCEWear) {
        this.engineCEWear = engineCEWear;
    }
    public int getEngineICEWear() {
        return engineICEWear;
    }
    public void setEngineICEWear(int engineICEWear) {
        this.engineICEWear = engineICEWear;
    }
    public int getEngineMGUKWear() {
        return engineMGUKWear;
    }
    public void setEngineMGUKWear(int engineMGUKWear) {
        this.engineMGUKWear = engineMGUKWear;
    }
    public int getEngineTCWear() {
        return engineTCWear;
    }
    public void setEngineTCWear(int engineTCWear) {
        this.engineTCWear = engineTCWear;
    }
    public int getEngineBlown() {
        return engineBlown;
    }
    public void setEngineBlown(int engineBlown) {
        this.engineBlown = engineBlown;
    }
    public int getEngineSeized() {
        return engineSeized;
    }
    public void setEngineSeized(int engineSeized) {
        this.engineSeized = engineSeized;
    }

    public float getTyresWearAvg() {
        float sum = 0;
        for (float num : tyresWear) {
            sum += num;
        }
        return sum / tyresWear.length;
    }

    public float getTyresDamageAvg() {
        float sum = 0;
        for (int num : tyresDamage) {
            sum += num;
        }
        return sum / tyresDamage.length;
    }

    public float getBrakesDamageAvg() {
        float sum = 0;
        for (int num : brakesDamage) {
            sum += num;
        }
        return sum / brakesDamage.length;
    }

    public String toString() {
        String string = 
            "Driver Name            : " + driverName +
          "\nTyres Wear             : " + getTyresWearAvg() + 
          "\nTyres Damage           : " + getTyresDamageAvg() +
          "\nBrakes Damage          : " + getBrakesDamageAvg() +
          "\nFront Left Wing Damage : " + frontLeftWingDamage +
          "\nFront Right Wing Damage: " + frontRightWingDamage +
          "\nRear Wing Damage       : " + rearWingDamage +
          "\nFloor Damage           : " + floorDamage +
          "\nDiffuser Damage        : " + diffuserDamage +
          "\nSidepod Damage         : " + sidepodDamage +
          "\nDRS Fault              : " + drsFault +
          "\nERS Fault              : " + ersFault +
          "\nGearbox Damage         : " + gearBoxDamage +
          "\nEngine Damage          : " + engineDamage +
          "\nMGUH Wear              : " + engineMGUHWear +
          "\nES Wear                : " + engineESWear +
          "\nCE Wear                : " + engineCEWear +
          "\nICE Wear               : " + engineICEWear +
          "\nMGUK Wear              : " + engineMGUKWear +
          "\nTC Wear                : " + engineTCWear +
          "\nEngine Blown           : " + engineBlown +
          "\nEngine Seized          : " + engineSeized;
          return string;

    }

}
