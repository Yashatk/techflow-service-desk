package com.techflow.ws.sys.domain;

public class ThreadInfo {
    private String name;
    private long id;
    private String state;
    private StackTraceElement[] stackTrace;

    public ThreadInfo(String name, long id, String state, StackTraceElement[] stackTrace) {
        this.name = name;
        this.id = id;
        this.state = state;
        this.stackTrace = stackTrace;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return StackTraceElement[] return the stackTrace
     */
    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }

    /**
     * @param stackTrace the stackTrace to set
     */
    public void setStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = stackTrace;
    }

}