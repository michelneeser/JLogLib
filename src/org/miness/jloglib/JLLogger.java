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

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * This class represents the logger of JLogLib.<br />
 * It is a singleton, hence there's only one instance at runtime (held by this class itself).
 * 
 * @author Michel Neeser
 * @version 1.0
 */
public final class JLLogger {
	
	// Single instance of this class
	private static JLLogger instance;
	// A reference to the single instance of JLLSettings
	private JLLSettings settings;
	// A reference to the single instance of JLLUtils
	private JLLUtils utils;
	
	// This class can't be instantiated from outside
	private JLLogger() {}
	
	/* Returns the single instance of this class.
	 * Default access, should be only callable from within JLogLib. */
	protected static synchronized JLLogger getInstance() {
		if (instance == null) {
			instance = new JLLogger();
			instance.setup();
		}
		return instance;
	}
	
	// Sets up the instance of this class
	private void setup() {
		this.settings = JLLSettings.getInstance();
		this.utils = JLLUtils.getInstance();
	}
	
	/**
	 * Writes the given message with log level MEDIUM to the log target.
	 * @param message the message to log.
	 */
	public void log(String message) {
		log(message, LogLevel.MEDIUM);
	}
	
	/**
	 * Writes the given message with the given log level to the log target.
	 * @param message the message to log.
	 * @param level the log level.
	 */
	public void log(String message, LogLevel level) {
		try {
			// Check if logging is enabled in general and if the given log level corresponds to the settings
			if (settings.isLoggingEnabled() && utils.checkLogLevel(level)) {
				DateFormat df = utils.createDateFormat(); // Create date formatter based on the settings
				StringBuilder logString = new StringBuilder();
				if (df != null) {
					logString.append(df.format(Calendar.getInstance().getTime()));
					if (settings.getPrintApplicationName()) {
						logString.append(" " + settings.getDelimiter() + " " + settings.getApplicationName());
					}
					logString.append(" " + settings.getDelimiter() + " " + message);
				}
				else {
					if (settings.getPrintApplicationName()) {
						logString.append(settings.getApplicationName() + " " + settings.getDelimiter() + " ");
					}
					logString.append(message);
				}
				if (settings.getLogTarget() == LogTarget.CONSOLE) {
					System.out.println(logString);
				}
				else if (settings.getLogTarget() == LogTarget.FILE) {
					// Appending only works with a wrapped FileWriter!
					PrintWriter writer = new PrintWriter(new FileWriter(settings.getLogFile(), true));
					writer.println(logString);
					writer.flush();
				}
			}
		}
		catch (IOException e) {
			utils.printInternalError("Possible Reasons:\n" +
									 "   - The log file exists but is a directory rather than a regular file.\n" +
									 "   - The log file does not exist but cannot be created.\n" +
									 "   - The log file cannot be opened for any other reason.");
		}
	}
	
}