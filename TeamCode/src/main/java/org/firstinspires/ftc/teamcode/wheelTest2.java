package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="wheelTest2")
public class wheelTest2 extends auto1 {
    @Override
    public void runOpMode() {
        super.runOpMode();
        super.rightDistance(0.5,50);
    }
}
