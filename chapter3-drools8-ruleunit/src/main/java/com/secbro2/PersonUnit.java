package com.secbro2;

import com.secbro2.entity.Person;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/15
 **/
public class PersonUnit implements RuleUnitData {

	private final DataStore<Person> persons;

	public PersonUnit(){
		this(DataSource.createStore());
	}

	public PersonUnit(DataStore<Person> persons){
		this.persons = persons;
	}

	public DataStore<Person> getPersons(){
		return persons;
	}

}
