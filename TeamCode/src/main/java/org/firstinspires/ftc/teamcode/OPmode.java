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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="OPmode")
public class OPmode extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();
    DcMotor leftmotor1 = null;
    DcMotor leftmotor2 = null;
    DcMotor rightmotor1 =null;
    DcMotor rightmotor2 =null;
    DcMotor armlift = null;
    DcMotor armmain = null;
   CRServo armservo1 = null;
    Servo armservo2 = null;



    public void runOpMode() {

        // define motors
        leftmotor1 = hardwareMap.get(DcMotor.class, "leftmotor1");
        leftmotor2 = hardwareMap.get(DcMotor.class, "leftmotor2");
        rightmotor1 = hardwareMap.get(DcMotor.class, "rightmotor1");
        rightmotor2 = hardwareMap.get(DcMotor.class, "rightmotor2");
       // armlift = hardwareMap.get(DcMotor.class, "armlift");
        //armmain = hardwareMap.get(DcMotor.class,"armmain");
        armservo1 = hardwareMap.get(CRServo.class,"armservo1");
        //armservo2 = hardwareMap.get(Servo.class, "armservo2");

        //reverse motors
        leftmotor2.setDirection(DcMotor.Direction.REVERSE);
        leftmotor1.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        //initialize encoders
        while (opModeIsActive()) {
            armservo1.resetDeviceConfigurationForOpMode();
            if (gamepad1.x) {
                armservo1.setPower(-1);
                armservo1.close();
            }
            else if (gamepad1.y){
                    armservo1.setPower(1);
                    armservo1.close();
            }
            else{
               //armservo1.setPower(0.0);
                armservo1.close();
            }
            armservo1.close();
            leftmotor1.setPower(0.5*(-gamepad1.left_stick_y+gamepad1.right_stick_x+gamepad1.left_stick_x));
            leftmotor2.setPower(0.5*(-gamepad1.left_stick_y+gamepad1.right_stick_x-gamepad1.left_stick_x));
            rightmotor1.setPower(0.5*(-gamepad1.left_stick_y-gamepad1.right_stick_x-gamepad1.left_stick_x));
            rightmotor2.setPower(0.5*(-gamepad1.left_stick_y-gamepad1.right_stick_x+gamepad1.left_stick_x));
            idle();
        }
    }
}
