package com.github.parshwa282011.drive.build;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface DriveInterface {
    void move(double froward, double strafe, double rotate, double speed);
    void move(double froward, double strafe, double rotate);
    void init(HardwareMap hardwareMap, Telemetry telemetry, DriveModes drivermode);
    void init(HardwareMap hardwareMap, Telemetry telemetry);
}
