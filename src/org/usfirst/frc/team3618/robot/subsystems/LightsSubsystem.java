package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.commands.LightsCommand;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LightsSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public final DigitalOutput red = new DigitalOutput(1);
	public final DigitalOutput blue = new DigitalOutput(2);
	public final DigitalOutput green = new DigitalOutput(3);
	public final DigitalOutput clamp = new DigitalOutput(4);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LightsCommand());
    }
}

