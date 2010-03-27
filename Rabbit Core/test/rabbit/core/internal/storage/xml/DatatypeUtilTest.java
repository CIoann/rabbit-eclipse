/*
 * Copyright 2010 The Rabbit Eclipse Plug-in Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rabbit.core.internal.storage.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static rabbit.core.internal.storage.xml.DatatypeUtil.toXMLGregorianCalendarDate;

import java.util.Calendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

/**
 * Test for {@link DatatypeUtil}
 */
public class DatatypeUtilTest {

	@Test
	public void testToXMLGregorianCalendarDate() {

		Calendar cal = Calendar.getInstance();
		XMLGregorianCalendar xmlCal = toXMLGregorianCalendarDate(cal);

		assertEquals(cal.get(Calendar.YEAR), xmlCal.getYear());
		// Calendar.MONTH is zero based.
		assertEquals(cal.get(Calendar.MONTH) + 1, xmlCal.getMonth());
		assertEquals(cal.get(Calendar.DAY_OF_MONTH), xmlCal.getDay());
	}

	@Test
	public void testIsSameDate() {

		try {
			Calendar cal = Calendar.getInstance();

			XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance()
					.newXMLGregorianCalendarDate(1, 1, 1, 1);
			assertFalse(DatatypeUtil.isSameDate(cal, xmlCal));

			xmlCal = toXMLGregorianCalendarDate(cal);
			assertTrue(DatatypeUtil.isSameDate(cal, xmlCal));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testIsSameMonthInYear() throws DatatypeConfigurationException {

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		assertTrue(DatatypeUtil.isSameMonthInYear(cal1, cal2));

		cal2.add(Calendar.MONTH, 1);
		assertFalse(DatatypeUtil.isSameMonthInYear(cal1, cal2));
	}
}