package com.dexlock.task.models;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(	name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskid;

    @Column
    private String title;

    @Column
    private String type;

    @Column
    private String description;

    @Column
    private String acceptanceCriteria;

    @Column
    private String status;

    @Column
    private Long startdate;

    @Column
    private Long duedate;

    @Column
    private String originalestimate;

    @Column
    private String reporter;

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getStartdate() {
        return startdate;
    }

    public void setStartdate(Long startdate) {
        this.startdate = startdate;

    }

    public Long getDuedate() {
        return duedate;
    }

    public void setDuedate(Long duedate) {
        this.duedate = duedate;
    }

    public String getOriginalestimate() {
        return originalestimate;
    }

    public void setOriginalestimate(String originalestimate) {
        this.originalestimate = originalestimate;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public Task(Long taskid, String title, String type, String description, String acceptanceCriteria, String status, Long startdate, Long duedate, String originalestimate, String reporter) {
        this.taskid = taskid;
        this.title = title;
        this.type = type;
        this.description = description;
        this.acceptanceCriteria = acceptanceCriteria;
        this.status = status;
        this.startdate = startdate;
        this.duedate = duedate;
        this.originalestimate = originalestimate;
        this.reporter = reporter;
    }

    public Task() {
    }
}
