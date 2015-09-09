package com.diplab.device;

import java.io.File;
import java.io.FileFilter;


//This FileFilter selects subdirs with name beginning with 28-
// Kernel module gives each 1-wire temp sensor name starting with 28-
public class DirectoryFileFilter implements FileFilter
{
	   public boolean accept(File file) {
	     String dirName = file.getName();
	     String startOfName = dirName.substring(0, 3);
	     return (file.isDirectory() && startOfName.equals("28-"));
	   }
	}
