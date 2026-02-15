package com.tekstil.textile_management_system.enums;

public enum Priority {
    LOW("Low",1),
    NORMAL("Normal",2),
    HIGH("High",3),
    URGENT("Urgent",4),
    CRITICAL("Critical",5);


    private final String displayName;
    private final int level;

    Priority(String displayName,int level){
        this.displayName = displayName;
        this.level = level;
    }
    public String getDisplayName(){
        return displayName;
    }
    public int getLevel(){
        return level;
    }







}
