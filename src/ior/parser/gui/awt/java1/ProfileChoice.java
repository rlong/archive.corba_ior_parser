
package ior.parser.gui.awt.java1;

import java.awt.Choice;

import ior.parser.corba.iop.TaggedProfileSequence;


public class ProfileChoice extends Choice
{
	String m_choices[];

	public ProfileChoice( TaggedProfileSequence profiles )
	{
		m_choices = new String[ profiles.value.length ];
		for ( int i = 0; i < profiles.value.length; i++ )
		{
			m_choices[ i ] = "[" + ( i + 1 ) + "]" + 
				profiles.value[ i ].tag.getProfileName();
			this.addItem( m_choices[ i ] );
		}
	}

	public String[] getChoices()
	{
		return m_choices;
	}

}

