/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot;

import java.time.LocalDateTime;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.lang.System;


// This class creates a class for logging data to the console and/or file
public class RobotLogger
{
   static private FileHandler logFile;
   private static boolean loggerEnabled = false;

   public static void init()
   {
      try
      {
         RobotLogger.setup();
         loggerEnabled = true;
      }
      catch (IOException e)
      {
         e.printStackTrace();
         throw new RuntimeException("Problems with creating the log files");
      }
   }


   public static void setup() throws IOException
   {
      // Get the global logger to configure it
      Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

      /**
       * Customize formatting to our preference
       * "%1$tT.%1$tL [%4$s]: %5$s%n" prints line as "Time [level] Text Data"
       */
      System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tT.%1$tL [%4$s]: %5$s%n");

      // Set the level of messages to log
      logger.setLevel(Level.FINER);

      // Specify the log file and the the file format
      String fileName = "RobotLog_" + LocalDateTime.now() + ".log";
      logFile = new FileHandler("/home/lvuser/logs/" + fileName);
      // logFile = new FileHandler("/u" + fileName);
 
      logFile.setFormatter(new SimpleFormatter());
      logger.addHandler(logFile);
   }


   /**
    * Manages logging data to the Java Logging Framework. All methods that manage
    * logging of data should be consolidated here.
    * @param enabled True enables logging, False disables
    */
   public static void logData()
   {
      if (loggerEnabled)
      {
         /**
          * Place all calls to logging methods here. This consolidates logging requests
          * so they are not sprinkledthroughout code and easier to manage.
          */
         Robot.m_robotContainer.driveTrainSubsystem.logDriveTrainData(true);
         Robot.m_robotContainer.liftSubsystem.logLiftData(true);
         Robot.m_robotContainer.ballShooterSubsystem.logBallShooterData(true);
         Robot.m_robotContainer.ballFeederSubsystem.logBallFeederData(true);
         Robot.m_robotContainer.ballCollectorSubsystem.logBallCollectorData(true);
         Robot.m_robotContainer.turretSubsystem.logTurretData(true);
         Robot.m_robotContainer.recordPlayerSubsystem.logRecordPlayerData(true);
         Robot.m_robotContainer.pdpSubsystem.logPDPData(true);
      }
   }
}