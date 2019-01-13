package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;

@Autonomous(name="LeftCorner")
public class leftCorner extends auto1  {
    @Override
    public void runOpMode()  {
        super.runOpMode();
        super.goUp(-0.6);
        super.backwardDistance(0.5,5);
        super.leftDistance(0.5,25);
        super.pushYellow();

    }
}