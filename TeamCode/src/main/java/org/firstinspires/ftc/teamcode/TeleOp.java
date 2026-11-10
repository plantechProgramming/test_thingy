package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.concurrent.TimeUnit;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode{
    @Override
    protected void run() {
        while (opModeIsActive()){
            FL.setPower(gamepad1.left_stick_y);
            FR.setPower(gamepad1.left_stick_y);
            BL.setPower(gamepad1.left_stick_y);
            BR.setPower(gamepad1.left_stick_y);
            if (gamepad1.a){
                FL.setPower(1);
                BR.setPower(-1);
            }
            else if (gamepad1.b){
                FL.setPower(-1);
                BR.setPower(1);
            }
            else {
                FL.setPower(0);
                BR.setPower(0);
            }
        }
    }

    @Override
    protected void end() {

    }
}
