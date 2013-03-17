
package ior.parser.tui;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


public class PrettyPrinter
{
	private static int ms_bytesPerLine = 12;
	private static char[] ms_hexNums = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private Vector m_buffer;
	private boolean m_onNewLine;
    
	private static StringBuffer[] ms_indents;
	private static final StringBuffer OPEN_BRACE = new StringBuffer( "{" );
	private static final StringBuffer CLOSE_BRACE = new StringBuffer( "}" );
	private static final StringBuffer NEW_LINE = new StringBuffer( "\n" );

	private int m_indent;

	static
	{
		StringBuffer whiteSpace = new StringBuffer();
		ms_indents = new StringBuffer[ 20 ];
		for ( int i = 0; i < ms_indents.length; i++ )
		{
			ms_indents[ i ] = whiteSpace;
			whiteSpace = new StringBuffer( whiteSpace.toString() );
			whiteSpace.append( "  " );
		}
	}

	public PrettyPrinter()
	{
		m_buffer = new Vector();
		m_onNewLine = true;
		m_indent = 0;
	}
	

	public void openScope()
	{
		this.addln( OPEN_BRACE );
		m_indent++;
	}

	public void closeScope()
	{
		if(  m_indent > 0 )
		{
			m_indent--;
		}
		this.addln( CLOSE_BRACE );
	}
    
    
	private void addByte( byte b )
	{
		StringBuffer sb = new StringBuffer();
		int offset = b;
		if ( 0 > offset ) // account for 2's complement
		{
			offset *= -1;
		}
		sb.append( ms_hexNums[ offset >> 4 ] ); // shift the offset to the right
		sb.append( ms_hexNums[ offset & 0xf ] ); // mask off the high bits
		this.add( sb );
	}

	private void addBytes( byte[] byteSeq, int index )
	{
		int i = index;
		int targetIndex = index + ms_bytesPerLine;
		while( i < byteSeq.length && i < targetIndex )
		{
			this.addByte( byteSeq[ i++ ] );
			this.add( " " );
		}
		while( i++ < targetIndex ) // account for the last line
		{
			this.add( "   " );
		}
	}

	private void addChars( byte[] byteSeq, int index )
	{
		int i = index;
		StringBuffer sb = new StringBuffer();
		int targetIndex = index + ms_bytesPerLine;
		while( i < byteSeq.length && i < targetIndex )
		{
			char c = (char)byteSeq[ i++ ];
			if( '!' <= c && '~' >= c )
			{
				sb.append( c );
			}
			else
			{
				sb.append( '.' );
			}
		}
		this.add( sb );
	}

	public void addln( byte[] byteSeq )
	{
		StringBuffer sb = new StringBuffer();
		for( int i = 0; i < byteSeq.length; i+= ms_bytesPerLine )
		{
			this.addBytes( byteSeq, i );
			this.add( " | " );
			this.addChars( byteSeq, i );
			this.addln( "" ); // force a new line
		}
	}

	public void addln( String str )
	{
		this.addln( new StringBuffer( str ) );
	}

	public void add( Object obj )
	{
		this.add( obj.toString() );
	}
    
	public void add( String str )
	{
		this.add( new StringBuffer( str ) );
	}

	public void add( StringBuffer sb )
	{
		if ( m_onNewLine )
        {
			m_buffer.addElement( ms_indents[ m_indent ] );
        }
		m_buffer.addElement( sb );
		m_onNewLine = false;
	}

	public void addln( StringBuffer sb )
	{
		this.add( sb );
		this.add( NEW_LINE );
		m_onNewLine = true;
	}

	public String toString()
	{
		StringBuffer retVal = new StringBuffer();
		for( Enumeration e = m_buffer.elements(); e.hasMoreElements(); )
		{
			StringBuffer sb = (StringBuffer)e.nextElement();
			retVal.append( sb );
		}
		return retVal.toString();	
	}

}

