
package ior.parser.mvc;

public abstract class ExceptionMessage extends UpdateMessage
{
	Exception m_e;

	public ExceptionMessage( int updateType, Exception e )
	{
		super( updateType );
		m_e = e;
	}

	public String toString()
	{
		return m_e.toString();
	}

	public Exception getException()
	{
		return m_e;		
	}

}

