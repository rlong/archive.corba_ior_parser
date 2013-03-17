
package ior.parser.corba.iop;


import ior.parser.corba.DecodeError;
import ior.parser.corba.ReadableStream;
import ior.parser.tui.PrettyPrinter;


public class TaggedProfileSequence
{
    public static final String TYPE = "sequence<" + 
    TaggedProfile.TYPE + ">";

    public TaggedProfile value[];

    public TaggedProfileSequence( ReadableStream rs ) throws DecodeError
    {
        rs.getTrace().goingToDecode( TYPE );
        
        int seqLength = rs.decodeUnsignedLong();

        value = new TaggedProfile[ seqLength ];
        
        for( int i = 0; i < seqLength; i++ )
        {
            value[ i ] = new TaggedProfile( rs );
        }
            
        rs.getTrace().doneDecoding( TYPE );

    }

	public String toString()
	{
		StringBuffer retVal = new StringBuffer();
		retVal.append( TYPE );
		retVal.append( "\n{\n" );
		for( int i = 0; i < value.length; i++ )
		{
			retVal.append( "[ " );
			retVal.append( i );
			retVal.append( " ] " );
			retVal.append( value[ i ] );
			retVal.append( '\n' );
		}
			
		retVal.append( "\n}");	
		return retVal.toString();
	}

	public void prettyPrint( String varName, PrettyPrinter pp )
	{
		pp.add( TaggedProfileSequence.TYPE );
		pp.add( " " );
		pp.addln( varName );
		pp.openScope();
		for( int i = 0; i < value.length; i++ )
		{
			String arrElemName = varName + "[ " + i + " ]";
			value[ i ].prettyPrint( arrElemName, pp );
		}
		pp.closeScope();
	}

}

