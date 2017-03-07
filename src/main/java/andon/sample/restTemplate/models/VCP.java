package andon.sample.restTemplate.models;

/**
 * Created by Caozheng on 2017/3/7.
 */
public class VCP {
    private int id;
    private String projectKey;
    private String modelKey;
    private String subKey;
    private String description;
    private String createTime;
    private String lastUpdateTime;

    public void setId(int id) {
        this.id = id;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public void setSubKey(String subKey) {
        this.subKey = subKey;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getId() {
        return id;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getModelKey() {
        return modelKey;
    }

    public String getSubKey() {
        return subKey;
    }

    public String getDescription() {
        return description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }
}
