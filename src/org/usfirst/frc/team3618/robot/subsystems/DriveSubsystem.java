/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.TeleOpDriveCommand;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
	public static final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_1);
	public static final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_2);
	public static final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_1);
	public static final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_2);
	static final SpeedControllerGroup left = new SpeedControllerGroup(leftMotor1,leftMotor2); 
	static final SpeedControllerGroup right = new SpeedControllerGroup(rightMotor1,rightMotor2);
	public static final DifferentialDrive driveTrain = new DifferentialDrive(left,right);
	public static final Solenoid leftDriveSolenoid = new Solenoid(0);
	public static final Solenoid rightDriveSolenoid = new Solenoid(2);
	static final ADIS16448_IMU gyro = new ADIS16448_IMU();
	public static final Compressor compressor = new Compressor();
	int leftValue = 0;
	int rightValue = 0;
	static final double TURN_CLIP = 0.1;
	
	public DriveSubsystem() {
		leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
		rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
		encoderReset();
//		liftHangMotor.getLimitSwitchState();
//		liftHangMotor.isFwdLimitSwitchClosed();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new TeleOpDriveCommand());
	}
	
	public int getLeftCounts() {
		return leftValue - leftMotor1.getSensorCollection().getPulseWidthPosition();
	}
	
	public int getRightCounts() {
		return rightMotor1.getSensorCollection().getPulseWidthPosition() - rightValue;
	}
	
	public double getRobotAngle() {
		return gyro.getAngleZ();
	}
	
	public void resetRobotAngle() {
		gyro.reset();
	}
	
	public void driveStraightGyro(double speed) {
		double Kp = 0.15;
		double turn = Kp*getRobotAngle(); 
		if (turn > TURN_CLIP)
			turn = TURN_CLIP;
		else if (turn < -TURN_CLIP)
			turn = -TURN_CLIP;
		driveTrain.arcadeDrive(speed,turn);
	}
	public void stop() {
		driveTrain.arcadeDrive(0, 0);
	}
	public void turn(double rate) {
		driveTrain.arcadeDrive(0, rate);
	}
	public void encoderReset() {
		leftValue = leftMotor1.getSensorCollection().getPulseWidthPosition();
		rightValue = rightMotor1.getSensorCollection().getPulseWidthPosition();
		
	}
}


