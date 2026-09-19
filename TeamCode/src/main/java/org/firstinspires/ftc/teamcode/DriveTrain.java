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

    public void drive(double y, double x, double rx, double botHeading){

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX *= 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double frontLeftPower = (rotY + rotX + rx);
        double backLeftPower = (rotY - rotX + rx);

        double frontRightPower = (rotY - rotX - rx);// before - rotX
        double backRightPower = (rotY + rotX - rx);// before + rotX

        FL.setPower(frontLeftPower);
        BL.setPower(backLeftPower);

        FR.setPower(frontRightPower);
        BR.setPower(backRightPower);

    }

    public void forward(double pow){
        FL.setPower(pow);
        BL.setPower(pow);

        FR.setPower(pow);
        BR.setPower(pow);
    }

    public void turnToAngle(double deg){

//        FL.setPower(pow);
//        BL.setPower(pow);
//
//        FR.setPower(-pow);
//        BR.setPower(-pow);
    }
}
