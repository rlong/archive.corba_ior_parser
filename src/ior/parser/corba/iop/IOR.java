//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.corba.iop;

import ior.parser.corba.DecodeError;
import ior.parser.corba.ReadableStream;


public class IOR
{
    public static final String TYPE = "IOP::IOR";
    
    public String type_id;
    public TaggedProfileSequence profiles;
    
    public  IOR( ReadableStream rs)	throws DecodeError
    {
        rs.getTrace().goingToDecode( TYPE );
        
        type_id = rs.decodeString();
        profiles = new TaggedProfileSequence( rs );
        
        rs.getTrace().doneDecoding( TYPE );
        
    }

	public String toString()
	{
		return TYPE + "\n{\n" +
			"type_id : " + type_id + '\n' +
			"profiles : " + profiles.toString()  + "\n}" ;
	}

}

