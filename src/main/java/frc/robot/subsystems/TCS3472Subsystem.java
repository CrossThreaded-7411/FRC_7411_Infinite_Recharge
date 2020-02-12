package frc.robot.subsystems;

import java.nio.ByteBuffer;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TCS3472Subsystem extends SubsystemBase
{
   private I2C i2cSource;
   private int deviceAddress;
   private int id;
   private boolean enabled;

   private enum Command
   {
      write(0x00),
      read(0x01);

      public final int value;

      Command(int value)
      {
         this.value = value;
      }
   }

   // Registar Addresses
   private final int ENABLE = 0x00;    // Enables states and interrupts
   private final int ATIME = 0x01;     // RGBC time
   private final int WTIME = 0x03;     // Wait time
   private final int AILTL = 0x04;     // Clear interrupt low threshold low byte
   private final int AILTH = 0x05;     // Clear interrupt low threshold high byte
   private final int AIHTL = 0x06;     // Clear interrupt high threshold low byte
   private final int AIHTH = 0x07;     // Clear interrupt high threshold high byte
   private final int PERS = 0x0C;      // Interrupt persistance filter
   private final int CONFIG = 0x0D;    // Configuration
   // private final int CONTROL =0x0F;    // Control
   private final int GAIN = 0x0F;    // Control
   private final int ID = 0x12;        // Device ID
   private final int STATUS = 0x13;    // Device status
   private final int CDATAL = 0x14;    // Clear data low byte
   private final int CDATAH = 0x15;    // Clear data high byte
   private final int RDATAL = 0x16;    // Red data low byte
   private final int RDATAH = 0x17;    // Red data high byte
   private final int GDATAL = 0x18;    // Gree data low byte
   private final int GDATAH = 0x19;    // Green data high byte
   private final int BDATAL = 0x1A;    // Blue data low byte
   private final int BDATAH = 0x1B;    // Blue data high byte
   // private final int GAIN = 0x0F; 
   private final int COMMANDBIT = 0x80;
   private final int AUTOINCREMENT = 0x20;

   public TCS3472Subsystem(int deviceAddress, int id)
   {
      System.out.println("Entered TCS subsystem constructor");

      this.id = id;
      this.deviceAddress = deviceAddress;
      i2cSource = new I2C(I2C.Port.kOnboard, this.deviceAddress);
      enabled = false;
      System.out.println("Finished TCS subsystem constructor");
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
      write8(ENABLE, 0);
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
   public void setGAIN()
   {
      // write8(CONTROL, Command.read.value);
      write8(GAIN, 0x01);
   }


   public void init()
   {
      setATIME();
      setGAIN();
      enable();
   }


   // Check to see if new data is in the data register
   public void getStatus()
   {
      ByteBuffer rawByte = ByteBuffer.allocate(1);
      i2cSource.read(COMMANDBIT | STATUS, 1, rawByte);
      System.out.println("Status: " +  rawByte.get());
   }


   public int[] getData()
   {
      // This will return 4 fields - clear, red, green, blue
      byte[] chipId = new byte[1];
      chipId[0] = 0x00;
      //System.out.println(i2cSource.read(0x92, 1, chipId));
      
      try
      {
         Thread.sleep(100);
      }
      catch (InterruptedException e)
      {
         System.out.println("InterruptedException");
      }

      System.out.println(chipId[0]);
      int[] output = {-1, -1, -1, -1};
      
      if (enabled == true)
      {
         int redValue = read16(RDATAL);
         int blueValue = read16(BDATAL);
         int greenValue = read16(GDATAL);
         int clearValue = read16(CDATAL);

         output[0] = 0;
         output[1] = redValue;
         output[2] = greenValue;
         output[3] = blueValue;
      }
      else
      {
         init();
         output[0] = -1;
         output[1] = -1;
         output[2] = -1;
         output[3] = -1;
      }

      return output;
   }


   // Read 16 bit data value
   private int read16(int register)
   {
      ByteBuffer rawByte = ByteBuffer.allocate(2);
      i2cSource.read(COMMANDBIT | AUTOINCREMENT | register, 2, rawByte);
      byte lo = rawByte.get();
      byte hi = rawByte.get();

      int result = ((hi & 0xFF) << 8) | (lo & 0xFF);
      return result;
   }
   

   private void write8(int register, int value)
   {
      i2cSource.write(COMMANDBIT | register, (byte) (value & 0xFF));
   }
}
