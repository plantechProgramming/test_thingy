package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.concurrent.TimeUnit;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode{
    double gamepadForward; //-1 to 1
    double gamepadTurn;
    double gamepadDrift;
    double botHeading;

    @Override
    protected void run() {
        DriveTrain driveTrain = new DriveTrain(BR, BL, FR, FL, telemetry, Imu);
        MotorVelocity motorVelocity = new MotorVelocity();
        while (opModeIsActive()){
            gamepadForward = -gamepad1.left_stick_y;
            gamepadTurn = gamepad1.right_stick_x;
            gamepadDrift = gamepad1.left_stick_x;
            botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

//            if(gamepad1.a){
//                driveTrain.forward(0.3);
////                driveTrain.turnToAngle(50);
//            }
//            else{
                driveTrain.drive(gamepadForward, gamepadDrift, gamepadTurn, botHeading);
//            }
            telemetry.update();
        }
    }

    @Override
    protected void end() {

    }
}
