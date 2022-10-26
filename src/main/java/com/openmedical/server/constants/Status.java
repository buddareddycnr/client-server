package com.openmedical.server.constants;

public enum Status {
    ACTIVE("Active"),PENDING("Pending"),CREATED("Created"),INACTIVE("Inactive"),RESTRICTED("restricted");
    private String status;
    private Status(String status){
        this.status = status;
    }
    public static Status getStatus(String status){
        return Status.valueOf(status);
    }

    public String getStatus() {
        return status;
    }
}
