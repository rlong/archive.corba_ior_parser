//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.gui.awt.java1;

import java.awt.Choice;

import ior.parser.corba.Endian;

public class EndianChoice extends Choice
{

	public EndianChoice( Endian endianValue )
	{
		// netscape 4.05 does not like this.add(String)
		if( endianValue == Endian.BIG )
		{
			this.addItem( Endian.BIG.toString() ); 
		}
		else
		{
			this.addItem( Endian.LITTLE.toString() );
		}
	}

}

