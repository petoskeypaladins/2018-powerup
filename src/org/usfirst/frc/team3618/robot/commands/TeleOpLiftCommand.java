package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleOpLiftCommand extends Command {
	boolean lastPress;
    public TeleOpLiftCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kLiftSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Current lift voltage",Robot.kLiftSubsystem.getCurrentLiftHeight());
    	SmartDashboard.putBoolean("Lift limit switch value", Robot.kLiftSubsystem.isLiftDown());
    	SmartDashboard.putNumber("Encoder value", Robot.kLiftSubsystem.getEncoderValue());
    	
//    	boolean thisPress = Robot.m_oi.functionController.getRawButton(12);
//    	if(thisPress != lastPress) {
//    		lastPress = thisPress;
//    		if(lastPress && Robot.kLiftSubsystem.isLiftDown()) {
//    			if (Robot.kLiftSubsystem.lock.get() == LiftSubsystem.LOCKED) {
//    				Robot.kLiftSubsystem.lock.set(LiftSubsystem.NOT_LOCKED);
//    			} else {
//    				Robot.kLiftSubsystem.lock.set(LiftSubsystem.LOCKED);
//    			}
//    		} else {
    			if (Robot.m_oi.functionController.getRawAxis(1) > 0.1 || Robot.m_oi.functionController.getRawAxis(1) < -0.1) {
    	    		Robot.kLiftSubsystem.moveLift(Robot.m_oi.functionController.getRawAxis(1));
    	    	} else {
    	    		Robot.kLiftSubsystem.moveLift(0);
    	    	}
//    		}
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
