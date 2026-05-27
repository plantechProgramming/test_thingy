package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode{
    @Override
    protected void run() {
        DriveTrain driveTrain = new DriveTrain(BR, BL, FR, FL);
        double forward, turn, drift, botHeading;
        while (opModeIsActive()){
            forward = -gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;
            drift = gamepad1.left_stick_x;
            botHeading = Imu.getRobotYawPitchRollAngles().getYaw();
            if(forward!=0){
                driveTrain.forward(0.1);
            }
//            driveTrain.drive(forward, drift, turn, botHeading, 1);
        }
    }

    @Override
    protected void end() {

    }
}
