// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

/** 
 * Contract for an object that can display and/or log information for an Arm subsystem.
 * 
 * An Arm subsystem may use zero to many IArmDisplays.
 * 
 * Examples:
 * 
 * One IArmDisplay may provide extensive debugging information.
 * One IArmDisplay may contribute information to a "competition" display that has less information and not just arm information.\
 * One Arm may use zero displays in case of unit tests.
 */
public interface IArmDisplay {

    public void initializeDisplay(Arm arm);

}
