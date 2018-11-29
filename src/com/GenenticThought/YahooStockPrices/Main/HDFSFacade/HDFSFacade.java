package com.GenenticThought.YahooStockPrices.Main.HDFSFacade;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.util.Progressable;

import com.GenenticThought.YahooStockPrices.UnitTests.StockTest;

public class HDFSFacade {
	
	 private static final Logger LOG = Logger.getLogger(HDFSFacade.class.getName());
     private String hadoopUserName = "moustafarefaat";
     private String uri = null;
     private FileSystem hdfs = null;
     
     public HDFSFacade(String hun,String _uri)
     {
    	 hadoopUserName = hun;
    	 uri = _uri;
    	 System.setProperty("HADOOP_USER_NAME",hadoopUserName );
     }
    
     /**
 	 * @param 
 	 * @return
 	 * @throws IOException
 	 */
 	private void init() throws IOException {
 		if(null != hdfs && null!= uri && !uri.isEmpty()){
 		   hdfs = FileSystem.get(URI.create(uri), new Configuration());
 		}
 	}
     
     public void deleteDir(String dir) throws IOException{
   	 
    	init();
    	 Path homeDir=hdfs.getHomeDirectory();
    	 Path newDirPath= new Path(dir);
    	 newDirPath=Path.mergePaths(homeDir, newDirPath);
    	 if(hdfs.exists(newDirPath))
    	 {
    	 hdfs.delete(newDirPath, true); //Delete existing Directory
    	 }
     }
     
     public void createDir(String dir) throws IOException{
    	 init();
    	 Path homeDir=hdfs.getHomeDirectory();
    	 Path newDirPath= new Path(dir);
    	 newDirPath=Path.mergePaths(homeDir, newDirPath);
    	 if(hdfs.exists(newDirPath))
    	 {
    	 hdfs.delete(newDirPath, true); //Delete existing Directory
    	 }
    	 hdfs.mkdirs(newDirPath);     //Create new Directory
 	}
 	
	 public void writeStringToFile(String str, String filePath){
		// Path that we need to create in HDFS. Just like Unix/Linux file systems, HDFS file system starts with "/"
		
		try{
			final Path path = new Path(filePath);
			init();
			OutputStream out = hdfs.create(path, new Progressable(){
				public void progress(){
				LOG.info(".");
			}});
			PrintWriter writer = new PrintWriter(out);
			writer.print(str);
			writer.close();
			out.close();
		}
		catch (Exception ex){
			LOG.logp(Level.SEVERE, StockTest.class.getName(), "writeStringToFile",ex.toString());
			
		}	
	}


}
