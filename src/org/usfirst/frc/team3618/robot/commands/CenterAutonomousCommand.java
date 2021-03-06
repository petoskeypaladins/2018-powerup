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

   		addParallel(new AutoSDriveCommand(125,(choice != CenterChoices.CenterLeft)),10);
		addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SWITCH_HEIGHT));
		addSequential(new AutoPivotCommand(),1.75);
		addSequential(new AutoIsSDriveCompleted());
		//originally 0.5 wait
		addSequential(new WaitCommand(),0.1);
		addSequential(new AutoOuttakeCommand(),1.25);
		addSequential(new WaitCommand(),0.1);
		addSequential(new AutoDriveCommand(-36,0));
		if(CenterChoices.CenterLeft == choice) addSequential(new AutoTurnCommand(45),2);
		else addSequential(new AutoTurnCommand(-45),2);
		addParallel(new AutoLiftCommand(LiftSubsystem.LIFT_BOTTOM_HEIGHT));
		addParallel(new AutoDropCommand());
		if(CenterChoices.CenterLeft == choice) addSequential(new AutoDriveCommand(12,45));
		else addSequential(new AutoDriveCommand(12,-45));
//		addSequential(new AutoDropCommand()); //Hooray for bad naming!
    }
}
