
package ior.parser.gui.awt.java1;

import ior.parser.corba.iop.IORModel;
import ior.parser.mvc.Controller;
import ior.parser.mvc.Model;

public class GUIIORController extends Controller
{
	private IORModel m_model;

	public GUIIORController( IORModel model )
	{
		m_model = model;	
	}

	public void model( Model newModel )
	{
		m_model = (IORModel)newModel;
	}
	
	public void setIOR( String stringifiedIOR )
	{
		m_model.setIOR( stringifiedIOR.trim() );
	}

	public void unsetIOR()
	{
		m_model.unsetIOR();
	}
}

