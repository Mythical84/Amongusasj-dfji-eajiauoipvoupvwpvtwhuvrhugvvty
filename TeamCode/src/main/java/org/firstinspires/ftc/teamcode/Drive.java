package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Drive")
public class Drive extends LinearOpMode{
    @Override
    public void runOpMode() {

        DcMotor frontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor backLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor backRight = hardwareMap.dcMotor.get("motorBackRight");
        DcMotor scissor = hardwareMap.dcMotor.get("scissor");
        DcMotor beanlift = hardwareMap.dcMotor.get("beanlift");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            if (gamepad1.a) {
                scissor.setPower(1.0);
                beanlift.setPower(-1.0);
            } else if (gamepad1.b) {
                scissor.setPower(-1.0);
                beanlift.setPower(1.0);
            } else {
                scissor.setPower(0);
                beanlift.setPower(0);
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