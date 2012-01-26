/*
 This file is part of JLogLib.

 JLogLib is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 JLogLib is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with JLogLib.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.miness.jloglib;

/**
 * This is the main class of JLogLib. To use JLogLib, you'll need <b>only</b> this class.<br /><br />
 * 
 * So with this class, you're able to:<br />
 * - Set up JLogLib<br />
 * - Request a logger object<br />
 * - Request a settings object<br />
 * - Request a utils object<br />
 * - Log stuff (convenience methods for the logger object)
 * 
 * @author Michel Neeser
 * @version 1.0
 */
public final class JLL {
	
	// A reference to the single instance of JLLogger
	private static JLLogger logger;
	// A reference to the single instance of JLLSettings
	private static JLLSettings settings;
	// A reference to the single instance of JLLUtils
	private static JLLUtils utils;
	// Will be set to true if the initial setup is done
	private static boolean setupDone = false;
	
	// This class can't be instantiated from outside
	private JLL() {}
		
	/**
	 * Sets up JLogLib. <br />
	 * If you don't call this method before calling getLogger(), getSettings() or getUtils(), it is called automatically.
	 */
	public static void setup() {
		logger = JLLogger.getInstance();
		settings = JLLSettings.getInstance();
		utils = JLLUtils.getInstance();
		setupDone = true;
	}
	
	/**
	 * Returns the single JLLogger object.
	 * @return the JLLogger object.
	 */
	public static JLLogger getLogger() {
		if (!setupDone) { setup(); }
		return logger;
	}
	
	/**
	 * Returns the single JLLSettings object.
	 * @return the JLLSettings object.
	 */
	public static JLLSettings getSettings() {
		if (!setupDone) { setup(); }
		return settings;
	}
	
	/**
	 * Returns the single JLLUtils object.
	 * @return the JLLUtils object.
	 */
	public static JLLUtils getUtils() {
		if (!setupDone) { setup(); }
		return utils;
	}
	
	/**
	 * Convenience method to log messages.
	 * The message will be redirected to the logger object (with log level MEDIUM).
	 * @param message the message to log.
	 */
	public static void log(String message) {
		getLogger().log(message);
	}
	
	/**
	 * Convenience method to log messages.
	 * The message will be redirected to the logger object (with the specified log level).
	 * @param message the message to log.
	 * @param level the log level for this message.
	 */
	public static void log(String message, LogLevel level) {
		getLogger().log(message, level);
	}
	
}