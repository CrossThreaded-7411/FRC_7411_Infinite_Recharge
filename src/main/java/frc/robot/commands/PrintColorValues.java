package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TCS3472Subsystem;

public class PrintColorValues extends CommandBase {

    TCS3472Subsystem colorSensor;
    public PrintColorValues(TCS3472Subsystem subsystem) {
        colorSensor = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        int[] values = colorSensor.getData();
        int red = values[1];
        int green = values[2];
        int blue = values[3];

        // System.out.print("Red: ");
        // System.out.print(red);
        // System.out.print("  Green: ");
        // System.out.print(green);
        // System.out.print("  Blue: ");
        // System.out.println(blue);
    }





}