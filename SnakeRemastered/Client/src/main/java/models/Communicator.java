/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import interfaces.ICommunicator;

import java.util.Observable;

/**
 * Abstract communicator.
 * A communicator needs to extend Observable and implement ICommunicator.
 * @author Nico Kuijpers
 */
public abstract class Communicator extends Observable implements ICommunicator {
    // Nothing
}
