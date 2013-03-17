//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.corba.iop;

import ior.parser.corba.DecodeError;
import ior.parser.corba.ReadableStream;
import ior.parser.corba.iiop.ProfileBody;
import ior.parser.tui.PrettyPrinter;



public class TaggedProfile
{
    public static final String TYPE = "IOP::TaggedProfile";
	
    public ProfileId tag;
    public byte profile_data[];
	private Object m_profile; 

    public TaggedProfile( ReadableStream rs ) throws DecodeError
    {
        rs.getTrace().goingToDecode( TYPE );

        tag = new ProfileId( rs );
        
        profile_data = rs.decodeOctetSequence();

		ReadableStream profileStream = new ReadableStream( profile_data );
		if( tag.value == ProfileId.TAG_INTERNET_IOP )
		{
			m_profile = new ProfileBody( profileStream );
		}
		else if ( tag.value == ProfileId.TAG_MULTIPLE_COMPONENTS )
		{
			m_profile = new TaggedComponentSequence( profileStream );
		}
		else
		{
			// this scenario does not arise, see decoding of ProfileId
		}
        
        rs.getTrace().doneDecoding( TYPE );
    }

	public Object getProfile()
	{
		return m_profile;
	}

	public void prettyPrint( String varName, PrettyPrinter pp )
	{
		pp.add( TYPE );
		pp.add( " " );
		pp.addln( varName );
		pp.openScope();

		tag.prettyPrint( "tag", pp );
		if( tag.value == ProfileId.TAG_INTERNET_IOP )
		{
			ProfileBody pb = (ProfileBody)m_profile;
			pb.prettyPrint( "profile_data", pp );
		}
		else
		{
			TaggedComponentSequence tcs = (TaggedComponentSequence)m_profile;
			tcs.prettyPrint( "profile_data", pp );
		}
		pp.closeScope();
		
	}

}

