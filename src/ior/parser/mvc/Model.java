
package ior.parser.mvc;

import java.util.Vector;
import java.util.Enumeration;

public class Model
{
	private Vector m_viewers;

	public Model()
	{
		m_viewers = new Vector();
	}

	// package level protection deliberately
	// only called from the View
	void view( View newView )
	{
		m_viewers.addElement( newView );
	}

	// package level protection deliberately
	// only called from the View
	void stopView( View oldView )
	{
		m_viewers.removeElement( oldView );
	}

	// method name following the Smalltalk naming covention
	protected void changed( UpdateMessage um )
	{
		for( Enumeration e = m_viewers.elements(); e.hasMoreElements(); )
		{
			View v = (View)e.nextElement();
			v.update( um );
		}
	}

	protected void changed( ExceptionMessage em )
	{
		for( Enumeration e = m_viewers.elements(); e.hasMoreElements(); )
		{
			View v = (View)e.nextElement();
			v.update( em );
		}
	}
	
}


