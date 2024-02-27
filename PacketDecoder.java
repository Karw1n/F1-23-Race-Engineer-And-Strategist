import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PacketDecoder {
    
    private PacketBuffer packetBuffer;
    private PacketLapData lapDataPacket;
    private PacketParticipantData participantDataPacket;
    private PacketCarTelemetryData carTelemetryDataPacket;
    private PacketEventData eventDataPacket;
    private PacketCarDamageData carDamageDataPacket;
    private PacketTyreSetData tyreSetDataPacket;
    private static List<String> drivers = new ArrayList<>();

    public PacketDecoder(byte[] data) {
        this.packetBuffer = PacketBuffer.wrap(data);
        this.lapDataPacket = new PacketLapData();
        this.participantDataPacket = new PacketParticipantData();
        this.carTelemetryDataPacket =  new PacketCarTelemetryData();
        this.carDamageDataPacket = new PacketCarDamageData();
        this.tyreSetDataPacket = new PacketTyreSetData();
    }

    public PacketDecoder(byte[] data, PacketLapData lapDataPacket, PacketParticipantData participantDataPacket, PacketCarTelemetryData carTelemetryDataPacket){
        this.packetBuffer = PacketBuffer.wrap(data);
        this.lapDataPacket = lapDataPacket;
        this.participantDataPacket = participantDataPacket;
        this.carTelemetryDataPacket =  carTelemetryDataPacket;
    }

    public Packet buildPacket(){

        int packetFormat = packetBuffer.getNextUInt16AsInt();
        int gameYear = packetBuffer.getNextUInt8AsInt();
        int gameMajorVersion = packetBuffer.getNextUInt8AsInt();
        int gameMinorVersion = packetBuffer.getNextUInt8AsInt();
        int packetVersion = packetBuffer.getNextUInt8AsInt();
        int packetId = packetBuffer.getNextUInt8AsInt();
        BigInteger sessionUID = packetBuffer.getNextUInt64AsBigInteger();
        float sessionTime = packetBuffer.getNextFloat();
        long frameIdentifier = packetBuffer.getNextUIntAsLong();
        long overallFrameIdentifier = packetBuffer.getNextUIntAsLong();
        int playerCarIndex = packetBuffer.getNextInt8AsInt();
        int secondayPlayerCarIndex = packetBuffer.getNextInt8AsInt();

        Header header = new Header(packetFormat, gameYear, gameMajorVersion, gameMinorVersion, packetVersion, packetId, sessionUID, sessionTime, frameIdentifier, overallFrameIdentifier, playerCarIndex, secondayPlayerCarIndex);

        if (packetId == 2) {
            return buildLapDataPacket(header);
        } else if (packetId == 3) {
            return buildEventDataPacket(header);
        } else if (packetId == 4) {
            return buildParticipantsDataPacket(header);
        } else if (packetId == 6) {
            return buildCarTelemetryDataPacket(header);
        } else if (packetId == 10) {
            return buildCarDamageDataPacket(header);
        } else if (packetId == 12 && packetBuffer.getNextUInt8AsInt() == header.getPlayerCarIndex()) {
            return buildTyreSetDataPacket(header);
        } else {
            return null;
        }
    }


    public Packet buildLapDataPacket(Header header){
        List<LapData> lapDataList = new ArrayList<>();

        int i = 0;
        // int playerCarIndex = header.getPlayerCarIndex();
        while (i < 20) { //max number of cars

            LapData lapData = new LapData();
            int lastLapTimeInMS = (int) packetBuffer.getNextUIntAsLong();
            lapData.setLastLapTimeInMS(lastLapTimeInMS);
            int currentLapTimeInMS = (int) packetBuffer.getNextUIntAsLong();
            lapData.setCurrentLapTimeInMS(currentLapTimeInMS);
            lapData.setSector1TimeInMS(packetBuffer.getNextUInt16AsInt());
            lapData.setSector1TimeMinutes(packetBuffer.getNextUInt8AsInt());
            lapData.setSector2TimeInMS(packetBuffer.getNextUInt16AsInt());
            lapData.setSector2TimeMinutes(packetBuffer.getNextUInt8AsInt());
            lapData.setDeltaToCarInFrontInMS(packetBuffer.getNextUInt16AsInt());
            lapData.setDeltaToRaceLeaderInMS(packetBuffer.getNextUInt16AsInt());
            lapData.setLapDistance(packetBuffer.getNextFloat());
            lapData.setTotalDistance(packetBuffer.getNextFloat());
            lapData.setSafetyCarDelta(packetBuffer.getNextFloat());
            lapData.setCarPosition(packetBuffer.getNextUInt8AsInt());
            lapData.setCurrentLapNum(packetBuffer.getNextUInt8AsInt());
            lapData.setPitStatus(packetBuffer.getNextUInt8AsInt());
            lapData.setNumPitStops(packetBuffer.getNextUInt8AsInt());
            lapData.setSector(packetBuffer.getNextUInt8AsInt());
            lapData.setCurrentLapInvalid(packetBuffer.getNextUInt8AsInt());
            lapData.setPenalties(packetBuffer.getNextUInt8AsInt());
            lapData.setTotalWarnings(packetBuffer.getNextUInt8AsInt());
            lapData.setCornerCuttingWarnings(packetBuffer.getNextUInt8AsInt());
            lapData.setNumUnservedDriveThroughPens(packetBuffer.getNextUInt8AsInt());
            lapData.setNumUnservedStopGoPens(packetBuffer.getNextUInt8AsInt());
            lapData.setGridPosition(packetBuffer.getNextUInt8AsInt());
            lapData.setDriverStatus(packetBuffer.getNextUInt8AsInt());
            lapData.setResultStatus(packetBuffer.getNextUInt8AsInt());
            lapData.setPitLaneTimerActive(packetBuffer.getNextUInt8AsInt());
            lapData.setPitLaneTimeInLaneInMS(packetBuffer.getNextUInt16AsInt());
            lapData.setPitStopTimerInMS(packetBuffer.getNextUInt16AsInt());
            lapData.setPitStopShouldServePen(packetBuffer.getNextUInt8AsInt());
            if (!(PacketDecoder.drivers.isEmpty())) {
                lapData.setDriverName(PacketDecoder.drivers.get(i));
            }

            lapDataList.add(lapData);
            i++;
        }
        
        this.lapDataPacket.setHeader(header);
        this.lapDataPacket.setLapData(lapDataList);
        return lapDataPacket;
    }

    public Packet buildParticipantsDataPacket(Header header) {
        List<ParticipantData> participantDataList = new ArrayList<>();
        this.participantDataPacket.setHeader(header);

        this.participantDataPacket.setNumActiveCars(packetBuffer.getNextUInt8AsInt());
        for (int i = 0; i < 20; i++) {
            ParticipantData participantData = new ParticipantData();
            participantData.setAiControlled(packetBuffer.getNextUInt8AsInt());
            participantData.setDriverId(packetBuffer.getNextUInt8AsInt());
            participantData.setNetworkID(packetBuffer.getNextUInt8AsInt());
            participantData.setTeamID(packetBuffer.getNextUInt8AsInt());
            participantData.setMyTeam(packetBuffer.getNextUInt8AsInt());
            participantData.setRaceNumber(packetBuffer.getNextUInt8AsInt());
            participantData.setNationality(packetBuffer.getNextUInt8AsInt());
            String name = packetBuffer.getNextCharArrayAsString(48);
            name = name.replaceAll("\u0000", "");
            participantData.setName(name);
            PacketDecoder.drivers.add(i, name);
            participantData.setYourTelemtry(packetBuffer.getNextUInt8AsInt());
            participantData.setShowOnlineNames(packetBuffer.getNextUInt8AsInt());
            participantData.setPlatform(packetBuffer.getNextUInt8AsInt());
            participantDataList.add(participantData);
        }
        this.participantDataPacket.setParticipantData(participantDataList);
        return participantDataPacket;
    }

    public Packet buildCarTelemetryDataPacket(Header header) {
        List<CarTelemetryData> carTelemetryDataList = new ArrayList<>();
        this.carTelemetryDataPacket.setHeader(header);

        for (int i = 0; i < 20; i++) {
            CarTelemetryData carTelemetryData = new CarTelemetryData();
            if (!(PacketDecoder.drivers.isEmpty())) {
                carTelemetryData.setDriverName(PacketDecoder.drivers.get(i));
            }
            carTelemetryData.setSpeed(packetBuffer.getNextUInt16AsInt());
            carTelemetryData.setThrottle(packetBuffer.getNextFloat());
            carTelemetryData.setSteer(packetBuffer.getNextFloat());
            carTelemetryData.setBrake(packetBuffer.getNextFloat());
            carTelemetryData.setClutch(packetBuffer.getNextUInt8AsInt());
            carTelemetryData.setGear(packetBuffer.getNextInt8AsInt());
            carTelemetryData.setEngineRPM(packetBuffer.getNextUInt16AsInt());
            carTelemetryData.setDrs(packetBuffer.getNextInt8AsInt());
            carTelemetryData.setRevLightsPercent(packetBuffer.getNextUInt16AsInt());
            carTelemetryData.setRevLightsBitValue(packetBuffer.getNextUInt16AsInt());
            int rl = packetBuffer.getNextUInt16AsInt();
            int rr = packetBuffer.getNextUInt16AsInt();
            int fl = packetBuffer.getNextUInt16AsInt();
            int fr = packetBuffer.getNextUInt16AsInt();
            WheelData<Integer> wheelData = new WheelData<>(rl, rr, fl, fr);
            carTelemetryData.setBrakeTemperature(wheelData);
            rl = packetBuffer.getNextUInt8AsInt();
            rr = packetBuffer.getNextUInt8AsInt();
            fl = packetBuffer.getNextUInt8AsInt();
            fr = packetBuffer.getNextUInt8AsInt();
            wheelData = new WheelData<>(rl, rr, fl, fr);
            carTelemetryData.setTyresSurfaceTemperature(wheelData);
            rl = packetBuffer.getNextUInt8AsInt();
            rr = packetBuffer.getNextUInt8AsInt();
            fl = packetBuffer.getNextUInt8AsInt();
            fr = packetBuffer.getNextUInt8AsInt();
            wheelData = new WheelData<>(rl, rr, fl, fr);
            carTelemetryData.setTyresInnerTemperature(wheelData);
            carTelemetryData.setEngineTemperature(packetBuffer.getNextUInt16AsInt());
            float rl_pressure = packetBuffer.getNextFloat();
            float rr_pressure = packetBuffer.getNextFloat();
            float fl_pressure = packetBuffer.getNextFloat();
            float fr_pressure = packetBuffer.getNextFloat();
            WheelData<Float> wheelData2 = new WheelData<>(rl_pressure, rr_pressure, fl_pressure, fr_pressure);
            carTelemetryData.setTyresPressure(wheelData2);
            rl = packetBuffer.getNextUInt8AsInt();
            rr = packetBuffer.getNextUInt8AsInt();
            fl = packetBuffer.getNextUInt8AsInt();
            fr = packetBuffer.getNextUInt8AsInt();
            wheelData = new WheelData<>(rl, rr, fl, fr);
            carTelemetryData.setSurfaceType(wheelData);
            // might need to add rest of packet information
            carTelemetryDataList.add(carTelemetryData);
        }

        this.carTelemetryDataPacket.setCarTelemetryData(carTelemetryDataList);
        return carTelemetryDataPacket;
    }

    public Packet buildEventDataPacket(Header header) {
        this.eventDataPacket = new PacketEventData();
        this.eventDataPacket.setHeader(header);

        eventDataPacket.setEventStringCode(packetBuffer.getNextCharArrayAsString(4));
        if (eventDataPacket.getEventStringCode().equals("FTLP")) {
            FastestLap fastestLapData = new FastestLap();
            fastestLapData.setVehicleIdx(packetBuffer.getNextInt8AsInt());
            if (!(PacketDecoder.drivers.isEmpty())) {
                fastestLapData.setDriverName(PacketDecoder.drivers.get(fastestLapData.getVehicleIdx()));
            }
            fastestLapData.setLapTime(packetBuffer.getNextFloat());

            eventDataPacket.setFastestLap(fastestLapData);
        }
        return eventDataPacket;
    }

    public Packet buildCarDamageDataPacket(Header header) {
        this.carDamageDataPacket.setHeader(header);
        List<CarDamageData> carDamageDataList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            CarDamageData carDamageData = new CarDamageData();
            if (!(PacketDecoder.drivers.isEmpty())) {
                carDamageData.setDriverName(PacketDecoder.drivers.get(i));
            }
            carDamageData.setTyresWear(packetBuffer.getNextFloatArray(4));
            carDamageData.setTyresDamage(packetBuffer.getNextUInt8ArrayAsIntegerArray(4));
            carDamageData.setBrakesDamage(packetBuffer.getNextUInt8ArrayAsIntegerArray(4));
            carDamageData.setFrontLeftWingDamage(packetBuffer.getNextUInt8AsInt());
            carDamageData.setFrontRightWingDamage(packetBuffer.getNextUInt8AsInt());
            carDamageData.setRearWingDamage(packetBuffer.getNextUInt8AsInt());
            carDamageData.setFloorDamage(packetBuffer.getNextUInt8AsInt());
            carDamageData.setDiffuserDamage(packetBuffer.getNextUInt8AsInt());
            carDamageData.setSidepodDamage(packetBuffer.getNextUInt8AsInt());
            carDamageData.setDrsFault(packetBuffer.getNextUInt8AsInt());
            carDamageData.setErsFault(packetBuffer.getNextUInt8AsInt());
            carDamageData.setGearBoxDamage(packetBuffer.getNextUInt8AsInt());
            carDamageData.setEngineDamage(packetBuffer.getNextUInt8AsInt());
            carDamageData.setEngineMGUHWear(packetBuffer.getNextUInt8AsInt());
            carDamageData.setEngineESWear(packetBuffer.getNextUInt8AsInt());
            carDamageData.setEngineCEWear(packetBuffer.getNextUInt8AsInt());
            carDamageData.setEngineICEWear(packetBuffer.getNextUInt8AsInt());
            carDamageData.setEngineMGUKWear(packetBuffer.getNextUInt8AsInt());
            carDamageData.setEngineTCWear(packetBuffer.getNextUInt8AsInt());
            carDamageData.setEngineBlown(packetBuffer.getNextUInt8AsInt());
            carDamageData.setEngineSeized(packetBuffer.getNextUInt8AsInt());
            carDamageDataList.add(carDamageData);
        }
        this.carDamageDataPacket.setCarDamageData(carDamageDataList);
        return carDamageDataPacket;
    }

    public Packet buildTyreSetDataPacket(Header header) {
        this.tyreSetDataPacket.setHeader(header);
        this.tyreSetDataPacket.setCarIdx(packetBuffer.getNextUInt8AsInt());
        if (!(PacketDecoder.drivers.isEmpty())) {
            this.tyreSetDataPacket.setDriver(PacketDecoder.drivers.get(header.getPlayerCarIndex()));
        }
        
        List<TyreSetData> tyreSetDataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TyreSetData tyreSetData = new TyreSetData();
            tyreSetData.setActualTyreCompound(packetBuffer.getNextUInt8AsInt());
            tyreSetData.setVisualTyreCompound(packetBuffer.getNextUInt8AsInt());
            tyreSetData.setWear(packetBuffer.getNextUInt8AsInt());
            tyreSetData.setAvailable(packetBuffer.getNextUInt8AsInt());
            tyreSetData.setRecommendedSession(packetBuffer.getNextUInt8AsInt());
            tyreSetData.setLifeSpan(packetBuffer.getNextUInt8AsInt());
            tyreSetData.setUsableLife(packetBuffer.getNextUInt8AsInt());
            tyreSetData.setLapDeltaTime(packetBuffer.getNextInt8AsInt()); //this might lead to errors as it's unsigned
            tyreSetData.setFitted(packetBuffer.getNextUInt8AsInt());
            tyreSetDataList.add(tyreSetData);
        }
        this.tyreSetDataPacket.setTyreSetData(tyreSetDataList);
        this.tyreSetDataPacket.setFittedIdx(packetBuffer.getNextUInt8AsInt());
        return tyreSetDataPacket;
    }
}
