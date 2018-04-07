/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3618.robot;

//import org.usfirst.frc.team3618.robot.commands.AutoLiftCommand;
//import org.usfirst.frc.team3618.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.buttons.Button;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
public final XboxController driveController = new XboxController(0);
public final Joystick functionController = new Joystick(1);

//private Button setToBottomButton = new JoystickButton(functionController, 7);
//private Button setToSwitchButton = new JoystickButton(functionController, 9);
//private Button setToScaleButton = new JoystickButton(functionController, 11);
//
//public OI() {
//	setToBottomButton.whenPressed(new AutoLiftCommand(LiftSubsystem.LIFT_BOTTOM_HEIGHT));
//	setToSwitchButton.whenPressed(new AutoLiftCommand(LiftSubsystem.LIFT_SWITCH_HEIGHT));
//	setToScaleButton.whenPressed(new AutoLiftCommand(LiftSubsystem.LIFT_SCALE_HEIGHT));
//	}
}
