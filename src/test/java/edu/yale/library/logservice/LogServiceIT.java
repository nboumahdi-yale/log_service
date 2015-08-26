package edu.yale.library.logservice;

import org.slf4j.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.tika.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * @author Osman Din
 */
public class LogServiceIT extends AbstractWarTest {

    private static final Logger logger = getLogger(LogServiceIT.class);

    private static final String HTTP_SERVICE = "log/hello";

    @BeforeClass
    public static void setup() throws MalformedURLException {
        try {
            AbstractWarTest.setupContainer();
        } catch (RuntimeException e) {
            logger.error("Error starting container", e);
            fail("Error starting container");
        }
    }

    @AfterClass
    public static void tearDown() {
    }

    @Test
    public void testGet() throws Exception {
        HttpServiceTestUtil httpServiceTestUtil = new HttpServiceTestUtil();
        final HttpGet getMethod0 = httpServiceTestUtil.doGET(HTTP_SERVICE);
        final HttpResponse response0 = httpServiceTestUtil.httpClient.execute(getMethod0);
        assertNotNull(response0);
        assertEquals(IOUtils.toString(response0.getEntity().getContent()), 200,
                response0.getStatusLine().getStatusCode());
    }

}