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
			addParallel(new AutoDriveCommand(158, 0));
			// addSequential(new WaitCommand(), 0.1);
			// addSequential(new AutoPivotCommand(),2);
			addSequential(new AutoPivotCommand(), 1.75);
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SWITCH_HEIGHT));
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoTurnCommand(-90),4);
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoDriveCommand(8, -90));
			// addSequential(new AutoDriveCommand(15,0));
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoOuttakeCommand(),1);
		} else if (choice == RightChoices.RightToScale) {
			// go to scale
			addParallel(new AutoDriveCommand(334,0));
			addSequential(new AutoPivotCommand(),1.75);
			//originally 0.5 wait
      		addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SCALE_HEIGHT));
      		addSequential(new WaitCommand(), 0.2);
      		addSequential(new AutoTurnCommand(-35));
      		addSequential(new WaitCommand(), 0.1);
      		addSequential(new AutoOuttakeCommand(),1.25);
		} else if (choice == RightChoices.RightBetween) {
			// park between the switch and scale
			addSequential(new AutoDriveCommand(185, 0));
//			addSequential(new WaitCommand(), 0.2);
//			addParallel(new AutoPivotCommand(), 1.75);
//			addSequential(new AutoTurnCommand(-90), 3);
//			addSequential(new WaitCommand(), 0.2);
//			addParallel(new AutoLiftCommand(15));
//			addSequential(new AutoDriveCommand(170, -90));
//			addSequential(new WaitCommand(), 0.2);
//			addSequential(new AutoTurnCommand(0),3.9);
//			addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SCALE_HEIGHT)); //If we have time, do it, else, just sit there.
			// Put right auto code here
		}
    }	  
    	}
	