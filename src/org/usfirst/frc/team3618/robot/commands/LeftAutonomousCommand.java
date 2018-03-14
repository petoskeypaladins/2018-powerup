package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftAutonomousCommand extends CommandGroup {
	public enum LeftChoices {LeftToScale, LeftToSwitch, LeftBetween};
	
    public LeftAutonomousCommand(LeftChoices choice) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	if(choice == LeftChoices.LeftToSwitch) {
			System.out.println("Started Left");
			Robot.kDriveSubsystem.resetRobotAngle();
			//addSequential(new AutoDriveCommand(inches,degreees);
			//addSequential(new AutoTurnCommand(degrees);
			//addSequential(new AutoLiftCommand(height in inches);
			//addSequential(new AutoOuttakeCommand(), 1);
			//addSequential(new WaitCommand(), time in seconds);
			//addSequential(new AutoPivotCommand(), 2);
			//addSequential(new AutoOuttakeCommand(), 1);
			addParallel(new AutoDriveCommand(170,0));
//			addSequential(new WaitCommand(), 0.1);
//			addSequential(new AutoPivotCommand(),2);
			addSequential(new AutoPivotCommand(),1.75);
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SWITCH_HEIGHT));
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoTurnCommand(90));
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoDriveCommand(15,90));
//			addSequential(new AutoDriveCommand(15,0));
			addSequential(new WaitCommand(), 0.1);
			addSequential(new AutoOuttakeCommand(), 1);
		  } else if (choice == LeftChoices.LeftToScale) {
			// go to scale
			addParallel(new AutoDriveCommand(231,0));
			addSequential(new AutoPivotCommand(),1.75);
      		addSequential(new WaitCommand(), 0.5);
			addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SCALE_HEIGHT));
      		addSequential(new WaitCommand(), 0.5);
      		addSequential(new AutoTurnCommand(45),5);
      		addSequential(new WaitCommand(), 0.1);
      		addSequential(new AutoOuttakeCommand(),2);
		  } else if (choice == LeftChoices.LeftBetween) {
			// park between the switch and scale
			addParallel(new AutoDriveCommand(220,0));
//			addSequential(new WaitCommand(), 1);
			addSequential(new AutoPivotCommand(),1.75);
			addSequential(new AutoTurnCommand(90));
			addSequential(new WaitCommand(), 0.5);
			addSequential(new AutoDriveCommand(80, 90));
			addSequential(new WaitCommand(), 0.5);
			addSequential(new AutoLiftCommand (LiftSubsystem.LIFT_SWITCH_HEIGHT));
			//Put right auto code here
		  	}
        }
    }

