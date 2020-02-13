package frc.robot.subsystems;

import java.nio.ByteBuffer;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TCS3472Subsystem extends SubsystemBase
{
   private I2C i2cSource;
   private int deviceAddress;
   private boolean enabled;

   // Registar Addresses
   private final int ENABLE = 0x00; // Enables states and interrupts
   private final int ATIME = 0x01; // RGBC time
   private final int WTIME = 0x03; // Wait time
   private final int AILTL = 0x04; // Clear interrupt low threshold low byte
   private final int AILTH = 0x05; // Clear interrupt low threshold high byte
   private final int AIHTL = 0x06; // Clear interrupt high threshold low byte
   private final int AIHTH = 0x07; // Clear interrupt high threshold high byte
   private final int PERS = 0x0C; // Interrupt persistance filter
   private final int CONFIG = 0x0D; // Configuration
   private final int CONTROL = 0x0F; // Control
   private final int ID = 0x12; // Device ID
   private final int STATUS = 0x13; // Device status
   private final int CDATAL = 0x14; // Clear data low byte
   private final int CDATAH = 0x15; // Clear data high byte
   private final int RDATAL = 0x16; // Red data low byte
   private final int RDATAH = 0x17; // Red data high byte
   private final int GDATAL = 0x18; // Gree data low byte
   private final int GDATAH = 0x19; // Green data high byte
   private final int BDATAL = 0x1A; // Blue data low byte
   private final int BDATAH = 0x1B; // Blue data high byte
   // private final int GAIN = 0x0F;
   private final int COMMANDBIT = 0x80; // Used to set the MSB
   private final int AUTOINCREMENT = 0x20; // Used to have slave send date consecutively


   // Constructor which created a color sensor given the address
   // For the TCS34742, it is hardcoded to address 0x29
   public TCS3472Subsystem(int deviceAddress)
   {
      this.deviceAddress = deviceAddress;
      i2cSource = new I2C(I2C.Port.kOnboard, this.deviceAddress);
      enabled = false;
   }


   // Used to turn on power and enable the ADC, also can set the wait time and enable
   // interrupt generation but we aren't using those.
   public void enable()
   {
      int regValue = 0x03; // 0000 0011 enables RGBC and Power on
      write8(ENABLE, regValue);
      enabled = true;
      System.out.println("Enabled TCS");
   }


   // Same as as enable method but turns off power and ADC
   public void disable()
   {
      int regValue = 0x00; // 0000 0000 disables RGBC and Power on
      write8(ENABLE, regValue);
      enabled = false;
      System.out.println("Disabled TCS");
   }


   // Sets the internal integration time (a.k.a averaging time). The
   // value is the number of cycles to use before the value is transmitted then reset
   // 0xFF - 1 cycle
   // 0xF6 - 10 cycles
   // 0xD5 - 42 cycles
   // 0xC0 - 64 cycles
   // 0x00 - 256 cycles
   public void setATIME()
   {
      write8(ATIME, 0xF6);
   }


   // Command register sets RGBC gain value
   // 0x00 - 1x
   // 0x01 - 4x
   // 0x02 - 16x
   // 0x03 - 60x
   public void setCONTROL()
   {
      write8(CONTROL, 0x00);
   }


   public void init()
   {
      setATIME();
      setCONTROL();
      enable();
   }


   // Check to see if new data is in the data register
   public void getStatus()
   {
      ByteBuffer rawByte = ByteBuffer.allocate(1);
      i2cSource.read(COMMANDBIT | STATUS, 1, rawByte);
      System.out.println("Status: " + rawByte.get());
   }


   // public int[] getData()
   public void getData()
   {
      // This will return 4 fields - clear, red, green, blue
      // byte[] chipId = new byte[1];
      // chipId[0] = 0x00;
      // System.out.println(i2cSource.read(0x92, 1, chipId));

      ByteBuffer chipId = ByteBuffer.allocate(1);
      i2cSource.read(0x92, 1, chipId);
      // System.out.println(chipId.get());

      // System.out.println(chipId[0]);
      // int[] output = {-1, -1, -1, -1};

      if (enabled == true)
      {
         int redValue = read16(RDATAL);
         int greenValue = read16(GDATAL);
         int blueValue = read16(BDATAL);
         int clearValue = read16(CDATAL);

         double redPercent = 100.0 * (double)redValue / (double)clearValue;
         double greenPercent = 100.0 * (double)greenValue / (double)clearValue;
         double bluePercent = 100.0 * (double)blueValue / (double)clearValue;

         String strRed = String.format("%.2f", redPercent);
         String strGreen = String.format("%.2f", greenPercent);
         String strBlue = String.format("%.2f", bluePercent);

         System.out.println(strRed + ", " + strGreen + ", " + strBlue + ", " + clearValue);

      // // int clearValue = read16(CDATAL);

      // // output[0] = 0;
      // // output[1] = redValue;
      // // output[2] = greenValue;
      // // output[3] = blueValue;
      // }
      // else
      // {
      // init();
      // output[0] = -1;
      // output[1] = -1;
      // output[2] = -1;
      // output[3] = -1;
      }

      // return output;
   }


   // Read 16 bit data value
   public int read16(int register)
   {
      ByteBuffer rawByte = ByteBuffer.allocate(2);
      i2cSource.read(COMMANDBIT | AUTOINCREMENT | register, 2, rawByte);

      // Color is a 16-bit value (2-bytes). These bytes are buffered as BIG ENDIAN.
      // Pull each byte of the buffer in the order lo than hi byte
      byte lo = rawByte.get();
      byte hi = rawByte.get();

      // Assemble teh 16 bit value from the two 8-bit values. This is done through
      // bit shifting the hi byte and using a bitwise OR
      int result = ((hi & 0xFF) << 8) | (lo & 0xFF);
      return result;
   }


   // Write an 8-bit value to a registers
   private void write8(int register, int value)
   {
      i2cSource.write(COMMANDBIT | register, (byte) (value & 0xFF));
   }
}
