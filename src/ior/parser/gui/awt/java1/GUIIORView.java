//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.gui.awt.java1;

import ior.parser.corba.DecodeError;
import ior.parser.corba.iop.IORExceptionMessage;
import ior.parser.corba.iop.IORMessageTypes;
import ior.parser.corba.iop.IORModel;
import ior.parser.mvc.ExceptionMessage;
import ior.parser.mvc.UpdateMessage;
import ior.parser.mvc.View;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class GUIIORView extends View
{
	IORPanel m_iorPanel;

	public GUIIORView( IORPanel iorPanel )
	{
		m_iorPanel = iorPanel;
	}

	public void update( UpdateMessage um )	
	{
		if( IORMessageTypes.IOR_UNSET == um.getUpdateType() )
		{
			m_iorPanel.clear();
		}
		else if ( IORMessageTypes.IOR_SET == um.getUpdateType() )
		{
			// don't really like this, rlong
			if( m_model instanceof IORModel ) 
			{
			
				IORModel model = (IORModel)m_model;

				m_iorPanel.update( model );
			}
		}
	}


	public void update( ExceptionMessage em )
	{
		// still don't really like this, rlong
		if( em instanceof IORExceptionMessage )
		{
			IORExceptionMessage iorEm = (IORExceptionMessage)em;
			// we know the iorEm is a holder for DecodeError exceptions
			DecodeError de = (DecodeError)em.getException();
			new IORParseErrorDialog( de );
		}
	}
}
