package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleOpIntakeCommand extends Command {
	final double INTAKE_SPEED = 0.3;
	boolean lastPress;
    public TeleOpIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kIntakeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kIntakeSubsystem.pivotReset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.m_oi.functionController.getRawButton(3)) {
    		Robot.kIntakeSubsystem.move(INTAKE_SPEED);
    	} else if (Robot.m_oi.functionController.getRawButton(4)) {
    		Robot.kIntakeSubsystem.move(-INTAKE_SPEED);
    	} else {
    		Robot.kIntakeSubsystem.move(0);
    	}
    	boolean thisPress = Robot.m_oi.functionController.getRawButton(2);
    	if(thisPress != lastPress) {
    		lastPress = thisPress;
    		if(lastPress) {
    			Robot.kIntakeSubsystem.toggleClamp();
    		}
    	}
    SmartDashboard.putNumber("Photo Sensor Value", Robot.kIntakeSubsystem.photoSwitch.getVoltage());
    SmartDashboard.putNumber("Pivot Encoder Value", Robot.kIntakeSubsystem.getPivotPosition());
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
