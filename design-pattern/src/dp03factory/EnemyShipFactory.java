/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp03factory;

/**
 *
 * @author jason
 */
public class EnemyShipFactory {
    public EnemyShip makeEnemyShip(String newShipType){
            switch(newShipType){
        case "U":
            return new UFOEnemyShip();
            //break;
        case "R":
            return new RocketEnemyShip();
            //break;
        case "B":
            return new BigUFOEnemyShip();
        default:
            return null;
    }
    }
    
}
