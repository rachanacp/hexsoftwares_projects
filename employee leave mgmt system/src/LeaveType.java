public enum LeaveType {
    ANNUAL("Annual Leave"),
    SICK("Sick Leave"),
    CASUAL("Casual Leave"),
    MATERNITY("Maternity Leave"),
    PATERNITY("Paternity Leave"),
    EMERGENCY("Emergency Leave");
    
    private final String displayName;
    
    LeaveType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}