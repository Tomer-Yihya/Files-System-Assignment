
public class Link {
	/*******************/
	/****** fields *****/
	/*******************/
	private String name;
	private boolean isAFolder; 
	private Folder pointToFolder;
	private File pointToFile;
	
	/*******************/
	/*** constructors **/
	/*******************/
	
	/**
	 * constructor for Link.
	 */
	public Link (String name) {
		this.name = name;
		isAFolder = isAFolder(name);
		this.pointToFolder = null;
		this.pointToFile = null;
	}
		
	
	/********************/
	/** get/set methods */
	/********************/
	
	/** 
	 * returns true if the Link is for a folder 
	 */
	public boolean getIsAFolder() {
		return this.isAFolder;
	}

	/** 
	 * sets the Link name 
	 */
	public void setLinkName(String LinkName) {
		this.name = LinkName;
	}

	/** 
	 * returns if the Link name 
	 */
	public String getLinkName() {
		return this.name;
	}
	
	/** 
	 * returns a pointer to the folder where the file or folder is located 
	 */
	public void setpointToFolder(Folder folder) {
		this.pointToFolder = folder;
	}
	
	/** 
	 * returns a pointer to the file  
	 */
	public void setpointToFile(File file) {
		this.pointToFile = file;
	}

	/** 
	 * Returns a pointer to the folder if it's a folder link
	 * Or returns a pointer to the folder where the file is located 
	 */
	public Folder getpointToFolder() {
		return this.pointToFolder;
	}
	
	/** 
	 * Checks whether the link points to a folder
	 */
	public boolean isAFolder(String LinkName){
		String[] res = LinkName.split("."); 
		// there is no "." in the Link -> it is a folder
		if(res.length == 1) {
			return true;
		}
		return false;
	}
	
}
