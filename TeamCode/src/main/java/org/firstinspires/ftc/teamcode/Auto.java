package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="Autonomous")
public class Auto extends LinearOpMode{
    @Override
    public void runOpMode() {

        DcMotor frontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        DcMotor backLeft = hardwareMap.dcMotor.get("motorBackLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("motorFrontRight");
        DcMotor backRight = hardwareMap.dcMotor.get("motorBackRight");
        DcMotor scissor = hardwareMap.dcMotor.get("scissor");
        DcMotor beanlift = hardwareMap.dcMotor.get("beanlift");
        DistanceSensor distance = hardwareMap.get(DistanceSensor.class, "distance");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("distance (yards)", distance.getDistance(DistanceUnit.INCH)/36);
            telemetry.update();
        }
    }
}