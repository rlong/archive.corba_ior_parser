//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.corba;

public class Types
{
    public static final String BOOLEAN_TYPE = "boolean";
    public static final String CHAR_TYPE = "char";
    public static final String OCTET_TYPE = "octet";
    public static final String SEQ_OCTET_TYPE = "sequence<" +OCTET_TYPE+ ">";
    public static final String STRING_TYPE = "string";
    public static final String U_LONG_TYPE = "unsigned long";
    public static final String U_SHORT_TYPE = "unsigned short";

    public static final int U_LONG_SIZE = 4;
    public static final int U_LONG_ALIGNMENT = 4;

    public static final int U_SHORT_SIZE = 2;
    public static final int U_SHORT_ALIGNMENT = 2;

}
