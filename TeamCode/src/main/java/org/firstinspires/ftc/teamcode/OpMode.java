package org.firstinspires.ftc.teamcode;

import android.provider.Settings;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.bylazar.panels.Panels;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;

public abstract class OpMode extends LinearOpMode {


    public DcMotorEx FL,BL,FR,BR;
    public GoBildaPinpointDriver pinpoint;
    void initialize() {
        FL = hardwareMap.get(DcMotorEx.class, "FL");
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL = hardwareMap.get(DcMotorEx.class, "BL");
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR = hardwareMap.get(DcMotorEx.class, "FR");
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR = hardwareMap.get(DcMotorEx.class, "BR");
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.resetPosAndIMU();
        pinpoint.update();
    }


    @Override
    public void runOpMode() throws InterruptedException {
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
