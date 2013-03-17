

package ior.parser.mvc;

public abstract class UpdateMessage
{
	private int m_updateType;

	public UpdateMessage( int updateType )
	{
		m_updateType = updateType;
	}

	public int getUpdateType()
	{
		return m_updateType;
	}
}

