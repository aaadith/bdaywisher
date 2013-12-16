package com.appspot.bdaywisher.datastore;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;


/*
 * @author Shravana Aadith <aaadith@gmail.com>
 */


public final class PMF {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	private PMF() {
	}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}