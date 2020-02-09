package frc.robot.subsystems;

import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TCS3472Subsystem extends SubsystemBase {

    private I2C i2cSource;
    private int deviceAddress;
    private int id;
    private boolean enabled;

    private final int ENABLE = 0x00; // 0x00
    private final int ATIME = 0x01; // 0x01
    private final int CDATAL = 0x14; // 0x14 0xB4 0x94
    private final int CDATAH = 0x15; // 0x15
    private final int RDATAL = 0x16; // 0x16 0xB6 0x96
    private final int RDATAH = 0x17; // 0x17
    private final int GDATAL = 0x18; // 0x18 0xB8 0x98
    private final int GDATAH = 0x19; // 0x19
    private final int BDATAL = 0x1A; // 0x1A 0xBA 0x9A
    private final int BDATAH = 0x1B; // 0x1B
    private final int GAIN = 0x0F;
    private final int COMMANDBIT = 0x80;
    private final int AUTOINCREMENT = 0x20;

    public TCS3472Subsystem(int deviceAddress, int id){
        System.out.println("Entered TCS subsystem constructor");

        this.id = id;
        this.deviceAddress = deviceAddress;
        i2cSource = new I2C(I2C.Port.kOnboard, this.deviceAddress);
        enabled = false;
        System.out.println("Finished TCS subsystem constructor");
    }

    public void enable() {
        write8(ENABLE, 3);  // 00000011 enables RGBC and Power ON
        enabled = true;
        System.out.println("Enabled TCS");
    }

    public void disable() {
        write8(ENABLE, 0);
        enabled = false;
    }

    public void setATIME() {
        write8(ATIME, 246);
    }

    public void setGAIN() {
        write8(GAIN, 0x01);
    }

    public void init() {
        setATIME();
        setGAIN();
        enable();
    }

    public int[] getData() {
        // This will return 4 fields - clear, red, green, blue
        byte[] chipId = new byte[1];
        chipId[0] = 0x00;
        System.out.println(i2cSource.read(0x92, 1, chipId));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println(chipId[0]);
        int[] output= {-1,-1,-1,-1};
        if(enabled == true) {
            int redValue = read16(RDATAL);
            int blueValue = read16(BDATAL);
            int greenValue = read16(GDATAL);
            int clearValue = read16(CDATAL);

            output[0] = 0;
            output[1] = redValue;
            output[2] = greenValue;
            output[3] = blueValue;
        } else {
            init();
            output[0] = -1;
            output[1] = -1;
            output[2] = -1;
            output[3] = -1;
        }

        return output;
    }

    private int read16(int register) {
        ByteBuffer rawByte = ByteBuffer.allocate(2);
        i2cSource.read(COMMANDBIT | AUTOINCREMENT | register, 2, rawByte);
        byte lo = rawByte.get();
        byte hi = rawByte.get();

        int result = ((hi & 0xFF) << 8) | (lo & 0xFF);
        return result;
    }

    private void write8(int register, int value){
        i2cSource.write(COMMANDBIT | register, (byte) (value & 0xFF));
    }
        

}
