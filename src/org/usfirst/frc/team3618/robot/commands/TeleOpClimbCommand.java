package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleOpClimbCommand extends Command {
	boolean lastPress6;
	boolean lastPress5;
	int timer = 0;

    public TeleOpClimbCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kClimbSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(Robot.m_oi.functionController.getRawButton(1)) {
//    		if (timer > 10)
//    			Robot.kClimbSubsystem.move(0.35);
//    		timer++;
//    		Robot.kLiftSubsystem.unlock();
//    	} else {
//    		timer = 0;
//    		Robot.kClimbSubsystem.move(0);
//    		Robot.kLiftSubsystem.lock();
//    	}
//    	boolean thisPress6 = Robot.m_oi.functionController.getRawButton(6);
//    	if(thisPress6 != lastPress6) {
//    		lastPress6 = thisPress6;
//    		if(lastPress6 && Robot.kLiftSubsystem.isLiftDown()) {
//    			Robot.kClimbSubsystem.latch();
//    		}
//    	}
//    	boolean thisPress5 = Robot.m_oi.functionController.getRawButton(5);
//    	if(thisPress5 != lastPress5) {
//    		lastPress5 = thisPress5;
//    		if(lastPress5) {
//    			Robot.kClimbSubsystem.unlatch();
//    		}
//    	}
//    	SmartDashboard.putNumber("Servo Angle", ClimbSubsystem.latchServo.getAngle());
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
