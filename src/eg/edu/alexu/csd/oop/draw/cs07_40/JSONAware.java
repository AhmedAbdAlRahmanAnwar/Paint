package eg.edu.alexu.csd.oop.draw.cs07_40;

/**
 * Beans that support customized output of JSON text shall implement this
 * interface.
 */
public interface JSONAware {
	/**
	 * @return JSON text
	 */
	String toJSONString();
}
