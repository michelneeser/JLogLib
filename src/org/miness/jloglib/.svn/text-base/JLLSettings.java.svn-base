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

import java.io.File;

/**
 * This class represents the settings of JLogLib.<br />
 * It is a singleton, hence there's only one instance at runtime (held by this class itself).
 * 
 * @author Michel Neeser
 * @version 1.0
 */
public final class JLLSettings implements Cloneable {
	
	// Single instance of this class
	private static JLLSettings instance;
	// Another instance of this class to save/load state
	private JLLSettings instanceCopy;
	// General enabling/disabling setting
	private boolean enableLogging;
	// Log level settings
	private LogLevel logLevel;
	private boolean allowSubLogLevels;
	// Log target settings
	private LogTarget logTarget;
	private File logFile;
	// Date and time settings
	private boolean printDate;
	private LogDateFormat dateFormat;
	private boolean printTime;
	private LogDateFormat timeFormat;
	// Delimiter settings
	private String delimiter;
	// Application name settings
	private String applicationName;
	private boolean printApplicationName;
	
	// This class can't be instantiated from outside
	private JLLSettings() {}
	
	/* Returns the single instance of this class.
	 * Default access, should be only callable from within JLogLib. */
	protected static JLLSettings getInstance() {
		if (instance == null) {
			instance = new JLLSettings();
			instance.setup();
		}
		return instance;
	}
	
	// Sets up the instance of this class
	private void setup() {
		// Set default values
		enableLogging(true);
		setLogLevel(LogLevel.MEDIUM);
		allowSubLogLevels(true);
		setLogTarget(LogTarget.CONSOLE);
		setLogFile(null);
		printDate(true);
		setDateFormat(LogDateFormat.MEDIUM);
		printTime(true);
		setTimeFormat(LogDateFormat.MEDIUM);
		setDelimiter("==>");
		setApplicationName("JLogLib");
		printApplicationName(true);
	}
	
	// Creates a copy of the current state of JLLSettings
	protected void createInstanceCopy() {
		try {
			instanceCopy = (JLLSettings) instance.clone();
		}
		catch (CloneNotSupportedException e) {
			// Cloning is always supported, so the user will never be faced with this message.
		}
	}
	
	// Returns the copied instance of JLLSettings
	protected JLLSettings getInstanceCopy() {
		return instanceCopy;
	}
	
	// Resets the instance just by calling setup() again
	protected void reset() {
		setup();
	}
	
	/**
	 * Defines if the logging functionality in general should by enabled/disabled.
	 * @param b the boolean value to set.
	 */
	public void enableLogging(boolean b) {
		this.enableLogging = b;
	}
	
	/**
	 * Returns the boolean which defines if the logging functionality in general is enabled or not.
	 * @return the boolean value.
	 */
	public boolean isLoggingEnabled() {
		return enableLogging;
	}
	
	/**
	 * Sets the log level (LOW, MEDIUM or HIGH).
	 * @param level the log level to set.
	 */
	public void setLogLevel(LogLevel level) {
		this.logLevel = level;
	}
	
	/**
	 * Returns the log level which is currently set.
	 * @return the current log level.
	 */
	public LogLevel getLogLevel() {
		return logLevel;
	}
	
	/**
	 * Defines if sub log levels are allowed.<br >
	 * <b>Example:</b> If true and current log level is MEDIUM, then messages with log level MEDIUM and LOW are printed.<br />
	 * If false and current log level is MEDIUM, then only messages with log level MEDIUM are printed.
	 * @param b the boolean value to set.
	 */
	public void allowSubLogLevels(boolean b) {
		this.allowSubLogLevels = b;
	}
	
	/**
	 * Returns the boolean which defines if sub log levels are allowed.<br >
	 * For more information, see Javadoc of allowSubLogLevels(boolean b).
	 * @return the currently set boolean value.
	 */
	public boolean getAllowSubLogLevels() {
		return allowSubLogLevels;
	}
	
	/**
	 * Sets the log target (CONSOLE or FILE).
	 * @param target the log target to set.
	 */
	public void setLogTarget(LogTarget target) {
		this.logTarget = target;
	}
	
	/**
	 * Returns the log target which is currently set.<br />
	 * <b>Attention:</b> If the target is FILE but there's no file set, CONSOLE is returned.
	 * @return the current log target.
	 */
	public LogTarget getLogTarget() {
		if (getLogFile() == null) {
			return LogTarget.CONSOLE;
		}
		else {
			return logTarget;
		}
	}
	
	/**
	 * Sets the log file (the file which is used for logging when the log target is FILE).
	 * @param path the path to the file on the file system.
	 */
	public void setLogFile(String path) {
		if (path != null) {
			this.logFile = new File(path);
		}
	}
	
	/**
	 * Returns the file which is currently set as log file.
	 * @return the current file.
	 */
	public File getLogFile() {
		return logFile;
	}
	
	/**
	 * Specifies if the date will be printed with each log message or not.
	 * @param b the boolean value.
	 */
	public void printDate(boolean b) {
		this.printDate = b;
	}
	
	/**
	 * Returns the boolean which defines if the print date option is set.
	 * @return the boolean value.
	 */
	public boolean getPrintDate() {
		return printDate;
	}
	
	/**
	 * Sets the desired format of the date which is printed with a log message (if getPrintDate() is true).
	 * @param format the format.
	 */
	public void setDateFormat(LogDateFormat format) {
		this.dateFormat = format;
	}
	
	/**
	 * Returns the date format which is currently set.
	 * @return the date format.
	 */
	public LogDateFormat getDateFormat() {
		return dateFormat;
	}
	
	/**
	 * Specifies if the time will be printed with each log message or not.
	 * @param b the boolean value.
	 */
	public void printTime(boolean b) {
		this.printTime = b;
	}
	
	/**
	 * Returns the boolean which defines if the print time option is set.
	 * @return the boolean value.
	 */
	public boolean getPrintTime() {
		return printTime;
	}
	
	/**
	 * Sets the desired format of the time which is printed with a log message (if getPrintTime() is true).
	 * @param format the format.
	 */
	public void setTimeFormat(LogDateFormat format) {
		this.timeFormat = format;
	}
	
	/**
	 * Returns the time format which is currently set.
	 * @return the time format.
	 */
	public LogDateFormat getTimeFormat() {
		return timeFormat;
	}
	
	/**
	 * Sets the delimiter for log entries.<br />
	 * E.g. the delimiter of "29.09.11 07:47 ==> Hello World" is "==>".
	 * @param delimiter the delimiter to set.
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	/**
	 * Returns the currently set delimiter.
	 * @return the delimiter.
	 */
	public String getDelimiter() {
		return delimiter;
	}
	
	/**
	 * Sets the name of the application.<br />
	 * Used to print the name of the application in log messages (if getPrintApplicationName() is true).
	 * @param name the name to set.
	 */
	public void setApplicationName(String name) {
		this.applicationName = name;
	}
	
	/**
	 * Returns the name of the application which is currently set.
	 * @return the name of the application.
	 */
	public String getApplicationName() {
		return applicationName;
	}
	
	/**
	 * Defines if the application name should be printed with each log message.
	 * @param b the boolean value to set.
	 */
	public void printApplicationName(boolean b) {
		this.printApplicationName = b;
	}
	
	/**
	 * Returns if the application name should be printed with each log message.
	 * @return the boolean value.
	 */
	public boolean getPrintApplicationName() {
		return printApplicationName;
	}

}