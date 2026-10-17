package org.firstinspires.ftc.teamcode;


import com.ThermalEquilibrium.homeostasis.Controllers.Feedforward.BasicFeedforward;
import com.ThermalEquilibrium.homeostasis.Parameters.FeedforwardCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.SerialNumber;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Configurable
@Config
public class Shooter {
    Telemetry telemetry;
    DcMotorEx shooter;
    Servo sizeChanger, servo;
    public Shooter(Telemetry telemetry, DcMotorEx shooter, Servo sizeChanger, Servo servo){
        this.telemetry = telemetry;
        this.shooter = shooter;
        this.sizeChanger = sizeChanger;
        this.servo = servo;
    }
    private static final ElapsedTime timer = new ElapsedTime();
    boolean prevMore = false;
    boolean prevLess = false;
    double variablePower = 0;
    public double getVariableShoot(boolean more, boolean less, double jumps){
        if(more && !prevMore){variablePower += jumps;}
        else if(less && !prevLess){
            variablePower -= jumps;
        }
        if (variablePower >= 0.8){
            variablePower = 0.8;
        }
        prevLess = less;
        prevMore = more;
        return variablePower;
    }
    public static double Kv = 0.04;
    public static double Ka = 0.07;
    public static double Ks = 0;
    FeedforwardCoefficients coefficients = new FeedforwardCoefficients(Kv,Ka,Ks);
    BasicFeedforward controller = new BasicFeedforward(coefficients);
    double prevVel = 0;
    double prevTime = 0;
    double flyWheelCir = 0.096;//m
    public void VariableShoot(boolean more, boolean less, double jumps){
        double time = timer.milliseconds();
        double deltaTime = time - prevTime;
        double rpm = getVariableShoot(more, less, jumps)*6000;
        telemetry.addData("wanted rpm", rpm);
        double vel = rpm*flyWheelCir*Math.PI/60;
        double accel = (vel - prevVel)/deltaTime;
        double pow = controller.calculate(0, vel, accel);
        telemetry.addData("pow", pow);
        shooter.setPower(pow);
        prevVel = vel;
        prevTime = time;
    }
//    public void turret(double pow){
//        servo.setPower(pow);
//    }
    public void swichToPollen(){
        sizeChanger.setPosition(0);
    }

    public void swichToNectar(){
        sizeChanger.setPosition(0.5);
    }
}
