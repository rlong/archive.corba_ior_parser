
package ior.parser.corba.iop;

import ior.parser.corba.DecodeError;
import ior.parser.corba.Endian;
import ior.parser.corba.ReadableStream;
import ior.parser.mvc.Model;
import ior.parser.tui.PrettyPrinter;



public class IORModel extends Model
{
	public static final String MAGIC_PREFIX = "IOR:";

	private String m_stringifiedIOR;
	private IOR m_ior = null;
	private Endian m_endian = null;
	
	// pre-requisite : stringifiedIOR != null
	public void setIOR( String stringifiedIOR )
	{
		try
		{
			this.unsetIOR();

			this.validateStringifiedIOR( stringifiedIOR );

			ReadableStream rs = this.decodeString( stringifiedIOR );

			m_endian = rs.getEndian();
			m_ior = new IOR( rs );

			// can update m_stringifiedIOR as all looks well with IOR passed in
			m_stringifiedIOR = stringifiedIOR;
		}
		catch ( DecodeError de )
		{
			this.changed( new IORExceptionMessage( de ) );	
			return;
		}
	this.changed( new IORUpdateMessage( IORMessageTypes.IOR_SET ) );
	}

	public void unsetIOR()
	{
		m_stringifiedIOR = null;
		m_ior = null;
		m_endian = null;
		this.changed( new IORUpdateMessage( IORMessageTypes.IOR_UNSET ) );
	}

	private static void validateStringifiedIOR( String stringifiedIOR )
		throws DecodeError
	{
		if ( ! stringifiedIOR.startsWith( MAGIC_PREFIX ) )
		{
			int magicLength = MAGIC_PREFIX.length();
			if( stringifiedIOR.length() < magicLength )
			{
				magicLength = stringifiedIOR.length();
			}
			String errString = "Expected \"" + MAGIC_PREFIX + "\", got \"" +
				stringifiedIOR.substring( 0, magicLength ) +
				"\".";
			throw new DecodeError( DecodeError.INVALID_STRING, errString );
		}

		if ( 0 != ( stringifiedIOR.length() % 2 ) )
		{
			String errString = "Stringified IOR should have an even length" +
				", length of stringified IOR passed in was " +
				stringifiedIOR.length();
			throw new DecodeError( DecodeError.INVALID_STRING, errString );
		}
	}

	ReadableStream decodeString( String stringifiedIOR )
		throws DecodeError
	{
		byte encapsulatedData[] = 
			new byte[ ( stringifiedIOR.length() - MAGIC_PREFIX.length() ) 
				/ 2 ];

		for( int i = MAGIC_PREFIX.length(), j = 0; i <
			stringifiedIOR.length(); i+=2, j++ )
		{
			encapsulatedData[ j ] = HexOctet.decodeOctet(
				stringifiedIOR.charAt( i ),
				stringifiedIOR.charAt( i + 1 ) );
		}

		ReadableStream retVal = new ReadableStream( encapsulatedData );
		return retVal;
	}

	public Endian getEndian()
	{
		return m_endian;
	}

	public IOR getIOR()
	{
		return m_ior;
	}

	public String toString()
	{
		PrettyPrinter pp = new PrettyPrinter();

		pp.addln( "Encoding : " + m_endian );

		pp.addln( IOR.TYPE );
		pp.openScope();
		pp.addln( "string type_id = \"" + m_ior.type_id + "\"" );
		m_ior.profiles.prettyPrint( "profiles", pp );		

		pp.closeScope();

		return pp.toString();
	}

}

