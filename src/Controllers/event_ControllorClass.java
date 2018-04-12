/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entity.Concour;
import Entity.Conseils;

/**
 *
 * @author wael
 */
public interface event_ControllorClass {
    public abstract void preloadData(Concour c);
    public abstract void preloadData(Conseils c);
    
}
