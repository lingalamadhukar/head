/**
 
 * SavingsActionForm.java    version: xxx
 
 
 
 * Copyright (c) 2005-2006 Grameen Foundation USA
 
 * 1029 Vermont Avenue, NW, Suite 400, Washington DC 20005
 
 * All rights reserved.
 
 
 
 * Apache License 
 * Copyright (c) 2005-2006 Grameen Foundation USA 
 * 
 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 *
 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the 
 
 * License. 
 * 
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an explanation of the license 
 
 * and how it is applied. 
 
 *
 
 */

package org.mifos.application.accounts.savings.struts.actionforms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.mifos.application.accounts.business.AccountCustomFieldEntity;
import org.mifos.application.accounts.savings.util.helpers.SavingsConstants;
import org.mifos.application.accounts.struts.actionforms.AccountAppActionForm;
import org.mifos.application.productdefinition.business.SavingsOfferingBO;
import org.mifos.application.productdefinition.util.helpers.SavingsType;
import org.mifos.framework.exceptions.PageExpiredException;
import org.mifos.framework.util.helpers.Constants;
import org.mifos.framework.util.helpers.Money;
import org.mifos.framework.util.helpers.SessionUtils;
import org.mifos.framework.util.helpers.StringUtils;

public class SavingsActionForm extends AccountAppActionForm {
	private String recommendedAmount;

	public SavingsActionForm() {
		super();
	}

	public String getRecommendedAmount() {
		return recommendedAmount;
	}

	public void setRecommendedAmount(String recommendedAmount) {
		this.recommendedAmount = recommendedAmount;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		String method = request.getParameter("method");
		ActionErrors errors = null;
		request.setAttribute(Constants.CURRENTFLOWKEY, request
				.getParameter(Constants.CURRENTFLOWKEY));

		if (method != null && method.equals("getPrdOfferings")
				|| method.equals("create") || method.equals("edit")
				|| method.equals("update") || method.equals("get")
				|| method.equals("validate")) {
		} else {
			errors = new ActionErrors();
			errors.add(super.validate(mapping, request));
			if (method.equals("preview") || method.equals("editPreview")) {
				try {
					SavingsOfferingBO savingsOffering = (SavingsOfferingBO) SessionUtils
							.getAttribute(SavingsConstants.PRDOFFCERING,
									request);
					if (savingsOffering.getSavingsType().getId().equals(
							SavingsType.MANDATORY.getValue())
							&& getRecommendedAmntValue().equals(new Money())) {
						// check for mandatory amount
						errors.add(SavingsConstants.MANDATORY,
								new ActionMessage(SavingsConstants.MANDATORY,
										SavingsConstants.MANDATORY_AMOUNT));
					}
				} catch (PageExpiredException e) {
					errors.add(SavingsConstants.MANDATORY, new ActionMessage(
							SavingsConstants.MANDATORY,
							SavingsConstants.MANDATORY_AMOUNT));
				}
			}
		}

		if (null != errors && !errors.isEmpty()) {
			request.setAttribute(Globals.ERROR_KEY, errors);
			request.setAttribute("methodCalled", method);
		}
		return errors;
	}

	public double getRecommendedAmntDoubleValue() {
		return getRecommendedAmntValue().getAmountDoubleValue();
	}

	public Money getRecommendedAmntValue() {
		return getMoney(recommendedAmount);
	}

	private Money getMoney(String str) {
		return (StringUtils.isNullAndEmptySafe(str) && !str.trim().equals(".")) ? new Money(
				str)
				: new Money();
	}

	public void clear() {
		this.setAccountId(null);
		this.setSelectedPrdOfferingId(null);
		this.setAccountCustomFieldSet(new ArrayList<AccountCustomFieldEntity>());
	}
}
