
package ior.parser.mvc;

public abstract class View
{
	protected Model m_model;
	
	// method name following the Smalltalk naming covention
	public void model( Model newModel )
	{
		m_model = newModel;
		m_model.view( this );
	}

	public void stopModel()
	{
		m_model.stopView( this );
	}

	// method name following the Smalltalk naming covention
	public abstract void update( UpdateMessage um );

	public void update( ExceptionMessage em )
	{
		this.update( (UpdateMessage)em );
	}
}

