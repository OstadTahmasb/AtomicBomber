package atomic.bomber.controller;

public class ControllerResponse {
    private String error;
    private boolean isFail;
    public ControllerResponse(String error, boolean isFail) {
        this.error = error;
        this.isFail = isFail;
    }

    public boolean isFail() { return this.isFail; }

    public String getError() { return this.error; }
}
