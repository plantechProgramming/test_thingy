package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode{
    double gamepadForward; //-1 to 1
    double gamepadTurn;
    double gamepadDrift;
    double botHeading;

    @Override
    protected void run() {
        DriveTrain driveTrain = new DriveTrain(BR, BL, FR, FL, telemetry, Imu);
        while (opModeIsActive()){
            gamepadForward = -gamepad1.left_stick_y;
            gamepadTurn = gamepad1.right_stick_x;
            gamepadDrift = gamepad1.left_stick_x;
            botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            if(gamepad1.a){
                driveTrain.turnToAngle(50);
            }
            else{
                driveTrain.drive(gamepadForward, gamepadDrift, gamepadTurn, botHeading);
            }
            telemetry.update();
        }
    }

    @Override
    protected void end() {

    }
}
