//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.gui.awt.java1;

import java.awt.Choice;

import ior.parser.corba.iiop.Version;

public class VersionChoice extends Choice
{

	private static final String VERSION_11 = "1.1";
	private static final String VERSION_10 = "1.0";

	public VersionChoice( Version ver )
	{
		if( 0 == ver.minor )
		{
			this.addItem( VERSION_10 );
		}
		else
		{
			this.addItem( VERSION_11 );
		}

	}

}

