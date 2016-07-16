/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp03factory;

import java.util.Scanner;

/**
 *
 * @author jason
 */
public class AppCtr {

    public static void main(String[] args) {

        EnemyShipFactory shipFactory = new EnemyShipFactory();

        EnemyShip theEnemy = null;

        Scanner userInput = new Scanner(System.in);
        String enemyShipOption = "";
        System.out.println(" what type of ship: U/R/B");
        if (userInput.hasNextLine()) {
            enemyShipOption = userInput.nextLine();
        }
        theEnemy = shipFactory.makeEnemyShip(enemyShipOption);

        if (theEnemy != null) {
            doStuffEnemy(theEnemy);
        } else {
            System.out.println("=======null===");
        }

    }

    private static void doStuffEnemy(EnemyShip enemyShip) {
        enemyShip.displayEnemyShip();
        enemyShip.followHeroShip();
        enemyShip.enemyShipShoots();
    }

}
