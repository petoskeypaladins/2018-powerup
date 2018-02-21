/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3618.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3618.robot.commands.AutoDriveCommand;
import org.usfirst.frc.team3618.robot.commands.AutoLiftCommand;
import org.usfirst.frc.team3618.robot.commands.AutoTurnCommand;
import org.usfirst.frc.team3618.robot.commands.testAuto;
import org.usfirst.frc.team3618.robot.commands.testLiftsSequence;
import org.usfirst.frc.team3618.robot.subsystems.ClimbSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.LightsSubsystem;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
@SuppressWarnings("unused")
public class Robot extends TimedRobot {
	public static final DriveSubsystem kDriveSubsystem
			= new DriveSubsystem();
	public static final LightsSubsystem kLightsSubsystem
			= new LightsSubsystem();
	public static final LiftSubsystem kLiftSubsystem
			= new LiftSubsystem();
	public static final IntakeSubsystem kIntakeSubsystem
			= new IntakeSubsystem();
	public static final ClimbSubsystem kClimbSubsystem
			= new ClimbSubsystem();
	public static OI m_oi;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("Lift Test Sequence", new testLiftsSequence());
		m_chooser.addObject("Auto turn test", new AutoTurnCommand(-90));
		m_chooser.addObject("Test Sequence", new testAuto());
		m_chooser.addObject("Lift Test Auto", new AutoLiftCommand(40));
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		//CameraServer.getInstance().startAutomaticCapture();
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("left encoder", Robot.kDriveSubsystem.getLeftCounts());
		SmartDashboard.putNumber("right encoder", Robot.kDriveSubsystem.getRightCounts());
		SmartDashboard.putNumber("Gyro Angle", Robot.kDriveSubsystem.getRobotAngle());
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("left encoder", Robot.kDriveSubsystem.getLeftCounts());
		SmartDashboard.putNumber("right encoder", Robot.kDriveSubsystem.getRightCounts());
		SmartDashboard.putNumber("Gyro Angle", Robot.kDriveSubsystem.getRobotAngle());
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
