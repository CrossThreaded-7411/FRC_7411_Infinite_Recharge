package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TCS3472Subsystem extends SubsystemBase {

    private I2C i2cSource;
    private int deviceAddress;
    private int id;
    private boolean enabled;

    private final int ENABLE = 0x80; // 0x00
    private final int ATIME = 0x81; // 0x01
    private final int CDATAL = 0x94; // 0x14 0xB4 0x94
    private final int CDATAH = 0x15; // 0x15
    private final int RDATAL = 0xB6; // 0x16 0xB6 0x96
    private final int RDATAH = 0xB7; // 0x17
    private final int GDATAL = 0x98; // 0x18 0xB8 0x98
    private final int GDATAH = 0x19; // 0x19
    private final int BDATAL = 0x9A; // 0x1A 0xBA 0x9A
    private final int BDATAH = 0x1B; // 0x1B

    public TCS3472Subsystem(int deviceAddress, int id){
        System.out.println("Entered TCS subsystem constructor");

        this.id = id;
        this.deviceAddress = deviceAddress;
        i2cSource = new I2C(I2C.Port.kOnboard, this.deviceAddress);
        enabled = false;
        System.out.println("Finished TCS subsystem constructor");

    }

    public void enable() {
        i2cSource.write(ENABLE, 3); // 00000011 enables RGBC and Power ON
        enabled = true;
        System.out.println("Enabled TCS");
    }

    public void disable() {
        i2cSource.write(ENABLE, 0); // 00000000 disables RGBC and Power ON
        enabled = false;
    }

    public void setATIME() {
        i2cSource.write(ATIME, 246); // 0xF6 ----- Integration time = 24ms
    }

    public void init() {
        enable();
        setATIME();
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
            System.out.println("getting TCS");
            byte[] clearBuffer = {0,0};
            byte[] redBuffer = {0,0};
            byte[] greenBuffer = {0, 0};
            byte[] blueBuffer = {0, 0};

            i2cSource.read(CDATAL, 2, clearBuffer);
            i2cSource.read(RDATAL, 2, redBuffer);
            i2cSource.read(GDATAL, 2, greenBuffer);
            i2cSource.read(BDATAL, 2, blueBuffer);
            System.out.println("RedHigh: " + redBuffer[1] + "RedLow: " + redBuffer[0]);

            int redValue = (int)((redBuffer[1] << 8) | (redBuffer[0] & 0xFF));  // Bitshifts the high buffer over 8 and appends the low buffer.
            int greenValue = (int)((greenBuffer[1] << 8) | (greenBuffer[0] & 0xFF));
            int blueValue = (int)((blueBuffer[1] << 8) | (blueBuffer[0] & 0xFF));

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
        

}
