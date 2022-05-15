import java.util.HashMap;


public class Folder {
		
		/*******************/
		/****** fields *****/
		/*******************/
		private String name;
		private Folder parentFolder;
		private HashMap<String,Folder> innerFolders = new HashMap<>();
		private HashMap<String,File> files = new HashMap<>();
		private HashMap<String,Link> links = new HashMap<>();
		
		
		/*******************/
		/*** constructors **/
		/*******************/
		
		/**
		 * constructor for folder.
		 */
		public Folder(String name) {
			this.name = name;
			this.parentFolder = null;
			this.innerFolders = new HashMap<>();
			this.files = new HashMap<>();
			this.links = new HashMap<>();
		}
		
		/**
		 * constructor for inner folder.
		 */
		public Folder(String name, Folder parentFolder) {
			this.name = name;
			this.parentFolder = parentFolder;
			this.innerFolders = new HashMap<>();
			this.files = new HashMap<>();
			this.links = new HashMap<>();
		}

		
		/********************/
		/** get/set methods */
		/********************/
		
		/** 
		 * sets the folder name 
		 */
		public void setFolderName(String folderName) {
			this.name = folderName;
		}

		/** 
		 * returns the folder parent or Null 
		 */
		public String getFolderName() {
			return this.name;
		}
		
		/** 
		 * sets the folder parent 
		 */
		public void setParentFolder(Folder parentFolder) {
			this.parentFolder = parentFolder;
		}

		/** 
		 * returns the folder parent or Null 
		 */
		public Folder getParentFolder() {
			return this.parentFolder;
		}
		
		/** 
		 * returns this folder links HashMap<link in String,folder location where the file/folder is> 
		 */
		public HashMap<String,Link> getLinks() {
			return this.links;
		}
		
		/** 
		 * returns this folder files HashMap<file name, list off all this file endings> 
		 */
		public HashMap<String,File> getFiles() {
			return this.files;
		}
		
		/** 
		 * returns this folder inner folders HashMap<folder name, pointer to this folder> 
		 */
		public HashMap<String,Folder> getInnerFolders() {
			return this.innerFolders;
		}
		
		
		/**
		 * insert given file to the folder
		 */
		public File addFileToFolder(File file) {
			if(this == null || file == null) {
				System.out.println("This is not a valid folder");
				return null;
			}
			// if the file already exists in this folder
			if(this.getFiles().containsKey(file.getFileName())) {
				System.out.println("the file " +file.getFileName()+ " already exists");
				return null;
			}
			
			this.getFiles().put(file.getFileName(), file);
			file.setParentFolder(this);
			System.out.println("The file "+ file.getFileName() +" was created successfully");
			return file;
		}

		
		/**
		 * insert link to the folder
		 */
		public Link addLinkToFolder(Link link) {
			if(this == null || link == null) {
				System.out.println("This is not a valid folder or Link");
				return null;
			}
			// if the link already exists in this folder
			if(this.getLinks().containsKey(link.getLinkName())) {
				System.out.println("the link " +link.getLinkName()+ " already exists");
				return null;
			}
			
			this.getLinks().put(link.getLinkName(), link);
			System.out.println("The link "+ link.getLinkName() +" was created successfully");
			return link;
		}
		
		
		/**
		 * insert given folder to parent folder
		 */
		public Folder addFolder(Folder newFolder) {
			if(this == null || newFolder == null) {
				System.out.println("This is not a valid folder");
				return null;
			}
			// if the folder already exists
			if(this.getInnerFolders().containsKey(newFolder.getFolderName())) {
				System.out.println("the folder already exists");
				return null;
			}
			
			this.getInnerFolders().put(newFolder.getFolderName(), newFolder);
			newFolder.setParentFolder(this);
			System.out.println("The folder "+ newFolder.getFolderName() +" was created successfully");
			return newFolder;
		}
		
		
		
		
		/********************/
		/** print Folder  ***/
		/********************/
		
		/** 
		 * prints the folder and all its contents according to the format  
		 */
		public void printFolder() {
			printFolderRec(this,"");
		}
		
		/** 
		 * recursive function for printing folders
		 */
		private void printFolderRec(Folder folder, String tabs) {
			// print files
			System.out.println(tabs+"* " + folder.name);
			tabs += "   ";
			for(String file : folder.getFiles().keySet()) {
				System.out.println(tabs+"$ " + file);
			}
			for(String link : folder.getLinks().keySet()) {
				if(folder.getLinks().containsKey(link) && folder.getLinks().get(link).getpointToFolder() != null)
					System.out.print(tabs + "@");
				else
					System.out.print(tabs + "!@");
				
				System.out.println(link);
			}
			for(Folder innerFolder : folder.getInnerFolders().values()) {
				printFolderRec(innerFolder, tabs);
			}
		}
		
	}
