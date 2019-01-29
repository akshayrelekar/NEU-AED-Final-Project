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
public enum ContactType {

    HOME("HOME"), WORK("WORK"), MOBILE("MOBILE");

    private final String type;

    private ContactType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
