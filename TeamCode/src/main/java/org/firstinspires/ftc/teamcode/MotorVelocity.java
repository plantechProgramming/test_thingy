package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MotorVelocity {
    private static final ElapsedTime timer = new ElapsedTime();
    private long prevEncoder = 0;
    private double prevTime = 0;
    private double prevVelocity = 0;
    private final int MILLISECONDS_TO_MINUTE = 60000;
    int ticksPerRevolution = 8192;

    public double getRawVelocity(DcMotorEx motor) {
        long curEncoder = motor.getCurrentPosition();
        double curTime = timer.milliseconds();

        double timeDiff = curTime - prevTime;
        long encoderDiff = curEncoder - prevEncoder;

        double tickVelocity = encoderDiff / timeDiff; // ticks/milliseconds
        double velocity = (tickVelocity * MILLISECONDS_TO_MINUTE) / ticksPerRevolution;

        prevTime = curTime;

        if(encoderDiff == 0){
            return prevVelocity;
        }

        prevEncoder = curEncoder;
        prevVelocity = velocity;
        return velocity;
    }
}
