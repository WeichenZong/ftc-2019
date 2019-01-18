package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="RightCorner")
public class rightCorner extends auto1 {
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
            rightTurn45();
            forwordDistance(1,100);
        } else if (isMiddle||detector.getAligned()) {
            backwardDistance(0.5,10);
            idle();
            leftDistance(0.5,55);
            idle();
            rightDistance(0.5,25);
            backwardDistance(0.8,120);
            rightTurn45();
            forwordDistance(1,100);
        } else if (isRight||!detector.isFound()) {
            leftDistance(0.5,30);
            forwordDistance(0.5,40);
            leftDistance(0.5,30);
            rightDistance(0.5,25);
            backwardDistance(0.8,140);
            rightTurn45();
            forwordDistance(1,100);
        }

    }
}