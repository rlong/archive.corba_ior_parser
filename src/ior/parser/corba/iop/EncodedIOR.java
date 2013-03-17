//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.corba.iop;

import ior.parser.corba.DecodeError;
import ior.parser.corba.Endian;
import ior.parser.corba.ReadableStream;

public class EncodedIOR
{
     
    public static final String MAGIC_PREFIX = "IOR:";

    public IOR m_ior;
    public Endian m_endian;

    public  EncodedIOR( String stringifiedIOR ) throws DecodeError
    {
        
        validateStringifiedIOR( stringifiedIOR );

        byte encapsulatedData[] = new byte[ ( stringifiedIOR.length() - 
                                              MAGIC_PREFIX.length() ) / 2 ];

        for( int i = MAGIC_PREFIX.length(), j = 0; i < 
                 stringifiedIOR.length(); i+=2, j++ )
        {
            encapsulatedData[ j ] = HexOctet.decodeOctet( 
                stringifiedIOR.charAt( i ), 
                stringifiedIOR.charAt( i + 1 ) );
        }
        ReadableStream rs = new ReadableStream( encapsulatedData );
        m_endian = rs.getEndian();
        m_ior = new IOR( rs );
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

}
