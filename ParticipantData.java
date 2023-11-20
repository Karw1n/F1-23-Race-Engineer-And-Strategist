public class ParticipantData {
    private int aiControlled;
    private int driverId;
    private int networkID;
    private int teamID;
    private int myTeam;
    private int raceNumber;
    private int nationality;
    private String name;
    private int yourTelemtry;
    private int showOnlineNames;
    private int platform;

    public ParticipantData(){}

    public int getAiControlled() {
        return aiControlled;
    }

    public void setAiControlled(int aiControlled) {
        this.aiControlled = aiControlled;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getNetworkID() {
        return networkID;
    }

    public void setNetworkID(int networkID) {
        this.networkID = networkID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getMyTeam() {
        return myTeam;
    }

    public void setMyTeam(int myTeam) {
        this.myTeam = myTeam;
    }

    public int getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(int raceNumber) {
        this.raceNumber = raceNumber;
    }

    public int getNationality() {
        return nationality;
    }

    public void setNationality(int nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYourTelemtry() {
        return yourTelemtry;
    }

    public void setYourTelemtry(int yourTelemtry) {
        this.yourTelemtry = yourTelemtry;
    }

    public int getShowOnlineNames() {
        return showOnlineNames;
    }

    public void setShowOnlineNames(int showOnlineNames) {
        this.showOnlineNames = showOnlineNames;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }
    
    public String toString() {
        //Note: Not full toString
        String string = "AiControlled:" + aiControlled + "\nDriverId:" + driverId + "\nNetworkID:" + networkID 
                        + "\nName:" + name;
        return string;
    }

}
