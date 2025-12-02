package ru.itk.jsonview.dto.user;

/**
 * An interface needed to create two separate json views - summary and detailed
 */
public interface UserView {

    /**
     * Basic information about the user - id, name, email
     */
    class Summary {}

    /**
     * Basic information + infromation about user's orders
     */
    class Detailed extends Summary {}
}
