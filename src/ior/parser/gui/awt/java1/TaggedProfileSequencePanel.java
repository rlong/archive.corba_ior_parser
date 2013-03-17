
package ior.parser.gui.awt.java1;

import java.awt.CardLayout;
import java.awt.Panel;

import ior.parser.corba.iiop.ProfileBody;
import ior.parser.corba.iop.ProfileId;
import ior.parser.corba.iop.TaggedProfileSequence;



public class TaggedProfileSequencePanel extends Panel
{
	private CardLayout m_layoutMgr;

	public TaggedProfileSequencePanel( TaggedProfileSequence tps  )
	{
		m_layoutMgr = new CardLayout();
		this.setLayout( m_layoutMgr );

		if( tps.value.length == 0 )
		{
			Panel p = new Panel();
			this.add( Integer.toString( 0 ), p );
		}
		else
		{
			for( int i = 0; i < tps.value.length; i++ )
			{
				if ( ProfileId.TAG_INTERNET_IOP == tps.value[ i ].tag.value )
				{
					ProfileBody pb = (ProfileBody)tps.value[ i ].getProfile();
					IIOPProfilePanel iiopPanel = new IIOPProfilePanel( pb );
					this.add( Integer.toString( i ), iiopPanel );
				}
				else
				{
					this.add( Integer.toString( i ), new Panel() );
				}
			}
		}

	}


	public void update( int profileIndex )
	{
		m_layoutMgr.show( this, Integer.toString( profileIndex ) );
	}

}


