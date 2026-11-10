package org.firstinspires.ftc.teamcode;

import android.provider.ContactsContract;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.AngleController;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
@Configurable
public class DriveTrain {
    Telemetry telemetry;
    GoBildaPinpointDriver pinpoint;
    private DcMotorEx BR, BL, FR, FL;
    public static double Kd, Ki, Kp = 0;

    public DriveTrain(DcMotorEx BR, DcMotorEx BL, DcMotorEx FR, DcMotorEx FL, Telemetry telemetry, GoBildaPinpointDriver pinpoint) {
        this.BL = BL;
        this.BR = BR;
        this.FL = FL;
        this.FR = FR;
        this.telemetry = telemetry;
        this.pinpoint = pinpoint;
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

    PIDCoefficients coefficients = new PIDCoefficients(Kp,Ki,Kd);
    BasicPID controller = new BasicPID(coefficients);
    public void moveDistanceForward(double dis){
        double output = controller.calculate(pinpoint.getPosX(DistanceUnit.CM)+dis, pinpoint.getPosX(DistanceUnit.CM));
        FL.setPower(output);
        BL.setPower(output);

        FR.setPower(output);
        BR.setPower(output);

    }
}
