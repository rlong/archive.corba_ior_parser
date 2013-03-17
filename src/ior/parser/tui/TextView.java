//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.tui;

import ior.parser.corba.iop.IORMessageTypes;
import ior.parser.mvc.ExceptionMessage;
import ior.parser.mvc.UpdateMessage;
import ior.parser.mvc.View;


public class TextView extends View
{
	public void update( UpdateMessage um )	
	{
		if ( IORMessageTypes.IOR_SET == um.getUpdateType() )
		{
			System.out.println( m_model );
		}
	}

	public void update( ExceptionMessage em )
	{
		System.out.println( em );
	}
}

