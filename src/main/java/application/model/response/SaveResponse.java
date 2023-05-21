package application.model.response;

/**
 * Class used for general save endpoint.
 */
public class SaveResponse<T> {
    private T entity;

    public T getEntity() {
        return this.entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

}
