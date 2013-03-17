
package ior.parser.corba.iiop;

import ior.parser.corba.DecodeError;
import ior.parser.corba.ReadableStream;
import ior.parser.corba.Types;
import ior.parser.tui.PrettyPrinter;


public class Version
{
    public static final String TYPE = "IIOP::Version";
    public byte major;
    public byte minor;

    public Version( ReadableStream rs ) throws DecodeError
    {     
        rs.getTrace().goingToDecode( TYPE );
        
        major = rs.decodeOctet();
        minor = rs.decodeOctet();

        if( major != 1 )
        {
            String errMessage = "Expected major component to be 1" +
                ", instead read " + major;
            throw new DecodeError( DecodeError.INVALID_DATA, errMessage );
        }
        if( minor != 0 && minor != 1 )
        {
            String errMessage = "Expected minor component to be 1 or 0" +
                ", instead read " + minor;
            throw new DecodeError( DecodeError.INVALID_DATA, errMessage );
        }
        
        rs.getTrace().doneDecoding( TYPE );
   
    }


	public void prettyPrint( String varName, PrettyPrinter pp )
	{
		pp.add( TYPE );
		pp.add( " " );
		pp.addln( varName );
		pp.openScope();
		pp.addln( Types.OCTET_TYPE + " major = " + major );
		pp.addln( Types.OCTET_TYPE + " minor = " + minor );
		pp.closeScope();
	}
    
}
