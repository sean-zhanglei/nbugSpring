package com.nbug.ss.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yihua.huang@dianping.com
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
