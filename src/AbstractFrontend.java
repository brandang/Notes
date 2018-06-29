/**
 * Interface that represents the frontend of the program. Allows the backend to tell the frontend what to display.
 * It is recommended that one create a child interface of this interface tailored to the
 * class that is the frontend.
 */
public interface AbstractFrontend {
    /**
     * Set the AbstractBackend that this AbstractFrontend must communicate with.
     * @param backend The AbstractBackend.
     */
    void setBackend(AbstractBackend backend);
} 