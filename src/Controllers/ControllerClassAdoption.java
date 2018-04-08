/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Animal;
import Entity.Refuge;

/**
 *
 * @author jabou
 */
public interface ControllerClassAdoption {
    public abstract void preloadData(Animal a);
    public abstract void preloadData(Refuge r);
}
