//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.corba;

public class Endian
{

    private static final boolean m_big = false;
    public static final Endian BIG = new Endian();
    
    private static final boolean m_little= true;
    public static final Endian LITTLE = new Endian();
    
    public static Endian lookup( boolean endian )
    {
        if ( endian )
        {
            return LITTLE;
        }
        return BIG;
    }
    
    private Endian()
    {
    }

	public String toString()
	{
		if ( this == BIG )
		{
			return "big endian";
		}
		return "little endian";
	}
}
