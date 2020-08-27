package ru.mantis.model;

public class Issue {

    private int id;
    private String summary;
    private String description;
    private Project project;
    private int status;

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", project=" + project +
                ", status=" + getStringStatus() +
                '}';
    }

    private String getStringStatus() {
        switch (status){
            case 10:
                return "new";
            case 20:
                return "feedback";
            case 50:
                return "assigned";
            case 80:
                return "resolved";
            case 90:
                return "closed";
            default:
                return "unknown";
        }
    }

    public int getStatus() {
        return status;
    }

    public Issue withStatus(int status) {
        this.status = status;
        return this;
    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }
}
