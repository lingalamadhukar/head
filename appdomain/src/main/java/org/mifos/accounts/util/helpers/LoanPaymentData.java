/*
 * Copyright (c) 2005-2011 Grameen Foundation USA
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 */

package org.mifos.accounts.util.helpers;

import java.util.Map;

import org.mifos.accounts.business.AccountActionDateEntity;
import org.mifos.framework.util.helpers.Money;

public class LoanPaymentData extends AccountPaymentData {

    private Money principalPaid;

    private Money interestPaid;

    private Money penaltyPaid;

    private Money miscFeePaid;

    private Money miscPenaltyPaid;

    private Map<Short, Money> feesPaid;

    public LoanPaymentData(AccountActionDateEntity accountActionDate) {
        super(accountActionDate);
    }

}
