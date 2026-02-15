package com.tekstil.textile_management_system.enums;

public enum ModelStatus {
    IN_DESIGN("In Design", "Model design and aesthetics are being developed"),
    TECHNICAL_DESIGN("Technical Design", "Technical patterns and size charts are being developed"),
    SAMPLE_PRODUCTION("Sample Production", "The first prototype/sample is being sewn"),
    AWAITING_APPROVAL("Awaiting Approval", "Waiting for owner or customer feedback"),
    CUTTING_PREPARATION("Cutting Prep", "Materials and fabrics are being gathered"),
    IN_CUTTING("In Cutting", "Fabric is being cut for mass production"),
    IN_PRODUCTION("In Production", "Mass production sewing is in progress"),
    QUALITY_CONTROL("Quality Control", "Final products are being inspected"),
    REWORK("Rework", "Defective items are being fixed"),
    PACKAGING("Packaging", "Final folding and packing is in progress"),
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
