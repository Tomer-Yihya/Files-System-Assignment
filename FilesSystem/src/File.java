
public class File {
	/*******************/
	/****** fields *****/
	/*******************/
	private String name;
	private Folder parentFolder;
	
	
	/*******************/
	/*** constructor **/
	/*******************/	
	
	/**
	 * constructor for new file.
	 */
	public File(String name) {
		this.name = name;
		this.parentFolder = null;
	}

	
	/********************/
	/** get/set methods */
	/********************/
	
	/** 
	 * sets the file name 
	 */
	public void setFileName(String fileName) {
		this.name = fileName;
	}

	/** 
	 * returns the file 
	 */
	public String getFileName() {
		return this.name;
	}
	
	/** 
	 * sets the file parent 
	 */
	public void setParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}

	/** 
	 * returns the file parent folder 
	 */
	public Folder getParentFolder() {
		return this.parentFolder;
	}
	
}
