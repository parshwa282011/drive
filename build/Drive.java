package com.github.parshwa282011.drive.build;


import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.Arrays;

public class Drive implements DriveInterface {
    private  Telemetry telemetry;
    private DcMotor FrontLeftMotor;
    private DcMotor FrontRightMotor;
    private DcMotor BackLeftMotor;
    private DcMotor BackRightMotor;
    private DriveBuilder builder = new DriveBuilder();
    @Override
    public void move(double froward, double strafe, double rotate, double speed) {
        switch (builder.getDriverMode()){
            case Kiwi:
                telemetry.addLine("not Implemented Yet");
                break;
            case HDrive:
                telemetry.addLine("not Implemented Yet");
                break;
            case Swerve:
                telemetry.addLine("not Implemented Yet");
                break;
            case SixWheel:
                telemetry.addLine("not Implemented Yet");
                break;
            case TwoWheel:
                telemetry.addLine("not Implemented Yet");
                break;
            case Holonomic:
                telemetry.addLine("not Implemented Yet");
                break;
            case FourWheel:
                telemetry.addLine("not Implemented Yet");
                break;
            case OneEachSideTank:
                telemetry.addLine("not Implemented Yet");
                break;
            case TwoEachSideTank:
                telemetry.addLine("not Implemented Yet");
                break;
            case MecanumFeildOriented:
                FeildOreintedMode(froward, strafe, rotate, speed);
                telemetry.addLine("Mechanum FeildOreintedDrive is running");
                break;
            case MecanumRobotOriented:
                RobotOrientedMode(froward, strafe, rotate, speed);
                telemetry.addLine("Mechanum RobotOreintedDrive is running");
                break;
            default:
                telemetry.addLine("ERROR INVALID DRIVE TYPE");
                telemetry.addLine("Send me a discord message at parshwa_fun if this is a drive mode type");
                break;
        }
        telemetry.update();
    }
    private void FeildOreintedMode(double forward, double strafe, double rotate, double speed){
        IMU imu = builder.getImu();
        double robotAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        robotAngle = AngleUnit.normalizeRadians(robotAngle);
        // convert to polar
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(forward, strafe);
        // rotate angle
        theta = AngleUnit.normalizeRadians(theta - robotAngle);

        // convert back to cartesian
        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);
        RobotOrientedMode(newForward,newStrafe,rotate,speed);
    }
    private void RobotOrientedMode(double forward, double strafe, double rotate, double speed){
        double leftFrontPower, rightFrontPower, leftBackPower, rightBackPower;

        leftFrontPower = (forward + strafe + rotate) * speed;
        rightFrontPower= (forward - strafe - rotate) * speed;
        leftBackPower  = (forward - strafe + rotate) * speed;
        rightBackPower = (forward + strafe - rotate) * speed;

        FrontRightMotor.setPower(rightFrontPower);
        BackRightMotor.setPower(rightBackPower);
        FrontLeftMotor.setPower(leftFrontPower);
        BackLeftMotor.setPower(leftBackPower);
    }
    private void FeildOreintedMode(double forward, double strafe, double rotate){
        double speed = builder.getSpeed();
        FeildOreintedMode(forward,strafe,rotate,speed);
    }
    private void RobotOrientedMode(double forward, double strafe, double rotate){
        double speed = builder.getSpeed();
        RobotOrientedMode(forward,strafe,rotate,speed);
    }

    @Override
    public void move(double froward, double strafe, double rotate) {
        switch (builder.getDriverMode()){
            case Kiwi:
                telemetry.addLine("not Implemented Yet");
                break;
            case HDrive:
                telemetry.addLine("not Implemented Yet");
                break;
            case Swerve:
                telemetry.addLine("not Implemented Yet");
                break;
            case SixWheel:
                telemetry.addLine("not Implemented Yet");
                break;
            case TwoWheel:
                telemetry.addLine("not Implemented Yet");
                break;
            case Holonomic:
                telemetry.addLine("not Implemented Yet");
                break;
            case FourWheel:
                telemetry.addLine("not Implemented Yet");
                break;
            case OneEachSideTank:
                telemetry.addLine("not Implemented Yet");
                break;
            case TwoEachSideTank:
                telemetry.addLine("not Implemented Yet");
                break;
            case MecanumFeildOriented:
                FeildOreintedMode(froward, strafe, rotate);
                telemetry.addLine("Mechanum FeildOreintedDrive is initilizing");
                break;
            case MecanumRobotOriented:
                RobotOrientedMode(froward, strafe, rotate);
                telemetry.addLine("Mechanum RobotOreintedDrive is initilizing");
                break;
            default:
                telemetry.addLine("ERROR INVALID DRIVE TYPE");
                telemetry.addLine("Send me a discord message at parshwa_fun if this is a drive mode type");
                break;
        }
        telemetry.update();
    }

    @Override
    public void init(HardwareMap hardwareMap, Telemetry telemetry, DriveModes drivermode) {
        change(drivermode);
        init(hardwareMap,telemetry);
    }

    @Override
    public void init(@NonNull HardwareMap hardwareMap, Telemetry telemetry) {
        FrontLeftMotor  = hardwareMap.dcMotor.get(builder.getLeftFront());
        FrontRightMotor = hardwareMap.dcMotor.get(builder.getRightFront());
        BackLeftMotor   = hardwareMap.dcMotor.get(builder.getLeftBack());
        BackRightMotor  = hardwareMap.dcMotor.get(builder.getRightBack());

        FrontLeftMotor.setDirection(builder.getMotorOrientationLeftFront());
        FrontRightMotor.setDirection(builder.getMotorOrientationRightFront());
        BackLeftMotor.setDirection(builder.getMotorOrientationLeftBack());
        BackRightMotor.setDirection(builder.getMotorOrientationRightBack());
        this.telemetry = telemetry;
        telemetry.addLine("Done initilizing drive");
        telemetry.update();
    }

    public void change(double speed){
        builder.speed(speed);
    }
    public void change(String imu){
        builder.imuName(imu);
    }
    public void change(IMU imu){
        builder.imu(imu);
    }
    public void change(String rightFront, String rightBack, String leftFront, String leftBack){
        builder.motors(rightFront,rightBack,leftFront,leftBack);
    }
    public void change(DriveModes mode){
        builder.mode(mode);
    }
    public void change(DcMotorSimple.Direction MotorOrientationRightFront, DcMotorSimple.Direction MotorOrientationRightBack, DcMotorSimple.Direction MotorOrientationLeftFront, DcMotorSimple.Direction MotorOrientationLeftBack){
        builder.oreintaion(MotorOrientationRightFront,MotorOrientationRightBack,MotorOrientationLeftFront,MotorOrientationLeftBack);
    }
}
