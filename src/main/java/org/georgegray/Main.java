package org.georgegray;

import org.georgegray.entity.Vec2i;
import org.georgegray.exceptions.RobotException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws RobotException {
        Scanner scanner = new Scanner(System.in);

        RoboControl roboControl = new RoboControl(new Vec2i(9,9), new Vec2i(0,0), new Vec2i(4,4), new Vec2i(9,9));

        while(true){
            System.out.println("Current State: ");
            System.out.println(roboControl.toString());
            System.out.println("Enter Command String: ");
            String command = scanner.nextLine();
            try {
                roboControl.processCommands(command);
            } catch (RobotException e){
                System.out.printf("Something Went wrong: \"%s\"%n", e.getMessage());
            }
        }
    }

}
