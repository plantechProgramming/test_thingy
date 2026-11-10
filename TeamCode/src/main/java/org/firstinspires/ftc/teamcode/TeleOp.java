package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.concurrent.TimeUnit;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode{
    @Override
    protected void run() {
        DriveTrain driveTrain = new DriveTrain(BR, BL, FR, FL, telemetry, pinpoint);
        while (opModeIsActive()){
            telemetry.addData("pos", sizeChanger.getPosition());
            telemetry.update();
        }
    }

    @Override
    protected void end() {

    }
}
