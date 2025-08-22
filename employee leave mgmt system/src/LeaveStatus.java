public enum LeaveStatus {
    PENDING("Pending Approval"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    CANCELLED("Cancelled");
    
    private final String displayName;
    
    LeaveStatus(String displayName) {
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