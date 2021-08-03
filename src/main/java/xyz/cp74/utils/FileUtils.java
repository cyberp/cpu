package xyz.cp74.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.tinylog.Logger;

/**
 * 
 * FileUtils
 * 
 * Read and write files.
 * 
 * @author Christian Paul
 *
 */
public class FileUtils {

	/**
	 * Write file
	 * @param file to write
	 * @param data to write
	 * @return true, if successfull
	 */
	public static boolean write(String file, String data) {
		if (file == null || data == null)
			return false;
		try {
			Path target = Paths.get(file, new String[]{});
			if (target!=null && target.getParent()!=null)
				Files.createDirectories(target.getParent());
			Files.write(target, data.getBytes());
			return true;
		} catch (IOException e) {
			Logger.error(e, "can't write file {}", file);
		}
		return false;
	}
	
	/**
	 * Read file to string
	 * @param file
	 * @return
	 */
	public static String read(String file) {
		return read(file, Charset.forName("UTF-8"));
	}
	
	/**
	 * Read file to string
	 * @param file
	 * @param charset
	 * @return
	 */
	public static String read(String file, Charset charset) {
		String s = null;
		if (file != null) {
			Path path = Paths.get(file, new String[]{});
			try {
				s = Files.readString(path, charset);
			} catch (IOException e) {
				Logger.error(e, "can't read file {}", file);
			}
		}
		return s;
	}
	
	/**
	 * Get extension of a filename
	 * @param file
	 * @return
	 */
	public static String getExtension(String file) {
		if (file!=null) {
			int i = file.trim().lastIndexOf('.');
			if (i!=-1 && i!=file.trim().length()-1)
				return file.substring(file.lastIndexOf('.')+1).toLowerCase();
		}
		return null;
	}
	
	/**
	 * Get a list of filenames from a given path
	 * @param path
	 * @return
	 */
	public static List<String> getFiles(String path) {
	    List<String> fileList = new ArrayList<String>();
	    String dir = "";
	    String glob = path;
	    int i = path.lastIndexOf('/');
	    if (i != -1) {
	    	dir = path.substring(0, i);
	    	glob = path.substring(i+1);
	    }
	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir), glob)) {
	        for (Path p: stream) {
	            if (!Files.isDirectory(p)) {
	            	if (!dir.isEmpty())
	            		fileList.add(dir+"/"+p.getFileName().toString());
	            	else
	            		fileList.add(p.getFileName().toString());
	            }
	        }
	    } catch (IOException e) {
			Logger.error(e);
		}
	    return fileList;
	}
	
}
