package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "DriverIdea2")
public class DriverIdea2 extends LinearOpMode{
    @Override
    public void runOpMode() {

        DcMotor frontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor backLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor backRight = hardwareMap.dcMotor.get("motorBackRight");
        DcMotor scissor = hardwareMap.dcMotor.get("scissor");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;
            boolean a = gamepad1.a;
            boolean b = gamepad1.b;

            if (a == true) {
                scissor.setPower(1.0);
            } else if (b == true) {
                scissor.setPower(-1.0);
            } else {
                scissor.setPower(0);
            }

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            double frontLeftPower;
            double backLeftPower;
            double frontRightPower;
            double backRightPower;

            if (rx > 0) {
                frontLeftPower = rx;
                backLeftPower = rx;
                frontRightPower = -rx;
                backRightPower = -rx;
            } else if (rx < 0) {
                frontLeftPower = rx;
                backLeftPower = rx;
                frontRightPower = -rx;
                backRightPower = -rx;
            } else {
                frontLeftPower = (y + x + rx) / denominator;
                backLeftPower = (y - x + rx) / denominator;
                frontRightPower = (y - x - rx) / denominator;
                backRightPower = (y + x - rx) / denominator;
            }

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);
        }
    }
}