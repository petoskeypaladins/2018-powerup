/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3618.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.LiftSubsystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class TeleOpDriveCommand extends Command {
	static final double MAX_SPEED_WHEN_LIFT_UP = 0.3; // all the way up, limit to this speed

	public TeleOpDriveCommand() {
		
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kDriveSubsystem);
	}
	double originalValue;
	boolean lastPress;
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}


	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// read Robot.kLiftSubsystem.getCurrentLiftHeight() and reduce speed to prevent tipping over
		double speed = -Robot.m_oi.driveController.getY(Hand.kLeft); // -1.0 to 1.0 [full reverse to full forward]
		double inchesFromTop = LiftSubsystem.LIFT_SCALE_HEIGHT - Robot.kLiftSubsystem.getCurrentLiftHeight(); // 75 to 0 [full down to full up]
		double percentageFromTop = inchesFromTop / (LiftSubsystem.LIFT_SCALE_HEIGHT - LiftSubsystem.SECOND_STAGE_HEIGHT); // 2.0 to 0 [full down to full up, 1.0 is SECOND_STAGE_HEIGHT]
		double powerFromTop = percentageFromTop * (1.0 - MAX_SPEED_WHEN_LIFT_UP) + MAX_SPEED_WHEN_LIFT_UP; // 1.4 to 0.3 [full down to full up, 1.0 is SECOND_STAGE_HEIGHT]
		speed = speed * (Math.min(1.0, powerFromTop)); // reduce speed when lift is above SECOND_STAGE_HEIGHT

		Robot.kDriveSubsystem.drive(speed, Robot.m_oi.driveController.getX(Hand.kRight));

//		if (Robot.m_oi.driveController.getBumperPressed(Hand.kRight)) {
//			Robot.kDriveSubsystem.shifToHighGear(Robot.kDriveSubsystem.getIsInLowGear());
//		}
		boolean thisPress = Robot.m_oi.driveController.getRawButton(6);
    	if(thisPress != lastPress) {
    		lastPress = thisPress;
    		if(lastPress) {
    			Robot.kDriveSubsystem.shifToHighGear(Robot.kDriveSubsystem.getIsInLowGear());
    		}
    	}

		if(Robot.m_oi.driveController.getBButtonPressed()) {
			Robot.kDriveSubsystem.encoderReset();
			Robot.kDriveSubsystem.resetRobotAngle();
		}
		SmartDashboard.putBoolean("In low gear",Robot.kDriveSubsystem.getIsInLowGear());
	}
	
	

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
