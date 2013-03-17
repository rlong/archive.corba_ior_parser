//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.corba.iop;

import ior.parser.corba.DecodeError;
import ior.parser.corba.ReadableStream;
import ior.parser.tui.PrettyPrinter;


public class ProfileId
{
    public static final String TYPE = "IOP::ProfileId";
    
    public static final int TAG_INTERNET_IOP = 0;
    public static final String TAG_INTERNET_IOP_NAME = "IOP::TAG_INTERNET_IOP";
    
    public static final int TAG_MULTIPLE_COMPONENTS = 1;
    public static final String TAG_MULTIPLE_COMPONENTS_NAME = 
		"IOP::TAG_MULTIPLE_COMPONENTS";

    public int value;
    
    public ProfileId( ReadableStream rs ) throws DecodeError
    {
        rs.getTrace().goingToDecode( TYPE );
        
        value = rs.decodeUnsignedLong();
        if( value != TAG_INTERNET_IOP && value != TAG_MULTIPLE_COMPONENTS )
        {
            String errMessage = "Expected 0 (" + TAG_INTERNET_IOP_NAME
                + " ) or " + "1 (" + TAG_MULTIPLE_COMPONENTS_NAME
                + "), instead read 0x" + Long.toHexString( value );
            
            throw new DecodeError( DecodeError.INVALID_DATA, errMessage,
                                   rs.getTrace() );
        }
        
        rs.getTrace().doneDecoding( TYPE );
    }

	public String getProfileName()
	{
		if ( value == TAG_INTERNET_IOP )
		{
			return TAG_INTERNET_IOP_NAME;
		}
		return TAG_MULTIPLE_COMPONENTS_NAME;
	}

	public void prettyPrint( String varName, PrettyPrinter pp )
	{
		pp.addln( TYPE + " " + varName + " = " + this.getProfileName() ); 
	}
}

