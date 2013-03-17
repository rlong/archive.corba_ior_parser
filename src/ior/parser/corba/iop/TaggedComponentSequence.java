
package ior.parser.corba.iop;


import ior.parser.corba.DecodeError;
import ior.parser.corba.ReadableStream;
import ior.parser.tui.PrettyPrinter;


public class TaggedComponentSequence 
{
    public static final String TYPE =
    "sequence<" + TaggedComponent.TYPE + ">";
    
    public TaggedComponent[] value;

    public TaggedComponentSequence()
    {
        value = new TaggedComponent[ 0 ];
    }
    
    public TaggedComponentSequence( ReadableStream rs ) throws DecodeError
    {
        rs.getTrace().goingToDecode( TYPE );
        
        int seqLength = rs.decodeUnsignedLong();
        
        value = new TaggedComponent[ seqLength ];
        for( int i = 0; i < seqLength; i++ )
        {
            value[ i ] = TaggedComponent.create( rs );
        }
            
        rs.getTrace().doneDecoding( TYPE );
    }

	public void prettyPrint( String varName, PrettyPrinter pp )
	{
		
		pp.addln( "TaggedComponentSequence : no op" );
	}
}
