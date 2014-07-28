package de.yourinspiration.jexpresso.staticresources;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link FileCacheLoader}.
 * 
 * @author Marcel Härle
 *
 */
public class FileCacheLoaderTest {

    private FileCacheLoader loader;

    @Before
    public void setUp() {
        loader = new FileCacheLoader();
    }

    @Test
    public void testLoadFile() throws Exception {
        final String path = "assets/test.txt";
        final FileCacheEntry entry = loader.load(path);
        assertEquals(path, entry.getPath());
        assertTrue(entry.isFound());
        assertFalse(entry.isDirectory());
        assertArrayEquals("test".getBytes(), entry.getBytes());
    }

    @Test
    public void testLoadDirectory() throws Exception {
        final String path = "assets";
        final FileCacheEntry entry = loader.load(path);
        assertEquals(path, entry.getPath());
        assertTrue(entry.isFound());
        assertTrue(entry.isDirectory());
    }

    @Test
    public void testLoadNotExistingFile() throws Exception {
        final String path = "assets/does/not/exist";
        final FileCacheEntry entry = loader.load(path);
        assertEquals(path, entry.getPath());
        assertFalse(entry.isFound());
    }

}
