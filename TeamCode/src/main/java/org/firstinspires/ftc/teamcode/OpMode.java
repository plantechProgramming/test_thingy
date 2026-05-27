package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public abstract class OpMode extends LinearOpMode {
    protected DcMotorEx FL, FR, BL, BR;

    protected IMU Imu;

    void initialize() {

        FL = hardwareMap.get(DcMotorEx.class, "FL");
        FL.setDirection(DcMotorEx.Direction.REVERSE);
        FL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        FL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        FR = hardwareMap.get(DcMotorEx.class, "FR");
        FR.setDirection(DcMotorEx.Direction.REVERSE);
        FR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        FR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER );

        BL = hardwareMap.get(DcMotorEx.class, "BL");
        BL.setDirection(DcMotorEx.Direction.REVERSE);
        BL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        BR = hardwareMap.get(DcMotorEx.class, "BR");
        BR.setDirection(DcMotorEx.Direction.FORWARD);
        BR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        Imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        Imu.initialize(parameters);
        Imu.resetYaw();
    }


    @Override
    public void runOpMode() throws InterruptedException  {
        initialize();
        waitForStart();
        postInit();

        if (opModeIsActive()) {
            run();
        }

        end();
    }

    protected void postInit() {

    }
    protected abstract void run();

    protected abstract void end();
}
