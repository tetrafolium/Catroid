/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2018 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.content.actions;

import org.catrobat.catroid.formulaeditor.Formula;

public class AssertEqualsAction extends AssertAction {

	private Formula actualFormula = null;
	private Formula expectedFormula = null;

	@Override
	public boolean act(float delta) {
		assertTitle = "\nAssertEqualsError\n";

		if (actualFormula == null) {
			failWith("Actual is null");
			return false;
		}
		if (expectedFormula == null) {
			failWith("Expected is null");
			return false;
		}

		String actualValue = actualFormula.interpretObject(sprite).toString();
		String expectedValue = expectedFormula.interpretObject(sprite).toString();

		if (!equalValues(actualValue, expectedValue)) {
			failWith(formattedAssertEqualsError(actualValue, expectedValue));
			return false;
		}
		return true;
	}

	private String formattedAssertEqualsError(Object actual, Object expected) {
		String indicator = generateIndicator(actual, expected);
		return "expected: <" + expected + ">\nactual:   <" + actual + ">\ndeviation: " + indicator;
	}

	public void setActual(Formula actual) {
		this.actualFormula = actual;
	}

	public void setExpected(Formula expected) {
		this.expectedFormula = expected;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
