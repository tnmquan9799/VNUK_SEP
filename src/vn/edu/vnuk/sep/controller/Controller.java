package vn.edu.vnuk.sep.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import vn.edu.vnuk.sep.dao.PersonDao;
import vn.edu.vnuk.sep.model.Person;

public class Controller {
	
	public ArrayList<Person> getAllPersons(String keyword) throws SQLException {
		return (ArrayList<Person>) new PersonDao().read(keyword);
	}
	
}

