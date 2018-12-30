package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="blueLeftCorner")
public class leftCorner extends auto1 {
    @Override
    public void runOpMode() {
        super.runOpMode();
        super.leftDrift(1);
    }
}