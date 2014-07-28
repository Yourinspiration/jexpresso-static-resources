package de.yourinspiration.jexpresso.staticresources.resource;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* ------------------------------------------------------------ */
/**
 * ClassLoader Helper. This helper class allows classes to be loaded either from
 * the Thread's ContextClassLoader, the classloader of the derived class or the
 * system ClassLoader.
 *
 * <B>Usage:</B>
 * 
 * <PRE>
 * public class MyClass {
 *     void myMethod() {
 *          ...
 *          Class c=Loader.loadClass(this.getClass(),classname);
 *          ...
 *     }
 * </PRE>
 * 
 */
public class Loader {
    /* ------------------------------------------------------------ */
    public static URL getResource(Class<?> loadClass, String name, boolean checkParents) {
        URL url = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        while (url == null && loader != null) {
            url = loader.getResource(name);
            loader = (url == null && checkParents) ? loader.getParent() : null;
        }

        loader = loadClass == null ? null : loadClass.getClassLoader();
        while (url == null && loader != null) {
            url = loader.getResource(name);
            loader = (url == null && checkParents) ? loader.getParent() : null;
        }

        if (url == null) {
            url = ClassLoader.getSystemResource(name);
        }

        return url;
    }

    /* ------------------------------------------------------------ */
    @SuppressWarnings("rawtypes")
    public static Class loadClass(Class loadClass, String name) throws ClassNotFoundException {
        return loadClass(loadClass, name, false);
    }

    /* ------------------------------------------------------------ */
    /**
     * Load a class.
     * 
     * @param loadClass
     * @param name
     * @param checkParents
     *            If true, try loading directly from parent classloaders.
     * @return Class
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("rawtypes")
    public static Class loadClass(Class loadClass, String name, boolean checkParents) throws ClassNotFoundException {
        ClassNotFoundException ex = null;
        Class<?> c = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        while (c == null && loader != null) {
            try {
                c = loader.loadClass(name);
            } catch (ClassNotFoundException e) {
                if (ex == null)
                    ex = e;
            }
            loader = (c == null && checkParents) ? loader.getParent() : null;
        }

        loader = loadClass == null ? null : loadClass.getClassLoader();
        while (c == null && loader != null) {
            try {
                c = loader.loadClass(name);
            } catch (ClassNotFoundException e) {
                if (ex == null)
                    ex = e;
            }
            loader = (c == null && checkParents) ? loader.getParent() : null;
        }

        if (c == null) {
            try {
                c = Class.forName(name);
            } catch (ClassNotFoundException e) {
                if (ex == null)
                    ex = e;
            }
        }

        if (c != null)
            return c;
        throw ex;
    }

    /* ------------------------------------------------------------ */
    public static ResourceBundle getResourceBundle(Class<?> loadClass, String name, boolean checkParents, Locale locale)
            throws MissingResourceException {
        MissingResourceException ex = null;
        ResourceBundle bundle = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        while (bundle == null && loader != null) {
            try {
                bundle = ResourceBundle.getBundle(name, locale, loader);
            } catch (MissingResourceException e) {
                if (ex == null)
                    ex = e;
            }
            loader = (bundle == null && checkParents) ? loader.getParent() : null;
        }

        loader = loadClass == null ? null : loadClass.getClassLoader();
        while (bundle == null && loader != null) {
            try {
                bundle = ResourceBundle.getBundle(name, locale, loader);
            } catch (MissingResourceException e) {
                if (ex == null)
                    ex = e;
            }
            loader = (bundle == null && checkParents) ? loader.getParent() : null;
        }

        if (bundle == null) {
            try {
                bundle = ResourceBundle.getBundle(name, locale);
            } catch (MissingResourceException e) {
                if (ex == null)
                    ex = e;
            }
        }

        if (bundle != null)
            return bundle;
        throw ex;
    }

    /* ------------------------------------------------------------ */
    /**
     * Generate the classpath (as a string) of all classloaders above the given
     * classloader.
     * 
     * This is primarily used for jasper.
     * 
     * @return the system class path
     */
    public static String getClassPath(ClassLoader loader) throws Exception {
        StringBuilder classpath = new StringBuilder();
        while (loader != null && (loader instanceof URLClassLoader)) {
            URL[] urls = ((URLClassLoader) loader).getURLs();
            if (urls != null) {
                for (int i = 0; i < urls.length; i++) {
                    Resource resource = Resource.newResource(urls[i]);
                    File file = resource.getFile();
                    if (file != null && file.exists()) {
                        if (classpath.length() > 0)
                            classpath.append(File.pathSeparatorChar);
                        classpath.append(file.getAbsolutePath());
                    }
                }
            }
            loader = loader.getParent();
        }
        return classpath.toString();
    }
}
