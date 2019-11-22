package util;

/**
 * Constructor class to construct a session
 * @author xHelixStorm
 *
 */

public class Session {
	String jarName;
	String path;
	String sessionName;
	boolean useParameters;
	String parameters;
	boolean useTempDirectory;
	String tempDirectory;
	String tempFileName;
	
	/**
	 * 
	 * @param _jarName Name of the jar file
	 * @param _path Path where the jar file resides
	 * @param _sessionName Name of the session
	 * @param _useParameters true/false if parameters should be passed
	 * @param _parameters contains all parameters for this session
	 * @param _useTempDirectory true/false if a temporary directory should be used
	 * @param _tempDirectory Path to the temporary directory
	 * @param _tempFileName name of the temporary file name for the session status
	 */

	public Session(String _jarName, String _path, String _sessionName, boolean _useParameters, String _parameters, boolean _useTempDirectory, String _tempDirectory, String _tempFileName) {
		this.jarName = _jarName;
		this.path = _path;
		this.sessionName = _sessionName;
		this.useParameters = _useParameters;
		this.parameters = _parameters;
		this.useTempDirectory = _useTempDirectory;
		this.tempDirectory = _tempDirectory;
		this.tempFileName = _tempFileName;
	}
	
	/**
	 * Retrieve the name of the jar file
	 * @return
	 */
	
	public String getJarName() {
		return this.jarName;
	}
	
	/**
	 * Retrieve the path of where the jar file resides
	 * @return
	 */
	
	public String getPath() {
		return path;
	}
	
	/**
	 * Retrieve the name of the session
	 * @return
	 */
	
	public String getSessionName() {
		return this.sessionName;
	}
	
	/**
	 * Retrieve a boolean value for either allowing or prohibit the use of parameters
	 * @return
	 */
	
	public boolean useParameters() {
		return this.useParameters;
	}
	
	/**
	 * Retrieve all parameters with which the jar file has to be launched
	 * @return
	 */
	
	public String getParameters() {
		return this.parameters;
	}
	
	/**
	 * Retrieve a boolean value for either allowing or prohibit the use of a temporary directory
	 * @return
	 */
	
	public boolean useTempDirectory() {
		return this.useTempDirectory;
	}
	/**
	 * Retrieve the temporary directory
	 * @return
	 */
	
	public String getTempDirectory() {
		return this.tempDirectory;
	}
	
	/**
	 * Retrieve the name of the session file which contains a running status
	 * @return
	 */
	
	public String getTempFileName() {
		return this.tempFileName;
	}
}
