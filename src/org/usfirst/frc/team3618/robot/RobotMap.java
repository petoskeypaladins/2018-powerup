/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3618.robot;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(1);
	public static final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(2);
	public static final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(3);
	public static final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(4);
	public static final SpeedControllerGroup left = new SpeedControllerGroup(leftMotor1,leftMotor2); 
	public static final SpeedControllerGroup right = new SpeedControllerGroup(rightMotor1,rightMotor2);
	public static final DifferentialDrive driveTrain = new DifferentialDrive(left,right);
	
	}
