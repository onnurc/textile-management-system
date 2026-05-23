package com.tekstil.textile_management_system.enums;

public enum ModelStatus {
    IN_DESIGN("In Design", "Model design and aesthetics are being developed"),
    TECHNICAL_DESIGN("Technical Design", "Technical patterns and size charts are being developed"),
    IN_PRODUCTION("In Production", "Mass production sewing is in progress"),
    IN_CUTTING("In Cutting", "Fabric is being cut for mass production"),
    MATERIAL_PREPARATION("Material Prep", "Materials and fabrics are being gathered"),
    SERIAL_PRODUCTION("Serial Production", "Mass production sewing is in progress"),
    IN_WAREHOUSE("In Warehouse", "Products are stored in warehouse"),
    COMPLETED("Completed", "The model/order has been delivered"),
    CANCELLED("Cancelled", "The model or order has been terminated");

    private final String displayName;
    private final String description;

    ModelStatus(String displayName, String descripton) {
        this.displayName = displayName;
        this.description = descripton;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
