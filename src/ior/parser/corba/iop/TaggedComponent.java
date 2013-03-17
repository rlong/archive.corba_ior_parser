
package ior.parser.corba.iop;

import ior.parser.corba.DecodeError;
import ior.parser.corba.ReadableStream;

public class TaggedComponent
{
    public static final String TYPE = "IOP::TaggedComponent";
	
    ComponentId tag;
    public byte[] component_data;

    private TaggedComponent( ComponentId id, ReadableStream rs ) 
		throws DecodeError
    {
        tag = id;
		component_data = new byte[ rs.getNumBytesLeft() ];
		for( int i = 0; i < component_data.length; i++ )
		{
			component_data[ i ] = rs.decodeOctet();
		}
    }

	public static TaggedComponent create( ReadableStream rs ) 
		throws DecodeError
	{
		TaggedComponent retVal = null;
        rs.getTrace().goingToDecode( TYPE );
		
		ComponentId id = new ComponentId( rs );
		retVal = new TaggedComponent( id, rs ); 

        rs.getTrace().doneDecoding( TYPE );
		return retVal;
	}

}

