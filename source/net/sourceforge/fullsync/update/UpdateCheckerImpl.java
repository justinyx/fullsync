package net.sourceforge.fullsync.update;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author <a href="mailto:codewright@gmx.net">Jan Kopcsek</a>
 */
public class UpdateCheckerImpl implements UpdateChecker
{
    private static URL server;
    private Properties versions;
    
    public UpdateCheckerImpl()
    	throws MalformedURLException
    {
        server = new URL( "http://fullsync.sourceforge.net/modules.php" ); 
    }
	public Version getLatestVersion( UpdatableModule module ) 
		throws IOException
	{
	    if( versions == null )
	    {
	        versions = new Properties();
	        versions.load( server.openStream() );
	    }
	    return new Version( versions.getProperty( module.getName() ) );
	}
	public File downloadUpdate( UpdatableModule module )
		throws IOException
	{
	    URL query = new URL( server+"?module="+module.getName() );
	    BufferedReader conn = new BufferedReader( new InputStreamReader( query.openStream() ) );
	    Vector mirrors = new Vector();
	    String line;
	    while( (line = conn.readLine()) != null )
	    {
	        mirrors.add( line );
	    }
	    conn.close();
	    
	    
        File tmpFile = new File( "__update.zip" );
	    for( int i = 0; i < mirrors.size(); i++ )
	    {
	        try {
	            URL url = new URL( (String)mirrors.get( i ) );
	            InputStream in = url.openStream();
	            OutputStream out = new FileOutputStream( tmpFile );
	            
	            int b = 0;
	            while( (b = in.read()) >= 0 )
	                out.write( b );
	            
	            out.close();
	            in.close();
	        } catch( Exception ex ) {
	            
	        }
	    }
	    
	    return tmpFile;
	}
	public boolean installUpdate( File file )
		throws IOException
	{
	    File basedir = new File(".");
	    ZipFile zip = new ZipFile( file );
	    Enumeration e = zip.entries();
	    while( e.hasMoreElements() )
	    {
	        ZipEntry entry = (ZipEntry)e.nextElement();
	        File f = new File( entry.getName() );
	        if( !entry.isDirectory() )
	        {
	            if( f.getParentFile() != null )
	                f.getParentFile().mkdirs();
	            InputStream in = zip.getInputStream( entry );
	            OutputStream out = new FileOutputStream( f );
	            
	            int b = 0;
	            while( (b = in.read()) >= 0 )
	                out.write( b );
	            
	            out.close();
	            in.close();
	        }
	    }
	    zip.close();
	    return true;
	}
	
	public static void main( String[] args )
		throws Exception
    {
	    UpdatableModule m = new UpdatableModule() {
	        public String getName()
	        {
	            return "FullSync";
	        }
	        public Version getVersion()
	        {
	            return null;
	        }
	    };
	    
        UpdateChecker checker = new UpdateCheckerImpl();
        System.out.println( "Latest Version: " + checker.getLatestVersion( m ) );
        File f = checker.downloadUpdate( m );
        checker.installUpdate( f );
        f.delete();
    }
}