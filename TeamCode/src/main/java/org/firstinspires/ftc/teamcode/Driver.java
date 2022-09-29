package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Driver")
public class Driver extends LinearOpMode{
    @Override
    public void runOpMode() {

        DcMotor frontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor backLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor backRight = hardwareMap.dcMotor.get("motorBackRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            if (rx == 0 || (y == 0 && x == 0)) {

                double frontLeftPower = (y + x + rx) / denominator;
                double backLeftPower = (y - x + rx) / denominator;
                double frontRightPower = (y - x - rx) / denominator;
                double backRightPower = (y + x - rx) / denominator;

                frontLeft.setPower(frontLeftPower);
                backLeft.setPower(backLeftPower);
                frontRight.setPower(frontRightPower);
                backRight.setPower(backRightPower);
            } else if (x / 1.1 < 0.1 && x / 1.1 > -0.1 && y > 0.1 && rx > 0) {
                double frontLeftPower = 1;
                double backLeftPower = 1;
                double frontRightPower = -0.5;
                double backRightPower = -0.5;
            } else if (x / 1.1 < 0.1 && x / 1.1 > -0.1 && y > 0.1 && rx < 0) {
                double frontLeftPower = -0.5;
                double backLeftPower = -0.5;
                double frontRightPower = 1;
                double backRightPower = 1;
            } else if (x / 1.1 < 0.1 && x / 1.1 > -0.1 && y < 0.1 && rx > 0) {
                double frontLeftPower = 0.5;
                double backLeftPower = 0.5;
                double frontRightPower = -1;
                double backRightPower = -1;
            } else if (x / 1.1 < 0.1 && x / 1.1 > -0.1 && y < 0.1 && rx < 0) {
                double frontLeftPower = -1;
                double backLeftPower = -1;
                double frontRightPower = 0.5;
                double backRightPower = 0.5;
            }
        }
    }
}