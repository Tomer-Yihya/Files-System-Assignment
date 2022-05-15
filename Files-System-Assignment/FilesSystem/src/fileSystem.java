import java.util.HashMap;


public class fileSystem {
	/***************/
	/** variables **/
	/***************/
	/* filesMap <String - folderName, Folder - pointer> */
	HashMap<String,Folder> folderMap = new HashMap<>();
	
	/***************/
	/*** methods ***/
	/***************/
	
	/* creates folder only if it doesn't already have a folder with the same name */
	public Folder createMainFolder(String newFolder) {
		if(this.folderMap.containsKey(newFolder)) {
			System.out.println("the folder " +newFolder+ " already exists");
			return null;
		}
		
		Folder folder = new Folder(newFolder);
		this.folderMap.put(newFolder, folder);
		System.out.println("The folder"+ newFolder +" was created successfully");	
		return folder;
	}

	
	/* add main folder only if it doesn't already have a folder with the same name */
	public Folder addMainFolder(Folder folder) {
		if(this.folderMap.containsKey(folder.getFolderName())) {
			System.out.println("the folder " +folder.getFolderName()+ " already exists");
			return null;
		}
		
		this.folderMap.put(folder.getFolderName(), folder);
		System.out.println("The folder "+ folder.getFolderName() +" was created successfully");	
		return folder;
	}
	
	
	/* create new folder in given folder */
	public Folder createFolderInFolder(String newFolder, Folder parentFolder) {
		if(parentFolder == null) {
			System.out.println("This is not a valid folder");
			return null;
		}
		// if the folder already exists
		if(parentFolder.getInnerFolders().containsKey(newFolder)) {
			System.out.println("the folder " +newFolder+ " already exists");
			return null;
		}
		
		Folder folder = new Folder(newFolder, parentFolder);
		parentFolder.getInnerFolders().put(newFolder, folder);
		System.out.println("The folder"+ newFolder +" was created successfully");
		return folder;
	}
	
	/* create new file in the folder */
	public void createFileInFolder(String fileName, Folder parentFolder) {
		if(parentFolder == null) {
			System.out.println("This is not a valid folder");
			return;
		}
		// if the file already exists in this folder
		if(parentFolder.getFiles().containsKey(fileName)) {
			System.out.println("the file " +fileName+ " already exists");
			return;
		}
		
		File file = new File(fileName);
		file.setParentFolder(parentFolder);
		parentFolder.getFiles().put(fileName, file);
		System.out.println("The file"+ fileName +" was created successfully");
	}
	
	
	/* create new link in the folder */
	public void createLinkInFolder(Folder folder, String linkName) {
		if(folder == null) {
			System.out.println("This is not a valid folder");
			return;
		}
		// if the link already exists in this folder
		if(folder.getLinks().containsKey(linkName)) {
			System.out.println("the folder " +linkName+ " already exists");
			return;
		}
		
		Link link = new Link(linkName);
		linkInitializ(link);
		folder.getLinks().put(linkName, link);
		System.out.println("The link"+ linkName +" was created successfully");
	}
	
	
	/* Initialization the link pointers */
	public void linkInitializ(Link link) {
		String[] path = link.getLinkName().split("-");
		if(path.length == 0)
			return;
		
		Folder temp = this.folderMap.get(path[0]);
		if(temp == null)
			return;
		
		if(path.length == 1) {
			link.setpointToFolder(temp);
			return;
		}
		
		for(int i = 1 ; i < path.length-2 ;i++) {
			temp = temp.getInnerFolders().get(path[i]);
		}
		
		if(link.getIsAFolder()) {
			temp = temp.getInnerFolders().get(path[path.length-1]);
			link.setpointToFolder(temp);
			return;
		}
		link.setpointToFolder(temp);
		link.setpointToFile(temp.getFiles().get(path[path.length-1]));
	}
	
	
	/* Returns true if the file was indeed deleted  */
	public boolean deleteFileFromFolder(Folder folder, String fileName) {
		if(!folder.getFiles().containsKey(fileName)) {
			System.out.println("There is no file named " +fileName+" in the folder");
			return false;
		}
		folder.getFiles().remove(fileName);
		System.out.println("The file was successfully deleted");
		return true;
	}
	
	
	/* Returns true if the link was indeed deleted  */
	public boolean deleteLinkFromFolder(Folder folder, String linkName) {
		if(!folder.getLinks().containsKey(linkName)) {
			System.out.println("There is no file named " +linkName+" in the folder");
			return false;
		}
		folder.getLinks().remove(linkName);
		System.out.println("The link was successfully deleted");
		return true;
	}
	
		
	/* delete given folder if it exists inside the parent folder */
	public boolean deleteFolderFromFolder(Folder folder, String folderToBeDeleted) {
		if(folder.getInnerFolders().containsKey(folderToBeDeleted)){
			deleteLinksInFolder(folder.getInnerFolders().get(folderToBeDeleted));
			System.out.println("the foldere was successfully removed");
			return true;
		}
		System.out.println(folderToBeDeleted +" was not found inside "+ folder.getFolderName());
		return false;
	}

	
	public boolean deleteFolder(Folder folder) {
		// main folder
		if(folder.getParentFolder() == null){
			this.folderMap.remove(folder.getFolderName());
			System.out.println("the foldere was successfully removed");
			return true;
		}	
		else {	
			Folder parent = folder.getParentFolder();
			parent.getInnerFolders().remove(folder.getFolderName());
			System.out.println("the foldere was successfully removed");
			return true;
		}
	}
	
	
	/* prints the folder and all its contents according to the format  */
	public void printFolder(Folder folder) {
		System.out.println();
		System.out.println("The contents of the folder:");
		folder.printFolder();
	}
	
	
	/* deletes the contents of the folder recursively */
	public void deleteLinksInFolder(Folder folder) {
		folder.getLinks().clear();
		for (Folder innerFolder : folder.getInnerFolders().values()) {
			deleteLinksInFolder(innerFolder);
		}
	}


	
	/* Searches the folder throughout the system 
	 * and returns it or null if not found */
	public Folder findFolder(String folder) {
		if (folderMap.get(folder) != null) {
			return folderMap.get(folder);
		}
		Folder foundFolder;
		for (String innerFolder : folderMap.keySet()) {
			foundFolder = findFolder(folder, folderMap.get(innerFolder));
			if (foundFolder != null) {
				return foundFolder;
			}
		}
		return null;
		
	}
	
	
	/*********************/
	/** private methods **/
	/*********************/
	
	private Folder findFolder(String foldername, Folder inFolder) {
		Folder foundFolder;
		for (Folder folder : inFolder.getInnerFolders().values()) {
			if (folder.getFolderName().equals(foldername)) {
				return folder;
			}
			foundFolder = findFolder(foldername, folder);
			if (foundFolder != null) {
				return foundFolder;
			}
		}
		return null;
		
	}
	
	
}
