package eg.edu.alexu.csd.oop.draw.cs07_40;

import java.util.List;
import java.util.Map;

/**
 * Container factory for creating containers for JSON object and JSON array.
 * 
 * @see org.json.simple.parser.JSONParser#parse(java.io.Reader,
 *      ContainerFactory)
 * 
 */
public interface ContainerFactory {
	/**
	 * @return A Map instance to store JSON object, or null if you want to use
	 *         org.json.simple.JSONObject.
	 */
	Map createObjectContainer();

	/**
	 * @return A List instance to store JSON array, or null if you want to use
	 *         org.json.simple.JSONArray.
	 */
	List creatArrayContainer();
}
