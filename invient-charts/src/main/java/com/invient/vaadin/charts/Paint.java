package com.invient.vaadin.charts;

import java.io.Serializable;

/**
 * This Paint interface defines how color patterns can be generated 
 * when drawing the InvientCharts. 
 * 
 * @author Invient
 * 
 */
public interface Paint extends Serializable {
    
    
    /**
     * Returns String representation of an object of type Paint.
     * 
     * @return Returns String representation of an object of type Paint.
     */
    public String getString();
    
}
