package ThreadHandler;

import GUI.MainWindow;
import GUI.controllers.LabFiveController;
import GUI.controllers.LabFourController;
import GUI.controllers.LabSixController;
import GUI.controllers.LabThreeController;
import lab1.AppRoot1;
import lab2.AppRoot2;
import lab3.AppRoot3;
import lab4.AppRoot4;
import lab5.AppRoot5;
import lab6.AppRoot6;

public class ThreadHandler {
    private static Thread appRootThread;
    private static AppRoot appRoot;

    public static void startFirstScene(){
        kill();
        appRootThread = new Thread(appRoot = new AppRoot1());
        appRootThread.start();
        System.err.print("AppRoot1 started on ");
        System.err.println(appRootThread.toString());
    }

    public static void startSecondScene(){
        kill();
        appRootThread = new Thread(appRoot = new AppRoot2());
        appRootThread.start();
        System.err.print("AppRoot2 started on ");
        System.err.println(appRootThread.toString());
    }

    public static void startThirdScene(LabThreeController c){
        kill();
        appRootThread = new Thread(appRoot = new AppRoot3(c));
        appRootThread.start();
        System.err.print("AppRoot3 started on ");
        System.err.println(appRootThread.toString());
    }

    public static void startFourthScene(LabFourController c){
        kill();
        appRootThread = new Thread(appRoot = new AppRoot4(c));
        appRootThread.start();
        System.err.print("AppRoot4 started on ");
        System.err.println(appRootThread.toString());
    }

    public static void startFifthScene(LabFiveController c){
        kill();
        appRootThread = new Thread(appRoot = new AppRoot5(c));
        appRootThread.start();
        System.err.print("AppRoot5 started on ");
        System.err.println(appRootThread.toString());
    }

    public static void startSixthScene(LabSixController c){
        kill();
        appRootThread = new Thread(appRoot = new AppRoot6(c));
        appRootThread.start();
        System.err.print("AppRoot6 started on ");
        System.err.println(appRootThread.toString());
    }

    public static void kill(){
        if (appRootThread != null){
            //appRootThread.interrupt();
            appRoot.stop();
            //

        }
    }
}
