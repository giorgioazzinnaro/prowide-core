/* 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
*/
package com.prowidesoftware.swift.io.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.prowidesoftware.swift.io.ConversionService;
import com.prowidesoftware.swift.model.SwiftMessage;

/**
 * MT940 tests
 * 
 * @author www.prowidesoftware.com
 * @since 4.0
 */
public class UnparsedTextParsingTest extends BaseMessageTestcase {
	
	@Test 
	public void test_1() {
	    String msg = "{1:F21XYZABCAAXXX1111112222}{4:{177:0011111111}{451:0}}{1:F21XYZABCAAXXXX1111112222}{2:O5691340110817LXLXXXXX4A1000002782131108171440N}{3:{108:MT569 011 OF 021}}{4:\n"
		    + ":35B:ISIN 123456ABCDEF\n"
		    + ":36B::SECV//UNIT/1,34\n"
		    + ":16S:SECDET\n"
		    + ":16S:VALDET\n"
		    + ":16S:TRANSDET\n"
		    + ":16S:SUMC\n"
		    + ":16S:SUME\n"
		    + ":16R:ADDINFO\n"
		    + ":19A::TCOP//USD123456789012,34\n"
		    + ":16S:ADDINFO\n"
		    + "-}{5:{CHK:15C62B525DAA}{TNG:}}{S:{SAC:}{COP:P}}"; 
	    
	    try {
			SwiftMessage m = (new SwiftParser()).parse(msg);
			SwiftMessage m569 = (new SwiftParser()).parse(m.getUnparsedTexts().getAsFINString());
	
			assertEquals("569", m569.getType());
			assertEquals("F21XYZABCAAXXXX1111112222", m569.getBlock1().getBlockValue());
			
			ConversionService service = new ConversionService(); 
			service.getXml(m.getUnparsedTexts().getAsFINString());
				
	    } catch (IOException ex) {
	    	fail(ex.getMessage());
	    }
		
	}
	
}
	
