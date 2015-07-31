package com.facerecognition.common.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.facerecognition.common.util.FalseMatchRateManager;

public class FalseMatchRateManagerTest {

	private FalseMatchRateManager manager;

	@Before
	public void setUp() throws Exception {
		manager = new FalseMatchRateManager(100);
	}

	@Test
	public void initialIsEmpty() {
		Assert.assertEquals(true, manager.getFalseMatchMap().isEmpty());
	}

	@Test
	public void testAddObject() {
		manager.addFalseMatchRate("Karl");
		Map<String, Integer> map = manager.getFalseMatchMap();
		Assert.assertEquals(false, map.isEmpty());
		Assert.assertEquals(new Integer(1), map.get("Karl"));
	}

	@Test
	public void testAddObjects() {
		manager.addFalseMatchRate("Karl");
		manager.addFalseMatchRate("Jonatan");
		manager.addFalseMatchRate("Jonatan");
		manager.addFalseMatchRate("Sebastian");
		Map<String, Integer> map = manager.getFalseMatchMap();
		Assert.assertEquals(false, map.isEmpty());
		Assert.assertEquals(new Integer(1), map.get("Karl"));
		Assert.assertEquals(new Integer(2), map.get("Jonatan"));
		Assert.assertEquals(new Integer(1), map.get("Sebastian"));
	}
	
	@Test
	public void testFMRPercentage(){
		manager.addFalseMatchRate("Karl");
		Assert.assertEquals(new Double(1.0), new Double(manager.getFalseMatchRate()));
	}
	
	@Test
	public void testFMRPercentage2(){
		manager.addFalseMatchRate("Karl");
		manager.addFalseMatchRate("Sebastian");
		Assert.assertEquals(new Double(2.0), new Double(manager.getFalseMatchRate()));
	}
	@Test
	public void testFMRPercentage3(){
		manager.addFalseMatchRate("Karl");
		manager.addFalseMatchRate("Karl");
		manager.addFalseMatchRate("Jonatan");
		Assert.assertEquals(new Double(3.0), new Double(manager.getFalseMatchRate()));
	}
}
