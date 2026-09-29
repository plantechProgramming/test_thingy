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
        Shooter shooter = new Shooter(telemetry, shooterMotor, sizeChanger);
        DriveTrain driveTrain = new DriveTrain(BR, BL, FR, FL, telemetry, Imu);
        MotorVelocity motorVelocity = new MotorVelocity();
        boolean swichedToPollen = false;
        while (opModeIsActive()){
            gamepadForward = -gamepad1.left_stick_y;
            gamepadTurn = gamepad1.right_stick_x;
            gamepadDrift = gamepad1.left_stick_x;
            botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            driveTrain.drive(gamepadForward, gamepadDrift, gamepadTurn, botHeading);
            shooter.VariableShoot(gamepad1.dpad_up, gamepad1.dpad_down, 0.01);
            telemetry.addData("curr rpm", motorVelocity.getRawVelocity(shooterMotor));
            telemetry.addData("pos", sizeChanger.getPosition());
            if(gamepad1.a){
//                if(swichedToPollen){
//                    shooter.swichToNectar();
//                    swichedToPollen = false;
//                }
//                else{
//                    shooter.swichToPollen();
//                    swichedToPollen = true;
//                }
                sizeChanger.setPosition(1);
            } else if (gamepad1.b) {
                sizeChanger.setPosition(-1);
            }

            telemetry.update();
        }
    }

    @Override
    protected void end() {

    }
}
