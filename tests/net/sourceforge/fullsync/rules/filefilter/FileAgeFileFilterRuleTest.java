/*
 * Created on Jun 2, 2005
 */
package net.sourceforge.fullsync.rules.filefilter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.sourceforge.fullsync.SystemDate;
import junit.framework.TestCase;

/**
 * @author Michele Aiello
 */
public class FileAgeFileFilterRuleTest extends TestCase {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	protected void tearDown() throws Exception {
		super.tearDown();
		SystemDate.getInstance().setUseSystemTime();
	}
	
	public void testOpIs() throws ParseException {
		SystemDate.getInstance().setTimeSpeed(0);
		SystemDate.getInstance().setCurrent(dateFormat.parse("01/01/2005 10:00:01").getTime());
		FileAgeFileFilterRule filterRule = new FileAgeFileFilterRule(1000, FileAgeFileFilterRule.OP_IS);
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 10:00:00").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 10:00:01").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 09:00:00").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/02/2005 10:00:00").getTime())));
	}

	public void testOpIsnt() throws ParseException {
		SystemDate.getInstance().setTimeSpeed(0);
		SystemDate.getInstance().setCurrent(dateFormat.parse("01/01/2005 10:00:01").getTime());
		FileAgeFileFilterRule filterRule = new FileAgeFileFilterRule(1000, FileAgeFileFilterRule.OP_ISNT);
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 10:00:00").getTime())));
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 10:00:01").getTime())));
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 09:00:00").getTime())));
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/02/2005 10:00:00").getTime())));
	}

	public void testOpIsGreaterThan() throws ParseException {
		SystemDate.getInstance().setTimeSpeed(0);
		SystemDate.getInstance().setCurrent(dateFormat.parse("01/01/2005 10:00:00").getTime());
		FileAgeFileFilterRule filterRule = new FileAgeFileFilterRule(1000, FileAgeFileFilterRule.OP_IS_GREATER_THAN);
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 10:00:00").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 10:00:01").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 10:00:02").getTime())));
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("30/12/2004 09:00:00").getTime())));
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 01:00:00").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 11:00:00").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2006 09:00:00").getTime())));
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2004 09:00:00").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 09:59:59").getTime())));
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 09:59:58").getTime())));
	}

	public void testOpIsLessThan() throws ParseException {
		SystemDate.getInstance().setTimeSpeed(0);
		SystemDate.getInstance().setCurrent(dateFormat.parse("01/01/2005 10:00:00").getTime());
		FileAgeFileFilterRule filterRule = new FileAgeFileFilterRule(10000, FileAgeFileFilterRule.OP_IS_LESS_THAN);
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 10:00:00").getTime())));
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 09:59:57").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 09:59:50").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("30/12/2004 10:00:00").getTime())));
		assertFalse(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 09:00:00").getTime())));
		assertTrue(filterRule.match(new TestNode("foobar.txt", "/root/foobar.txt", true, false, 1024, dateFormat.parse("01/01/2005 11:00:00").getTime())));
	}
}