/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3618.robot.subsystems;

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
	static final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_1);
	static final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_2);
	static final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_1);
	static final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_2);
	static final SpeedControllerGroup left = new SpeedControllerGroup(leftMotor1,leftMotor2); 
	static final SpeedControllerGroup right = new SpeedControllerGroup(rightMotor1,rightMotor2);
	static final DifferentialDrive driveTrain = new DifferentialDrive(left,right);
	static final Solenoid leftDriveSolenoid = new Solenoid(0);
	static final Solenoid rightDriveSolenoid = new Solenoid(2);
	static final ADIS16448_IMU gyro = new ADIS16448_IMU();
	static final Compressor compressor = new Compressor();
	int leftValue = 0;
	int rightValue = 0;
	static final double TURN_CLIP = 0.1;

	double lastSpeed = 0;
	static final double ACCEL_RATE = 0.1; // amount of power change per cycle
	
	public DriveSubsystem() {
		gyro.calibrate();
		resetRobotAngle();
		leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
		rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
		encoderReset();
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
	
    double accelRamp(double speed) {
    	int direction = 0;
    	double difference = speed - lastSpeed;
    	if (difference != 0) {
    		direction = (int) (difference / Math.abs(difference)); //1 or -1
    	}
    
    	double newSpeed = lastSpeed + (ACCEL_RATE * direction); // apply the limited amount of change this scan
    	if (Math.abs(difference) < ACCEL_RATE) {
    		newSpeed = speed; // don't overshoot the requested speed
    	}
    	lastSpeed = newSpeed;
    	return(newSpeed);
    }
	public void driveStraightGyro(double speed,double angle) {
		double Kp = 0.15;
		double turn = Kp*(getRobotAngle()-angle); 
		if (turn > TURN_CLIP)
			turn = TURN_CLIP;
		else if (turn < -TURN_CLIP)
			turn = -TURN_CLIP;
		driveTrain.arcadeDrive(accelRamp(speed),turn);
	}
	public void drive(double speed,double angle) {
		driveTrain.arcadeDrive(accelRamp(speed), angle);
	}


	public void stop() {
		driveTrain.arcadeDrive(0, 0);
		lastSpeed = 0;
	}
	public void turn(double rate) {
		driveTrain.arcadeDrive(0, rate);
	}
	public void encoderReset() {
		leftValue = leftMotor1.getSensorCollection().getPulseWidthPosition();
		rightValue = rightMotor1.getSensorCollection().getPulseWidthPosition();
	}

	public boolean getIsInLowGear() {
		return !leftDriveSolenoid.get();
	}
	public void shifToHighGear(boolean highGear) {
		// true is High gear, false is Low gear
		leftDriveSolenoid.set(highGear);
		rightDriveSolenoid.set(highGear);
		
	}
}


