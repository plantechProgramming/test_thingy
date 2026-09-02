package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode{
    @Override
    protected void run() {
        Imu.resetYaw();
        DriveTrain driveTrain = new DriveTrain(BR, BL, FR, FL);
        double forward, turn, drift, botHeading;
        while (opModeIsActive()){
            forward = -gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;
            drift = -gamepad1.left_stick_x;
            botHeading = Math.toRadians(Imu.getRobotYawPitchRollAngles().getYaw());
            if(gamepad1.a){
                elevator.setTargetPosition(3000);
                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setVelocity(elevator.getMotorType().getAchieveableMaxTicksPerSecond());
            }
            else if(gamepad1.b){
                elevator.setTargetPosition(0);
                elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.setVelocity(elevator.getMotorType().getAchieveableMaxTicksPerSecond());
            }
            else{
                elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                elevator.setPower(0);
            }
//            if(forward!=0){
//                driveTrain.forward(0.1);
//            }
//            driveTrain.drive(forward, drift, turn,-botHeading);
            telemetry.addData("imu", botHeading);
            telemetry.addData("elevator pos", elevator.getCurrentPosition());
            telemetry.addData("elevator pow", elevator.getPower());
            telemetry.update();
        }
    }

    @Override
    protected void end() {

    }
}
