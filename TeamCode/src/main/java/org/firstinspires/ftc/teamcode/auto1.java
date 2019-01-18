/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;


@Disabled
public class auto1 extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();
    DcMotor leftmotor1 = null;
    DcMotor leftmotor2 = null;
    DcMotor rightmotor1 =null;
    DcMotor rightmotor2 =null;
    DcMotor armlift =null;
    DcMotor armmain =null;
    CRServo armservo1 =  null;
    Servo armservo2 = null;
    //global varible
    int tetrixencoderfactor=1440;
    int andmarkencoderfactor=1120;
    GoldAlignDetector detector;
    double pos;
    double target = 290.0;
    double k;
    boolean isMiddle = false;
    boolean isLeft = false;
    boolean isRight = false;


    public void runOpMode() {
        // define motors
        leftmotor1 = hardwareMap.get(DcMotor.class, "leftmotor1");
        leftmotor2 = hardwareMap.get(DcMotor.class, "leftmotor2");
        rightmotor1 = hardwareMap.get(DcMotor.class, "rightmotor1");
        rightmotor2 = hardwareMap.get(DcMotor.class, "rightmotor2");
        armlift = hardwareMap.get(DcMotor.class, "armlift");
        armmain = hardwareMap.get(DcMotor.class,"armmain");
        armservo1 = hardwareMap.get(CRServo.class,"armservo1");
        armservo2 = hardwareMap.get(Servo.class, "armservo2");

        //reverse motors
        leftmotor2.setDirection(DcMotor.Direction.REVERSE);
        leftmotor1.setDirection(DcMotor.Direction.REVERSE);

        //initialize encoders
        setMotorsRunUsingEncoders();
        armlift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armmain.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //OpenCV detector
        telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");

        // Set up detector
        detector = new GoldAlignDetector(); // Create detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings

        // Optional tuning
        detector.alignSize = 80; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment
        waitForStart();
        detector.enable(); // Start the detector
        telemetry.addData("IsAligned" , detector.getAligned()); // Is the bot aligned with the gold mineral?
        telemetry.addData("X Pos" , detector.getXPosition()); // Gold X position.
        armservo2.setPosition(0);

    }

    public void goStraigt(double power){
        leftmotor1.setPower(power);
        leftmotor2.setPower(power);
        rightmotor1.setPower(power);
        rightmotor2.setPower(power);
    }
    public void rightTurn(double power){
        leftmotor1.setPower(power);
        leftmotor2.setPower(power);
        rightmotor1.setPower(-power);
        rightmotor2.setPower(-power);
    }
    public void leftTurn(double power){
        leftmotor1.setPower(-power);
        leftmotor2.setPower(-power);
        rightmotor1.setPower(power);
        rightmotor2.setPower(power);
    }
    public void leftDrift(double power){
        leftmotor1.setPower(-power);
        rightmotor2.setPower(-power);
        leftmotor2.setPower(power);
        rightmotor1.setPower(power);
    }
    public void rightDrift(double power){
        rightmotor1.setPower(-power);
        leftmotor2.setPower(-power);
        rightmotor2.setPower(power);
        leftmotor1.setPower(power);
    }
    public void stopWheel(){
        goStraigt(0);
    }

    public int cmToWheelRotation(int distance){
        return (int)((distance/(3.1416*15.2))*tetrixencoderfactor);
    }
    public int cmToWheelRotation(double distance){
        return (int)(distance/(3.1416*15.2))*tetrixencoderfactor;
    }
    public int armToArmRotation(double distance){
        return  (int) (((distance*1.5*25)*andmarkencoderfactor));
    }
    public boolean encodersAreBusy(){
        return leftmotor1.isBusy()&&leftmotor2.isBusy()&&rightmotor2.isBusy()&&rightmotor1.isBusy();
    }
    public void YellowPosition(){
        if(!detector.isFound()) {
            isRight = true;}

        else if(detector.getXPosition()<250) {
            isLeft =true;
        }
        else if(detector.getXPosition()>500){
            isRight=true;
        }
        else {
            isMiddle=true;
        }
    }
    public void setMotorsStopAndResetEncoders(){
        leftmotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftmotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightmotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightmotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void setMotorsRunToPosition(){
        leftmotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftmotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightmotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightmotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void setMotorsRunUsingEncoders(){
        leftmotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftmotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightmotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightmotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void forwordDistance(double power,int distance){
        setMotorsStopAndResetEncoders();
        leftmotor1.setTargetPosition(cmToWheelRotation(distance));
        rightmotor1.setTargetPosition(cmToWheelRotation(distance));
        leftmotor2.setTargetPosition(cmToWheelRotation(distance));
        rightmotor2.setTargetPosition(cmToWheelRotation(distance));
        setMotorsRunToPosition();
        goStraigt(power);
        while(encodersAreBusy()){
            idle();
        }
        stopWheel();
        setMotorsRunUsingEncoders();

    }

    public void backwardDistance(double power,int distance){
        setMotorsStopAndResetEncoders();
        leftmotor1.setTargetPosition(cmToWheelRotation(-distance));
        leftmotor2.setTargetPosition(cmToWheelRotation(-distance));
        rightmotor2.setTargetPosition(cmToWheelRotation(-distance));
        rightmotor1.setTargetPosition(cmToWheelRotation(-distance));
        setMotorsRunToPosition();
        goStraigt(-power);
        while(encodersAreBusy()){
            idle();
        }
        stopWheel();
        setMotorsRunUsingEncoders();

    }

    public void leftDistance(double power,int distance){
        setMotorsStopAndResetEncoders();
        leftmotor1.setTargetPosition(cmToWheelRotation(-distance));
        leftmotor2.setTargetPosition(cmToWheelRotation(distance));
        rightmotor2.setTargetPosition(cmToWheelRotation(-distance));
        rightmotor1.setTargetPosition(cmToWheelRotation(distance));
        setMotorsRunToPosition();
        leftDrift(power);
        while(encodersAreBusy()){
            idle();
        }
        stopWheel();
        setMotorsRunUsingEncoders();

    }
    public void rightDistance(double power,int distance){
        setMotorsStopAndResetEncoders();
        leftmotor1.setTargetPosition(cmToWheelRotation(distance));
        leftmotor2.setTargetPosition(cmToWheelRotation(-distance));
        rightmotor2.setTargetPosition(cmToWheelRotation(distance));
        rightmotor1.setTargetPosition(cmToWheelRotation(-distance));
        setMotorsRunToPosition();
        rightDrift(power);
        while(encodersAreBusy()){
            idle();
        }
        stopWheel();
        setMotorsRunUsingEncoders();

    }
    public void leftTurn90(){
        setMotorsStopAndResetEncoders();
        leftmotor1.setTargetPosition(-1600);
        rightmotor1.setTargetPosition(1600);
        leftmotor2.setTargetPosition(-1600);
        rightmotor2.setTargetPosition(1600);
        setMotorsRunToPosition();
        goStraigt(0.3);
        while(encodersAreBusy()){
            idle();
        }
        stopWheel();
        setMotorsRunUsingEncoders();
    }
    public void rightTurn90(){
        setMotorsStopAndResetEncoders();
        leftmotor1.setTargetPosition(1600);
        rightmotor1.setTargetPosition(-1600);
        leftmotor2.setTargetPosition(1600);
        rightmotor2.setTargetPosition(-1600);
        setMotorsRunToPosition();
        goStraigt(0.3);
        while(encodersAreBusy()){
            idle();
        }
        stopWheel();
        setMotorsRunUsingEncoders();
    }
    public void leftTurn45(){
        setMotorsStopAndResetEncoders();
        leftmotor1.setTargetPosition(-750);
        rightmotor1.setTargetPosition(750);
        leftmotor2.setTargetPosition(-750);
        rightmotor2.setTargetPosition(750);
        setMotorsRunToPosition();
        goStraigt(0.3);
        while(encodersAreBusy()){
            idle();
        }
        stopWheel();
        setMotorsRunUsingEncoders();
    }
    public void rightTurn45(){
        setMotorsStopAndResetEncoders();
        leftmotor1.setTargetPosition(750);
        rightmotor1.setTargetPosition(-750);
        leftmotor2.setTargetPosition(750);
        rightmotor2.setTargetPosition(-750);
        setMotorsRunToPosition();
        goStraigt(0.3);
        while(encodersAreBusy()){
            idle();
        }
        stopWheel();
        setMotorsRunUsingEncoders();
    }
    public void pushYellow () {
        setMotorsRunToPosition();
        for (int i = 0;i < 2; ++i){
            if (detector.isFound())
            {
                pos = detector.getXPosition();
                while(!detector.getAligned()){
                    goStraigt((target-pos)/700);
                    idle();
                }
                stopWheel();
                break;
            }
            else{
                forwordDistance(0.5,15);
            }
        }
    }
    public void goUp(double distance){
        armlift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armlift.setTargetPosition(armToArmRotation(distance));
        armlift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armlift.setPower(1);
        while (armlift.isBusy()){
            idle();
        }
        armlift.setPower(0);
        armlift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


}
