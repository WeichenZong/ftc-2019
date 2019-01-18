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

            if (isLeft) {
                leftDistance(0.5,30);
                backwardDistance(0.5,40);
                leftDistance(0.5,30);
                rightDistance(0.5,25);
                backwardDistance(0.8,60);
                leftTurn45();
                backwardDistance(0.5,65);
                armservo2.setPosition(5);
                idle();
            } else if (isMiddle||detector.getAligned()) {
                backwardDistance(0.5,10);
                idle();
                leftDistance(0.5,55);
                idle();
                rightDistance(0.5,25);
                backwardDistance(0.8,100);
                idle();
                leftTurn45();
                backwardDistance(0.5,65);
                armservo2.setPosition(5);
                idle();
            } else if (isRight||!detector.isFound()) {
                leftDistance(0.5,30);
                forwordDistance(0.5,40);
                leftDistance(0.5,30);
                rightDistance(0.5,25);
                backwardDistance(0.8,140);
                leftTurn45();
                backwardDistance(0.5,65);
                armservo2.setPosition(5);
                idle();
            }

    }
}