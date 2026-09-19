package org.firstinspires.ftc.teamcode;

import android.provider.Settings;

import com.bylazar.camerastream.PanelsCameraStream;
import com.bylazar.panels.Panels;
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

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;

public abstract class OpMode extends LinearOpMode {

    public DcMotorEx FL,BL,FR,BR;
    protected IMU Imu;
    public FtcDashboard dashboard;
    public Limelight3A ll;

    void initialize() {
        FL = hardwareMap.get(DcMotorEx.class, "FL");
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL = hardwareMap.get(DcMotorEx.class, "BL");
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR = hardwareMap.get(DcMotorEx.class, "FR");
        BR = hardwareMap.get(DcMotorEx.class, "BR");
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        Imu.initialize(parameters);
        Imu.resetYaw();
        ll = hardwareMap.get(Limelight3A.class, "LimeLight");
        ll.setPollRateHz(100);
        ll.pipelineSwitch(2);
        dashboard = FtcDashboard.getInstance();
        dashboard.startCameraStream(ll, 60);
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
//    public void test(double x){
//        motor.setPower(x);
//        motor2.setPower(-x);
//    }
    protected void postInit() {

    }

    protected abstract void run();

    protected abstract void end();
}
