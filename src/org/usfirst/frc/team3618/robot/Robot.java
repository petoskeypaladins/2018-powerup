/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3618.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3618.robot.commands.AutoDriveCommand;
import org.usfirst.frc.team3618.robot.commands.AutoLeftScaleCommand;
import org.usfirst.frc.team3618.robot.commands.AutoLiftCommand;
import org.usfirst.frc.team3618.robot.commands.AutoTurnCommand;
import org.usfirst.frc.team3618.robot.commands.CenterAutonomousCommand;
import org.usfirst.frc.team3618.robot.commands.LeftAutonomousCommand;
import org.usfirst.frc.team3618.robot.commands.RightAutonomousCommand;
import org.usfirst.frc.team3618.robot.commands.pivotTester;
import org.usfirst.frc.team3618.robot.commands.testLiftsSequence;
import org.usfirst.frc.team3618.robot.subsystems.ClimbSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.LightsSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.PivotSubsystem;


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
	public static final PivotSubsystem kPivotSubsystem
			= new PivotSubsystem();
	public static OI m_oi;

	Command m_autonomousCommand;
	SendableChooser<Integer> m_chooser = new SendableChooser<>();
	SendableChooser<Integer> priority_chooser = new SendableChooser<>();
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("Autonomous Left", 1);
		m_chooser.addObject("Autonomous Center", 2);
		m_chooser.addObject("Autonomous Right", 3);
		priority_chooser.addDefault("Switch", 1);
		priority_chooser.addObject("Scale", 2);
		
//		m_chooser.addObject("Autonomous Left Scale", 4);
//		m_chooser.addObject("Lift Test", 5);
//		m_chooser.addObject("Baseline Auto", 5);
		// chooser.addObject("My Auto", new MyAutoCommand());
		
		SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putData("Priority chooser", priority_chooser);
		CameraServer.getInstance().startAutomaticCapture();
		
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
		// new AUTO COMMAND THAT SIMPLY CHOOSES WHICH Left|Right|CenterAutomous runs
		  // new AUTO COMMAND THAT SIMPLY CHOOSES WHICH Left|Right|Center Auto
	    //m_autonomousCommand = m_chooser.getSelected();
	    // this will construct the commands after Auton starts
	    
	    Integer positionChoice = m_chooser.getSelected();
	    Integer priorityChoice = priority_chooser.getSelected();
	    String gameMsg = DriverStation.getInstance().getGameSpecificMessage();
	    
	    if (gameMsg != null && gameMsg.length() >0){
	    	char switchLoc = gameMsg.charAt(0);
	    	char scaleLoc = gameMsg.charAt(1);
	    	
	    	switch(positionChoice) { 
		      case 1: 
		    	  if (switchLoc == 'L' && scaleLoc == 'R'){
		    		  // TODO add left1 auto
		    	  }
		    	  if (switchLoc == 'L' && scaleLoc == 'L'){
		    		  if (priorityChoice == 1){
		    			  //TODO add left3 auto
		    			 
		    		  }
		    		  if (priorityChoice == 2){
		    			  //TODO add left2 auto
		    		  }
		    	  }
		              break;
		      case 2: 
		    	  if(switchLoc == 'L'){
		    	  //TODO add leftcenter auto
		    	  }
		    	  if(switchLoc == 'R'){
		    		  //TODO add rightcenter auto
		    	  }
		              break; 
		      case 3:
		    	  if (switchLoc == 'R' && scaleLoc == 'L'){
		    		  // TODO add right1 auto
		    	  }
		    	  if (switchLoc == 'R' && scaleLoc == 'R'){
		    		  if (priorityChoice == 1){
		    			  //TODO add right3 auto
		    			 
		    		  }
		    		  if (priorityChoice == 2){
		    			  //TODO add right2 auto
		    		  }
		    	  }
		              break; 
		     
		      default: // what to do if you don't choose 1, 2 or 3
		              break; 
		    }

	    }
	   	kPivotSubsystem.pivotReset();
	    

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
	   	kPivotSubsystem.pivotReset();
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
