package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="test1")
public class test1 extends auto1  {
    @Override
    public void runOpMode()  {
        super.runOpMode();
        super.goUp(0.6);

    }
}