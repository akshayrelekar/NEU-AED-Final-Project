/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.entities.enums;

/**
 *
 * @author Ninad Subhedar (NUID : 001472377)
 */
public enum AddressType {
    HOME("HOME"), WORK("WORK");

    private final String type;

    private AddressType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
