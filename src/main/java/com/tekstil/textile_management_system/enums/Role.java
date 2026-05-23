package com.tekstil.textile_management_system.enums;

/**
 * Represents the different organizational roles within the textile
 * and garment production workflow.
 */
public enum Role {
    COMPANY_MANAGER("Owner", "Company owner with full administrative access", null),
    STYLIST("Stylist/Designer", "Responsible for visual design and aesthetics", "MODELIST"),
    MODELIST("Pattern Maker", "Creates technical drawings and measurement charts", "OPERATOR"),
    OPERATOR("Machine Operator", "Performs mass production sewing", "CUTTER"),
    CUTTER("Cutter", "Responsible for fabric cutting operations", "TRIM_SPECIALIST"),
    TRIM_SPECIALIST("Trim Specialist", "Responsible for buttons, zippers, labels...", "FASON"),
    FASON("Fason", "Coordinates the mass production and sewing line", "PACKAGING_SPECIALIST"),
    PACKAGING_SPECIALIST("Packaging Specialist", "Handles final packing and folding", null),
    PENDING("Pending", "Awaiting role assignment", null);

    private final String displayName;
    private final String description;
    private final String nextRole;

    Role(String displayName, String description, String nextRole) {
        this.displayName = displayName;
        this.description = description;
        this.nextRole = nextRole;
    }

    public boolean hasNextRole() {
        return nextRole != null;
    }

    public Role getNextRole() {
        if (nextRole == null) throw new IllegalStateException("there is no role for the next stage");
        return Role.valueOf(nextRole);
    }
}