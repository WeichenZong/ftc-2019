package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="RightCorner")
public class rightCorner extends auto1 {
    @Override
    public void runOpMode() {
        super.runOpMode();goUp(-0.6);
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
                backwardDistance(0.5,45);
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
                leftDistance(0.5,30);
                forwordDistance(0.5,30);
                leftDistance(0.5,30);
                rightDistance(0.5,25);
                break;
            }
        }
    }
}