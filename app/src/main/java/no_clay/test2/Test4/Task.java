package no_clay.test2.Test4;

import java.io.Serializable;

/**
 * Created by noclay on 2017/5/17.
 */

public class Task implements Serializable{
    private static final long serialVersionUID = -6260638343988981965L;
    public String date;
    public String startTime;
    public String endTime;
    public String taskContent;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
}
