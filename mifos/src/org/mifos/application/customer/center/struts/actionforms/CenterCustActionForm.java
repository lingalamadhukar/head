/**

* CenterActionForm    version: 1.0



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
package org.mifos.application.customer.center.struts.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.mifos.application.customer.struts.actionforms.CustomerActionForm;
import org.mifos.application.util.helpers.EntityType;
import org.mifos.application.util.helpers.Methods;
import org.mifos.framework.exceptions.ApplicationException;

public class CenterCustActionForm extends CustomerActionForm{
	
	@Override
	protected ActionErrors validateFields(HttpServletRequest request, String method) throws ApplicationException{
		ActionErrors errors = new ActionErrors();
		if(method.equals(Methods.preview.toString())){		
			validateName(errors);
			validateLO(errors);
			validateMeeting(request, errors);
			validateConfigurableMandatoryFields(request,errors,EntityType.CENTER);
			validateCustomFields(request,errors);
			validateFees(request, errors);
		}else if (method.equals(Methods.editPreview.toString())){
			validateLO(errors);
			validateConfigurableMandatoryFields(request,errors,EntityType.CENTER);
			validateCustomFields(request,errors);
		}
		return errors;
	}
}
