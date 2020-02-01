package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;

public class TCS3472Subsystem {

    private I2C i2cSource;
    private int deviceAddress;
    private int id;
    private boolean enabled;

    private final int ENABLE = 0; // 0x00
    private final int ATIME = 1; // 0x01
    private final int CDATAL = 20; // 0x14
    private final int CDATAH = 21; // 0x15
    private final int RDATAL = 22; // 0x16
    private final int RDATAH = 23; // 0x17
    private final int GDATAL = 24; // 0x18
    private final int GDATAH = 25; // 0x19
    private final int BDATAL = 26; // 0x1A
    private final int BDATAH = 27; // 0x1B

    public TCS3472Subsystem(int deviceAddress, int id){
        this.id = id;
        this.deviceAddress = deviceAddress;
        i2cSource = new I2C(I2C.Port.kOnboard, deviceAddress);
        enabled = false;
    }

    public void enable() {
        i2cSource.write(ENABLE, 3); // 00000011 enables RGBC and Power ON
        enabled = true;
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
        int[] output;
        if(enabled == true){
            byte[] clearBuffer = {0,0};
            byte[] redBuffer = {0, 0};
            byte[] greenBuffer = {0, 0};
            byte[] blueBuffer = {0, 0};

            i2cSource.read(CDATAL, 2, clearBuffer);
            i2cSource.read(RDATAL, 2, redBuffer);
            i2cSource.read(GDATAL, 2, greenBuffer);
            i2cSource.read(BDATAL, 2, blueBuffer);

            int redValue = (int)((redBuffer[1] << 8) | (redBuffer[0] & 0xFF));
            int greenValue = (int)((greenBuffer[1] << 8) | (greenBuffer[0] & 0xFF));
            int blueValue = (int)((blueBuffer[1] << 8) | (blueBuffer[0] & 0xFF));

            output[0] = 0;
            output[1] = redValue;
            output[2] = greenValue;
            output[3] = blueValue;
        } else {
            output[0] = -1;
            output[1] = -1;
            output[2] = -1;
            output[3] = -1;
        }

        return output;
    }
        

    }

      







}