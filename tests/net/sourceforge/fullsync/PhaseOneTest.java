package net.sourceforge.fullsync;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Hashtable;

import junit.framework.TestCase;
import net.sourceforge.fullsync.impl.AdvancedRuleSetDescriptor;
import net.sourceforge.fullsync.impl.TaskGeneratorImpl;

/**
 * @author <a href="mailto:codewright@gmx.net">Jan Kopcsek</a>
 */
public class PhaseOneTest extends TestCase
{
    private File testingDir;
    private File testingSource;
    private File testingDestination;
    
    private TaskGenerator processor;
    private Profile profile;
    
    protected void setUp() throws Exception
    {
        testingDir = new File( "testing" );
        testingSource = new File( testingDir, "source" );
        testingDestination = new File( testingDir, "destination" );
        
        testingDir.mkdirs();
        testingSource.mkdir();
        testingDestination.mkdir();
        
        processor = new TaskGeneratorImpl();
        profile = new Profile();
        profile.setName( "TestProfile" );
        profile.setSource( new ConnectionDescription( testingSource.toURI().toString(), "" ) );
        profile.setDestination( new ConnectionDescription( testingDestination.toURI().toString(), "" ) );
        profile.setRuleSet( new AdvancedRuleSetDescriptor( "UPLOAD" ) );
        profile.setSynchronizationType( "Publish/Update" );
        
        clearUp();

        super.setUp();
    }
    
    protected void tearDown() throws Exception
    {
        clearUp();
        testingSource.delete();
        testingDestination.delete();
        testingDir.delete();
        
        super.tearDown();
    }
    
    protected void clearDirectory( File dir )
    {
        File[] files = dir.listFiles();
        for( int i = 0; i < files.length; i++ )
        {
            if( files[i].isDirectory() )
                clearDirectory( files[i] );
            files[i].delete();
        }
    }
    protected void clearUp()
    	throws IOException
    {
        clearDirectory( testingSource );
        clearDirectory( testingDestination );
    }
    protected void createRuleFile()
    	throws IOException
    {
        createNewFileWithContents( 
                testingSource, 
                ".syncrules", 
                new Date().getTime(),
                 "START RULESET UPLOAD\n"
            	+"	USE RULEFILES SOURCE\n"
            	+"	USE DIRECTION DESTINATION\n"
            	+"	USE RECURSION YES\n"
            	+"	USE RECURSIONONIGNORE YES\n"
                +"\n"
            	+"	APPLY IGNORERULES YES\n"
            	+"	APPLY TAKERULES YES\n"
            	+"	APPLY DELETION DESTINATION\n"
                +"\n"
            	+"	DEFINE IGNORE \"^[.].+\"\n"
            	+"	DEFINE SYNC \"length != length\"\n"
            	+"	DEFINE SYNC \"date != date\"\n"
            	+"END RULESET UPLOAD" );
    }
    
    protected PrintStream createNewFile( File dir, String filename )
    	throws IOException
    {
        File file = new File( dir, filename );
        file.createNewFile();
        PrintStream out = new PrintStream( new FileOutputStream( file ) );
        return out;
    }
    protected void createNewFileWithContents( File dir, String filename, long lm, String content )
    	throws IOException
    {
        PrintStream out = createNewFile( dir, filename );
        out.print( content );
        out.close();
        
        new File( dir, filename ).setLastModified( lm );
    }
    protected void assertPhaseOneActions( final Hashtable expectation )
    	throws Exception
    {
        TaskGenerationListener list = new TaskGenerationListener() {
            public void taskGenerationFinished( Task task )
            {
                Object ex = expectation.get( task.getSource().getName() );
                assertNotNull( "Unexpected generated Task for file: "+task.getSource().getName(), ex );
                assertTrue( "Action was "+task.getCurrentAction()+", expected: "+ex+" for File "+task.getSource().getName(), 
                        task.getCurrentAction().equalsExceptExplanation((Action)ex) );
            }
            public void taskGenerationStarted(
                    net.sourceforge.fullsync.fs.File source,
                    net.sourceforge.fullsync.fs.File destination )
            {}
            public void taskTreeFinished( TaskTree tree ) {}
            public void taskTreeStarted( TaskTree tree ) {}
        };
        
        processor.addTaskGenerationListener( list ); 
        TaskTree tree = processor.execute( profile );
        processor.removeTaskGenerationListener( list );
    }
    
    public void testSingleInSync()
    	throws Exception
    {
        createRuleFile();
        long lm = new Date().getTime();
        
        createNewFileWithContents( testingSource, "sourceFile1.txt", lm, "this is a test\ncontent1" );
        createNewFileWithContents( testingDestination, "sourceFile1.txt", lm, "this is a test\ncontent1" );
        
        Hashtable expectation = new Hashtable();
        expectation.put( "sourceFile1.txt", new Action( Action.Nothing, Location.None, BufferUpdate.None, "" ) );
        assertPhaseOneActions( expectation );
    }
    public void testSingleFileChange()
        throws Exception
    {
        createRuleFile();
        long lm = new Date().getTime();
        
        createNewFileWithContents( testingSource, "sourceFile1.txt", lm, "this is a test\ncontent2" );
        createNewFileWithContents( testingDestination, "sourceFile1.txt", lm, "this is a test\ncontent2 bla" );
        createNewFileWithContents( testingSource, "sourceFile2.txt", lm+3600, "this is a test\ncontent2" );
        createNewFileWithContents( testingDestination, "sourceFile2.txt", lm, "this is a test\ncontent2" );
        
        Hashtable expectation = new Hashtable();
        expectation.put( "sourceFile1.txt", new Action( Action.Update, Location.Destination, BufferUpdate.Destination, "" ) );
        expectation.put( "sourceFile2.txt", new Action( Action.Update, Location.Destination, BufferUpdate.Destination, "" ) );
        assertPhaseOneActions( expectation );
    }
    public void testSingleFileChangeLm()
    {
        /*        
        createNewFileWithContents( testingSource, "sourceFile3.txt", "this is a test\ncontent2" );
        createNewFileWithContents( testingSource, "sourceFile3.txt", "this is a test\ncontent2" );

        createNewFileWithContents( testingSource, "sourceFile4.txt", "this is a test\ncontent2" );
        createNewFileWithContents( testingSource, "sourceFile4.txt", "this is a test\ncontent2" );

        TaskTree tree = processor.execute( profile );
        
        assertEquals( tree.getTaskCount(), 2 );
        assertEquals( tree.getSource(), profile.getSource() );
        assertEquals( tree.getDestination(), profile.getDestination() );
        */
    }
}
