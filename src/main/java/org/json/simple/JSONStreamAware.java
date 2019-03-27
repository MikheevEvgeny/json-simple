package org.json.simple;

import java.io.IOException;
import java.io.Writer;

/**
 * Beans that support customized output of JSON text to a writer shall implement this interface.
 *
 * @author FangYidong
 */
public interface JSONStreamAware {
	/**
	 * write JSON string to out.
	 *
	 * @param out write result to
	 * @throws IOException Something went wrong
	 */
	void writeJSONString(Writer out) throws IOException;
}
