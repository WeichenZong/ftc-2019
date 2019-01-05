package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="wheelTest")
public class wheelTest extends auto1 {
    @Override
    public void runOpMode() {
        super.runOpMode();
        super.leftDistance(0.5,70);
    }
}
