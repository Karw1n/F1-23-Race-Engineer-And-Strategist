package data.elements;
import java.math.BigInteger;

public class Header {
    private int packetFormat;
    private int gameYear;
    private int gameMajorVersion;
    private int gameMinorVersion;
    private int packetVersion;
    private int packetId;
    private BigInteger sessionUID;
    private float sessionTime;
    private long frameIdentifier;
    private long overallFrameIdentifier;
    private int playerCarIndex;
    private int secondayPlayerCarIndex;

    public Header(int packetFormat, int gameYear, int gameMajorVersion, int gameMinorVersion,
                int packetVersion, int packetId, BigInteger sessionUID, float sessionTime,
                long frameIdentifier, long overallFrameIdentifier, int playerCarIndex, int secondayPlayerCarIndex){
    this.packetFormat = packetFormat;
    this.gameYear = gameYear;
    this.gameMajorVersion = gameMajorVersion;
    this.gameMinorVersion = gameMinorVersion;
    this.packetVersion = packetVersion;
    this.packetId = packetId;
    this.sessionUID = sessionUID;
    this.sessionTime = sessionTime;
    this.frameIdentifier = frameIdentifier;
    this.overallFrameIdentifier = overallFrameIdentifier;
    this.playerCarIndex = playerCarIndex;
    this.secondayPlayerCarIndex = secondayPlayerCarIndex;
    }

    public int getPacketFormat() {
        return packetFormat;
    }

    public void setPacketFormat(int packetFormat) {
        this.packetFormat = packetFormat;
    }

    public int getGameYear() {
        return gameYear;
    }

    public void setGameYear(int gameYear) {
        this.gameYear = gameYear;
    }

    public int getGameMajorVersion() {
        return gameMajorVersion;
    }

    public void setGameMajorVersion(int gameMajorVersion) {
        this.gameMajorVersion = gameMajorVersion;
    }

    public int getGameMinorVersion() {
        return gameMinorVersion;
    }
    
    public void setGameMinorVersion(int gameMinorVersion) {
        this.gameMinorVersion = gameMinorVersion;
    }

    public int getPacketVersion() {
        return packetVersion;
    }

    public void setPacketVersion(int packetVersion) {
        this.packetVersion = packetVersion;
    }

    public int getPacketId() {
        return packetId;
    }

    public void setPacketId(int packetId) {
        this.packetId = packetId;
    }

    public BigInteger getSessionUID() {
        return sessionUID;
    }

    public void setSessionUID(BigInteger sessionUID) {
        this.sessionUID = sessionUID;
    }

    public float getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(float sessionTime) {
        this.sessionTime = sessionTime;
    }

    public long getFrameIdentifier() {
        return frameIdentifier;
    }

    public void setFrameIdentifier(long frameIdentifier) {
        this.frameIdentifier = frameIdentifier;
    }

    public long getOverallFrameIdentifier() {
        return overallFrameIdentifier;
    }

    public void setOverallFrameIdentifier(long overallFrameIdentifier) {
        this.overallFrameIdentifier = overallFrameIdentifier;
    }

    public int getPlayerCarIndex() {
        return playerCarIndex;
    }

    public void setPlayerCarIndex(int playerCarIndex) {
        this.playerCarIndex = playerCarIndex;
    }

    public int getSecondayPlayerCarIndex() {
        return secondayPlayerCarIndex;
    }

    public void setSecondayPlayerCarIndex(int secondayPlayerCarIndex) {
        this.secondayPlayerCarIndex = secondayPlayerCarIndex;
    }

    public String toString() {
        String string = "Packet Id:" + getPacketId() +  " Session Time:" + getSessionTime() + " Player:" + getPlayerCarIndex() + "\n";
        return string;
    }

}
