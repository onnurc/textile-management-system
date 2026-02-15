package com.tekstil.textile_management_system.enums;

/**
 * Represents the different organizational roles within the textile
 * and garment production workflow.
 */
public enum Role {
    COMPANY_MANAGER("Owner", "Company owner with full administrative access"),
    STYLIST("Stylist/Designer", "Responsible for visual design and aesthetics"),
    MODELIST("Pattern Maker", "Creates technical drawings and measurement charts"),
    OPERATOR("Machine Operator", "Performs mass production sewing"),
    CUTTER("Cutter", "Responsible for fabric cutting operations"),
    TRIM_SPECIALIST("Trim Specialist", "Responsible for buttons, zippers, labels, and all garment accessories"),
    ATOLYE_MANAGER("Atöyle Chef", "Coordinates the mass production and sewing line"),
    PACKAGING_SPECIALIST("Packaging Specialist", "Handles final packing and folding");

    private final String displayName;
    private final String description;

    Role(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}