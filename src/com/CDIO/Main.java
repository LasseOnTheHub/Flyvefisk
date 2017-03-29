package com.CDIO;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.*;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import java.util.Scanner;

public class Main {


    static IARDrone drone;
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("mat = " + mat.dump());

        Scanner scanner = new Scanner(System.in);

        Main main = new Main();
        main.connectToDrone();
       // TutorialVideoListener VL = new TutorialVideoListener(drone);
        String n = "s";
        while (!n.equals("k"))
        {
            //drone.takeOff();
            n = scanner.nextLine();
        }
        //drone.getCommandManager().waitFor(5000);
        //drone.landing();
        drone.stop();
    }

    private void connectToDrone()
    {
            drone = null;
            try
            {
                drone = new ARDrone();
                drone.start();
            }
            catch (Exception exc)
            {
                exc.printStackTrace();
            }
            finally
            {
                if (drone != null);
                    //drone.stop();

            }

        drone.getNavDataManager().addAttitudeListener(new AttitudeListener() {

            public void attitudeUpdated(float pitch, float roll, float yaw)
            {
                System.out.println("Pitch: " + pitch + " Roll: " + roll + " Yaw: " + yaw);
            }

            public void attitudeUpdated(float pitch, float roll) { }
            public void windCompensation(float pitch, float roll) { }
        });

        drone.getNavDataManager().addBatteryListener(new BatteryListener() {

            public void batteryLevelChanged(int percentage)
            {
                System.out.println("Battery: " + percentage + " %");
            }

            public void voltageChanged(int vbat_raw) { }
        });
        drone.getNavDataManager().addAcceleroListener(new AcceleroListener() {
            @Override
            public void receivedRawData(AcceleroRawData acceleroRawData) {
                System.out.println(acceleroRawData.getRawAccs().toString());
            }

            @Override
            public void receivedPhysData(AcceleroPhysData acceleroPhysData) {

            }
        });
    }
}
