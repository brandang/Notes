/**
 * Interface that represent the backend code for the program. Used to allow the GUI to communicate
 * with the backend. It is recommended that one create a child interface of this interface tailored to the
 * class that is the backend.
 */
public interface AbstractBackend {
    /**
     * Set the AbstractFrontend that this AbstractBackend must communicate with.
     * @param frontend The AbstractFrontend.
     */
    void setFrontend(AbstractFrontend frontend);
}