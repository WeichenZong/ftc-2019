package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.io.IOException;

@Autonomous(name="LeftCorner")
public class leftCorner extends auto1  {
    @Override
    public void runOpMode()  {
        super.runOpMode();
        goUp(-0.6);
        idle();
        backwardDistance(0.5,10);
        idle();
        leftDistance(0.5,5);
        idle();
        forwordDistance(0.5,10);
        idle();
        YellowPosition();
        for(int i=0;i<2 ; i++){

            if (isLeft) {
                leftDistance(0.5,30);
                backwardDistance(0.5,40);
                leftDistance(0.5,30);
                rightDistance(0.5,25);
                break;
            } else if (isMiddle) {
                backwardDistance(0.5,5);
                idle();
                leftDistance(0.5,55);
                forwordDistance(1,5);
                rightDistance(0.5,25);
                break;
            } else if (isRight||!detector.isFound()) {
                leftDistance(0.5,40);
                forwordDistance(0.5,40);
                leftDistance(0.5,30);
                rightDistance(0.5,25);
                break;
            }
        }

    }
}