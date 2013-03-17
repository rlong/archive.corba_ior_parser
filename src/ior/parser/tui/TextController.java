
package ior.parser.tui;

import ior.parser.corba.iop.IORModel;
import ior.parser.mvc.Controller;
import ior.parser.mvc.Model;

public class TextController extends Controller
{
	IORModel m_model;

	private TextController() 
	{
	}

	public void model( Model newModel )
	{
		m_model = (IORModel)newModel;
	}

	public void setIOR( String stringifiedIOR )
	{
		m_model.setIOR( stringifiedIOR );
	}

	public static void main( String args[] )
	{
		if ( 1 != args.length )
		{
			System.out.println("Please pass the IOR as the only parameter" );
			System.exit( 1 );
		}
		TextController tc = new TextController();	
		IORModel model = new IORModel();
		TextView tv = new TextView();
		// model.view( tv );
		tv.model( model );
		tc.model( model );
		tc.setIOR( args[ 0 ] );

		System.exit( 0 );
	}
}


