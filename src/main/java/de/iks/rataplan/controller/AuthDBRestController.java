package de.iks.rataplan.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.iks.rataplan.exceptions.DBHelperException;
import de.iks.rataplan.models.TestId;

@RestController
@RequestMapping("/e2e/auth")
public class AuthDBRestController {
	
	@Autowired
	@Qualifier("authDataSource")
	private DataSource authDataSource;
	
	@RequestMapping("/clear")
	public ResponseEntity<Boolean> clearDB() {
		boolean isEmpty = true;
		// Connection and preparedStatement are automatically closed by java after try block
		try (Connection con = authDataSource.getConnection();
				PreparedStatement ps = con.prepareStatement("TRUNCATE rataplanuser CASCADE;");
				) {
			isEmpty = ps.execute();
		} catch (SQLException e) {
			throw new DBHelperException("Error clearing db");
		}
		return new ResponseEntity<>(!isEmpty, HttpStatus.OK);
	}
	
	@RequestMapping("/setup/{testId}")
	public ResponseEntity<TestId> setUp(@PathVariable TestId testId) {
		
		try {
			testId.setupData(authDataSource);
		} catch (Exception e) {
			throw new DBHelperException("Error migrating Script: " + testId);
		}
		return new ResponseEntity<TestId>(testId, HttpStatus.OK);
	}
	
}
