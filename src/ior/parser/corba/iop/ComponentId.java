//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.corba.iop;


import ior.parser.corba.DecodeError;
import ior.parser.corba.ReadableStream;

public class ComponentId 
{
    public static final String TYPE = "IOP::ComponentId";
    public int value;

	public static final int TAG_ORB_TYPE =  0;
	public static final String TAG_ORB_TYPE_NAME = "IOP::TAG_ORB_TYPE";

	public static final int TAG_CODE_SETS = 1;
	public static final String TAG_CODE_SETS_NAME = "IOP::TAG_CODE_SETS";

	public static final int TAG_POLICIES = 2;
	public static final String TAG_POLICIES_NAME = "IOP::TAG_POLICIES";

	public static final int TAG_ALTERNATE_IIOP_ADDRESS = 3;
	public static final String TAG_ALTERNATE_IIOP_ADDRESS_NAME = 
		"IOP::TAG_ALTERNATE_IIOP_ADDRESS";

	public static final int TAG_COMPLETE_OBJECT_KEY = 5;
	public static final String TAG_COMPLETE_OBJECT_KEY_NAME = 
		"IOP::TAG_COMPLETE_OBJECT_KEY";

	public static final int TAG_ENDPOINT_ID_POSITION = 6;
	public static final String TAG_ENDPOINT_ID_POSITION_NAME =
		"IOP::TAG_ENDPOINT_ID_POSITION";

	public static final int TAG_LOCATION_POLICY = 12;
	public static final String TAG_LOCATION_POLICY_NAME =
		"IOP::TAG_LOCATION_POLICY";

	public static final int TAG_ASSOCIATION_OPTIONS = 13;
	public static final String TAG_ASSOCIATION_OPTIONS_NAME =
		"IOP::TAG_ASSOCIATION_OPTIONS";

	public static final int TAG_SEC_NAME = 14;
	public static final String TAG_SEC_NAME_NAME = "IOP::TAG_SEC_NAME";

	public static final int TAG_SPKM_1_SEC_MECH = 15;
	public static final String TAG_SPKM_1_SEC_MECH_NAME = 
		"IOP::TAG_SPKM_1_SEC_MECH";

	public static final int TAG_SPKM_2_SEC_MECH = 16;
	public static final String TAG_SPKM_2_SEC_MECH_NAME = 
		"IOP::TAG_SPKM_2_SEC_MECH";

	public static final int TAG_KerberosV5_SEC_MECH = 17;
	public static final String TAG_KerberosV5_SEC_MECH_NAME = 
		"IOP::TAG_KerberosV5_SEC_MECH";

	public static final int TAG_CSI_ECMA_Secret_SEC_MECH = 18;
	public static final String TAG_CSI_ECMA_Secret_SEC_MECH_NAME =
		"IOP::TAG_CSI_ECMA_Secret_SEC_MECH";

	public static final int TAG_CSI_ECMA_Hybrid_SEC_MECH = 19;
	public static final String TAG_CSI_ECMA_Hybrid_SEC_MECH_NAME =
		"IOP::TAG_CSI_ECMA_Hybrid_SEC_MECH";

	public static final int TAG_SSL_SEC_TRANS = 20;
	public static final String TAG_SSL_SEC_TRANS_NAME =
		"IOP::TAG_SSL_SEC_TRANS";
	
	public static final int TAG_CSI_ECMA_Public_SEC_MECH = 21;
	public static final String TAG_CSI_ECMA_Public_SEC_MECH_NAME =
		"IOP::TAG_CSI_ECMA_Public_SEC_MECH";

	public static final int TAG_GENERIC_SEC_MECH = 22;
	public static final String TAG_GENERIC_SEC_MECH_NAME =
		"IOP::TAG_GENERIC_SEC_MECH";

	public static final int TAG_JAVA_CODEBASE = 25;
	public static final String TAG_JAVA_CODEBASE_NAME =
		"IOP::TAG_JAVA_CODEBASE";

	public static final int TAG_DCE_STRING_BINDING = 100;
	public static final String TAG_DCE_STRING_BINDING_NAME =
		"IOP::TAG_DCE_STRING_BINDING";

	public static final int TAG_DCE_BINDING_NAME = 101;
	public static final String TAG_DCE_BINDING_NAME_NAME =
		"IOP::TAG_DCE_BINDING_NAME";

	public static final int TAG_DCE_NO_PIPES = 102;
	public static final String TAG_DCE_NO_PIPES_NAME =
		"IOP::TAG_DCE_NO_PIPES";

	public static final int TAG_DCE_SEC_MECH = 103;
	public static final String TAG_DCE_SEC_MECH_NAME = 
		"IOP::TAG_DCE_SEC_MECH";
    
    public ComponentId( ReadableStream rs ) throws DecodeError
    {
        rs.getTrace().goingToDecode( TYPE );
        
        value = rs.decodeUnsignedLong();
        
        rs.getTrace().doneDecoding( TYPE );
    }

	private String getName()
	{
		switch( value )
		{
			case TAG_ORB_TYPE: return TAG_ORB_TYPE_NAME;
			case TAG_CODE_SETS: return TAG_CODE_SETS_NAME;
			case TAG_POLICIES: return TAG_POLICIES_NAME;
			case TAG_ALTERNATE_IIOP_ADDRESS: 
				return TAG_ALTERNATE_IIOP_ADDRESS_NAME;
			case TAG_COMPLETE_OBJECT_KEY: return TAG_COMPLETE_OBJECT_KEY_NAME;
			case TAG_ENDPOINT_ID_POSITION: return TAG_ENDPOINT_ID_POSITION_NAME;
			case TAG_LOCATION_POLICY: return TAG_LOCATION_POLICY_NAME;
			case TAG_ASSOCIATION_OPTIONS: return TAG_ASSOCIATION_OPTIONS_NAME;
			case TAG_SEC_NAME: return TAG_SEC_NAME_NAME;
			case TAG_SPKM_1_SEC_MECH: return TAG_SPKM_1_SEC_MECH_NAME;
			case TAG_SPKM_2_SEC_MECH: return TAG_SPKM_2_SEC_MECH_NAME;
			case TAG_KerberosV5_SEC_MECH: return TAG_KerberosV5_SEC_MECH_NAME;
			case TAG_CSI_ECMA_Secret_SEC_MECH: 
				return TAG_CSI_ECMA_Secret_SEC_MECH_NAME;
			case TAG_CSI_ECMA_Hybrid_SEC_MECH:
				return TAG_CSI_ECMA_Hybrid_SEC_MECH_NAME;
			case TAG_SSL_SEC_TRANS: return TAG_SSL_SEC_TRANS_NAME;
			case TAG_CSI_ECMA_Public_SEC_MECH: 
				return TAG_CSI_ECMA_Public_SEC_MECH_NAME;
			case TAG_GENERIC_SEC_MECH: return TAG_GENERIC_SEC_MECH_NAME;
			case TAG_JAVA_CODEBASE: return TAG_JAVA_CODEBASE_NAME;
			case TAG_DCE_STRING_BINDING: return TAG_DCE_STRING_BINDING_NAME;
			case TAG_DCE_BINDING_NAME: return TAG_DCE_BINDING_NAME_NAME;
			case TAG_DCE_NO_PIPES: return TAG_DCE_NO_PIPES_NAME;
			case TAG_DCE_SEC_MECH: return TAG_DCE_SEC_MECH_NAME;
		}
		return "Unknown !?!";
	}

	public String toString()
	{
		return TYPE + " : " + this.getName();
	}
}

