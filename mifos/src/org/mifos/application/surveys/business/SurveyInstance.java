package org.mifos.application.surveys.business;

import java.util.Date;
import java.util.Set;

import org.mifos.application.accounts.business.AccountBO;
import org.mifos.application.customer.business.CustomerBO;
import org.mifos.application.personnel.business.PersonnelBO;
import org.mifos.application.surveys.helpers.InstanceStatus;
import org.mifos.framework.business.PersistentObject;

public class SurveyInstance extends PersistentObject {
	
	private int instanceId;
	
	private Survey survey;
	
	private Set<SurveyResponse> surveyResponses;
	
	private CustomerBO customer;
	
	private AccountBO account;
	
	private PersonnelBO officer;
	
	private PersonnelBO creator;
	
	private Date dateConducted;
	
	private InstanceStatus completedStatus;
	
	public SurveyInstance() {
		completedStatus = InstanceStatus.INCOMPLETE;
	}

	public int getCompletedStatus() {
		return completedStatus.getValue();
	}
	
	public InstanceStatus getCompletedStatusAsEnum() {
		return completedStatus;
	}

	public void setCompletedStatus(int completedStatus) {
		this.completedStatus = InstanceStatus.fromInt(completedStatus);
	}
	
	public void setCompletedStatus(InstanceStatus completedStatus) {
		this.completedStatus = completedStatus;
	}

	public Date getDateConducted() {
		return dateConducted;
	}

	public void setDateConducted(Date dateConducted) {
		this.dateConducted = dateConducted;
	}

	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	public PersonnelBO getOfficer() {
		return officer;
	}

	public void setOfficer(PersonnelBO officer) {
		this.officer = officer;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public CustomerBO getCustomer() {
		return customer;
	}
	
	/*note that a survey instance must be associated with either a client
	 * or an account, not both... we could include a check against the survey 
	 * type here, but that would cause needless errors when you set the client/account
	 * before the survey
	*/
	public void setCustomer(CustomerBO customer) {
		this.customer = customer;
		this.account = null;
	}

	public AccountBO getAccount() {
		return account;
	}

	public void setAccount(AccountBO account) {
		this.account = account;
		this.customer = null;
	}

	public PersonnelBO getCreator() {
		return creator;
	}

	public void setCreator(PersonnelBO creator) {
		this.creator = creator;
	}

	public void setSurveyResponses(Set<SurveyResponse> surveyResponses) {
		this.surveyResponses = surveyResponses;
	}

	public Set<SurveyResponse> getSurveyResponses() {
		return surveyResponses;
	}
}
