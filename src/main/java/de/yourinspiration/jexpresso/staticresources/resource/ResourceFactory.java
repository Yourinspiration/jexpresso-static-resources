package de.yourinspiration.jexpresso.staticresources.resource;

public interface ResourceFactory {

    /* ------------------------------------------------------------ */
    /**
     * Get a resource for a path.
     * 
     * @param path
     *            The path to the resource
     * @return The resource or null
     */
    Resource getResource(String path);
}
