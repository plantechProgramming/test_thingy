package org.firstinspires.ftc.teamcode;

public class Shooter {
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
}
