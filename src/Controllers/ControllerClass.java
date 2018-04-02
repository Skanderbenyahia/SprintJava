/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Categorie;
import Entity.CentreDressage;

/**
 *
 * @author jabou
 */
public interface ControllerClass {
    public abstract void preloadData(CentreDressage d);
    public abstract void preloadData(Categorie c);
}
