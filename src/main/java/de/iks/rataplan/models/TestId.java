package de.iks.rataplan.models;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

public enum TestId {
	BACKEND_SHOW_REQUEST_50("empty request", "/migration/backend/appointmentrequest/show/request_50.xml");
	
	private String value;
	private String path;
	
	private TestId(String value, String path) {
		this.value = value;
		this.path = path;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public void setupData(DataSource dataSource) throws Exception {
		DataSourceDatabaseTester dbHandle = new DataSourceDatabaseTester(dataSource);
		
		DataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet dataSet = loader.load(this.path);
		
		dbHandle.setDataSet(dataSet);
		dbHandle.onSetup();
	}
}
