/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3618.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * An example command.  You can replace me with your own command.
 */
public class TeleOpCommand extends Command {
	public TeleOpCommand() {
		
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kDriveSubsystem);
	}
	double originalValue;
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	DriveSubsystem.driveTrain.arcadeDrive(-Robot.m_oi.driveController.getY(Hand.kLeft), Robot.m_oi.driveController.getX(Hand.kRight));
	if (Robot.m_oi.driveController.getBumperPressed(Hand.kRight) == true) {
		if (DriveSubsystem.driveSolenoid.get() == true) {
			DriveSubsystem.driveSolenoid.set(false);
		} else {
			DriveSubsystem.driveSolenoid.set(true);
			
		}
	}
	
	if(Robot.m_oi.driveController.getBButtonPressed()) {
		Robot.kDriveSubsystem.encoderReset();
		Robot.kDriveSubsystem.resetRobotAngle();
	}
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
