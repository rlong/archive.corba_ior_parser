//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.corba.iiop;


import ior.parser.corba.DecodeError;
import ior.parser.corba.ReadableStream;
import ior.parser.corba.Types;
import ior.parser.corba.iop.TaggedComponentSequence;
import ior.parser.tui.PrettyPrinter;



public class ProfileBody 
{
    public static final String TYPE_1_0 = "IIOP::ProfileBody_1_0";
    public static final String TYPE_1_1 = "IIOP::ProfileBody_1_1";
	private String m_type = null;

    public Version iiop_version;
    public String host;
    public int port;
    public byte[] object_key;
    public TaggedComponentSequence components;
    
    public ProfileBody( ReadableStream rs ) throws DecodeError
    {
        m_type = "IIOP::ProfileBody_1_?";
        
        rs.getTrace().goingToDecode( m_type );
        iiop_version = new Version( rs );
        rs.getTrace().doneDecoding( m_type );
        
        
        if(  iiop_version.minor == 0 )
        {
            m_type = TYPE_1_0;
        }
        else
        {
            m_type = TYPE_1_1;
        }
        rs.getTrace().goingToDecode( m_type );
        
        host = rs.decodeString();
        
        port = rs.decodeUnsignedShort();
        object_key = rs.decodeOctetSequence();

        if ( m_type == TYPE_1_0 )
        {
            components = new TaggedComponentSequence();
        }
        else
        {
            components = new TaggedComponentSequence( rs );
        }
        
        rs.getTrace().doneDecoding( m_type );
        
    }
	
	public void prettyPrint( String varName, PrettyPrinter pp )
	{
		pp.add( m_type );
		pp.add( " " );
		pp.addln( varName );
		pp.openScope();
		iiop_version.prettyPrint( "iiop_version", pp );
		pp.addln( Types.STRING_TYPE + " host = \"" + host + "\"" );
		pp.addln( Types.U_SHORT_TYPE + " port = " + port );
		pp.add( Types.SEQ_OCTET_TYPE );
		pp.addln( " object_key" );
		pp.openScope();
		pp.addln( object_key );
		pp.closeScope();
		if ( m_type == TYPE_1_1 )
		{
			components.prettyPrint( "components", pp );
		}
		pp.closeScope();
	}


}
