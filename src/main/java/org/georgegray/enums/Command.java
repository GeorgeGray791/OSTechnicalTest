package org.georgegray.enums;

public enum Command {
    N,S,E,W,G,D;

    public static boolean contains(String s){
        for (Command c : Command.values()) {
            if (c.name().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
