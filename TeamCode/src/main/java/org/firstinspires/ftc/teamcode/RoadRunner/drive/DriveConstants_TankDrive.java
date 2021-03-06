package org.firstinspires.ftc.teamcode.RoadRunner.drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

/*
 * Constants shared between multiple drive types.
 *
 * TODO: Tune or adjust the following constants to fit your robot. Note that the non-final
 * fields may also be edited through the dashboard (connect to the robot's WiFi network and
 * navigate to https://192.168.49.1:8080/dash). Make sure to save the values here after you
 * adjust them in the dashboard; **config variable changes don't persist between app restarts**.
 *
 * These are not the only parameters; some are located in the localizer classes, drive base classes,
 * and op modes themselves.
 */
@Config
public class DriveConstants_TankDrive {
    //TODO: Si vas a usar un TankDrive, en los OpModes de prueba, cambia lo que diga
    // SampleMecanumDrive por SampleTankDrive
    // \(￣︶￣*\))
    /*
     * These are motor constants that should be listed online for your motors.
     */
    public static final double TICKS_PER_REV = 560;
    public static final double MAX_RPM = 300;//TODO: Con relacion 20:1
    /** Neta chequen bien esto si no, no va a jalar bien*/
    /*
     * Set RUN_USING_ENCODER to true to enable built-in hub velocity control using drive encoders.
     * Set this flag to false if drive encoders are not present and an alternative localization
     * method is in use (e.g., tracking wheels).
     *
     * If using the built-in motor velocity PID, update MOTOR_VELO_PID with the tuned coefficients
     * from DriveVelocityPIDTuner.
     */

    public static final boolean RUN_USING_ENCODER = true;//TODO: no le juegen al vivo y le pongan false
    public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(0, 0, 0,
            getMotorVelocityF(MAX_RPM / 60 * TICKS_PER_REV));
    /**Aqui no importa nada, busca siempre que llegue bien y recorra bien la distancia especificada*/
    /*
     * These are physical constants that can be determined from your robot (including the track
     * width; it will be tune empirically later although a rough estimate is important). Users are
     * free to chose whichever linear distance unit they would like so long as it is consistently
     * used. The default values were selected with inches in mind. Road runner uses radians for
     * angular distances although most angular parameters are wrapped in Math.toRadians() for
     * convenience. Make sure to exclude any gear ratio included in MOTOR_CONFIG from GEAR_RATIO.
     */
    public static double WHEEL_RADIUS = 1.7715; // in
    public static double GEAR_RATIO =  1.085;
    /**En el straight Test, porbar hasta que la distancia dada en el simulador
     * concuerde con la recorrida en fisico
     */
    //1 *(66/60.85);
    // TODO: output (wheel) speed / input (motor) speed
    //  en base a los valores obtenidos continuar con el tuneo hasta que el
    //  error sea 0


    public static double TRACK_WIDTH = 15; // in
    /**En el Turn Test que sea muy preciso el giro, con eso*/
    /*
     * These are the feedforward parameters used to model the drive motor behavior. If you are using
     * the built-in velocity PID, *these values are fine as is*. However, if you do not have drive
     * motor encoders or have elected not to use them for velocity control, these values should be
     * empirically tuned.
     */
    public static double kV = 1.0 / rpmToVelocity(MAX_RPM);
    public static double kA = 0;                //TODO: no tocar ( •̀ ω •́ )✧
    public static double kStatic = 0;

    /*
     * These values are used to generate the trajectories for you robot. To ensure proper operation,
     * the constraints should never exceed ~80% of the robot's actual capabilities. While Road
     * Runner is designed to enable faster autonomous motion, it is a good idea for testing to start
     * small and gradually increase them later after everything is working. All distance units are
     * inches.
     */

    public static double MAX_VEL = 30;
    public static double MAX_ACCEL =30;
    public static double MAX_ANG_VEL = Math.toRadians(60);
    public static double MAX_ANG_ACCEL = Math.toRadians(60);
    /**Estas constantes dejarlas asi tal cual estan, ningun cambio es requerido,
     *  mientras que sea una relacion 20:1
     */

    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    public static double rpmToVelocity(double rpm) {
        return rpm * GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS / 60.0;
    }

    public static double getMotorVelocityF(double ticksPerSecond) {
        // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
        return 32767 / ticksPerSecond;
    }
}
