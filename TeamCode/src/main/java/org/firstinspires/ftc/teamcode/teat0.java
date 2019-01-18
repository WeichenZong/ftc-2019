package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="test0")
public class teat0 extends auto1  {
    @Override
    public void runOpMode()  {
        super.runOpMode();
        super.goUp(-0.6);
        idle();
        super.backwardDistance(0.5,10);

    }
}