package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class DriveTrain {
    private DcMotorEx BR, BL, FR, FL;

    public DriveTrain(DcMotorEx BR, DcMotorEx BL, DcMotorEx FR, DcMotorEx FL) {
        this.BL = BL;
        this.BR = BR;
        this.FL = FL;
        this.FR = FR;
    }

    public void drive(double y, double x, double rx, double botHeading, double slowRatio){

        // slowRatio [0,1] - output power multiplier

        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX *= 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;

        double frontRightPower = (rotY - rotX - rx) / denominator;// before - rotX
        double backRightPower = (rotY + rotX - rx) / denominator;// before + rotX

        FL.setPower(frontLeftPower * slowRatio);
        BL.setPower(backLeftPower * slowRatio);

        FR.setPower(frontRightPower * slowRatio);
        BR.setPower(backRightPower * slowRatio);

    }

    public void forward(double pow){
        FL.setPower(pow);
        BL.setPower(pow);

        FR.setPower(pow);
        BR.setPower(pow);
    }
}
