package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightAutonomousCommand extends CommandGroup {
	public enum RightChoices {
		RightToScale,RightToSwitch,RightBetween
	}
    public RightAutonomousCommand(RightChoices choice) {
    	if (choice == RightChoices.RightToSwitch) {
			System.out.println("Started Left");
			Robot.kDriveSubsystem.resetRobotAngle();
			// addSequential(new AutoDriveCommand(inches,degreees);
			// addSequential(new AutoTurnCommand(degrees);
			// addSequential(new AutoLiftCommand(height in inches);
			// addSequential(new AutoOuttakeCommand(), 1);
			// addSequential(new WaitCommand(), time in seconds);
			// addSequential(new AutoPivotCommand(), 2);
			// addSequential(new AutoOuttakeCommand(), 1);
			addParallel(new AutoDriveCommand(170, 0));
			// addSequential(new WaitCommand(), 0.1);
			// addSequential(new AutoPivotCommand(),2);
			addSequential(new AutoPivotCommand(), 1.75);
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SWITCH_HEIGHT));
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoTurnCommand(-90));
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoDriveCommand(15, -90));
			// addSequential(new AutoDriveCommand(15,0));
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoDropCommand());
		} else if (choice == RightChoices.RightToScale) {
			// go to scale
			addParallel(new AutoDriveCommand(232,0));
			addSequential(new AutoPivotCommand(),1.75);
      		addSequential(new WaitCommand(), 0.5);
			addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SCALE_HEIGHT));
      		addSequential(new WaitCommand(), 0.5);
      		addSequential(new AutoTurnCommand(-45));
      		addSequential(new WaitCommand(), 0.1);
      		addSequential(new AutoOuttakeCommand(),2);
		} else if (choice == RightChoices.RightBetween) {
			// park between the switch and scale
			addParallel(new AutoDriveCommand(220, 0));
			// addSequential(new WaitCommand(), 1);
			addSequential(new AutoPivotCommand(), 1.75);
			addSequential(new AutoTurnCommand(-90));
			addSequential(new WaitCommand(), 0.5);
			addSequential(new AutoDriveCommand(80, -90));
			addSequential(new WaitCommand(), 0.5);
			addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SWITCH_HEIGHT));
			// Put right auto code here
		}

    }	  
    	}
	