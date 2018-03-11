package org.usfirst.frc.team3618.robot.commands;


import org.usfirst.frc.team3618.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAutonomousCommand extends CommandGroup {
	public enum CenterChoices{
		CenterLeft,CenterRight
	}
    public CenterAutonomousCommand(CenterChoices choice) {

   		addParallel(new AutoSDriveCommand(142,(choice != CenterChoices.CenterLeft)));
		addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SWITCH_HEIGHT));
		addSequential(new AutoPivotCommand(),2.10);
		addSequential(new AutoIsSDriveCompleted());
		addSequential(new AutoOuttakeCommand(),2);
    }
}
