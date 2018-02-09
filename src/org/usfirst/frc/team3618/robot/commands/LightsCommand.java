package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LightsCommand extends Command {

    public LightsCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kLightsSubsystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kLightsSubsystem.red.set(false);
    	Robot.kLightsSubsystem.green.set(false);
    	Robot.kLightsSubsystem.blue.set(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int light;
    	light = Math.floorMod((int) timeSinceInitialized(), 3);
    	if (light == 0) {
    		Robot.kLightsSubsystem.green.set(false);
    		Robot.kLightsSubsystem.red.set(true);
    	} else if (light == 1) {
    		Robot.kLightsSubsystem.red.set(false);
    		Robot.kLightsSubsystem.blue.set(true);
    	} else {
    		Robot.kLightsSubsystem.blue.set(false);
    		Robot.kLightsSubsystem.green.set(true);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kLightsSubsystem.red.set(false);
    	Robot.kLightsSubsystem.green.set(false);
    	Robot.kLightsSubsystem.blue.set(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
