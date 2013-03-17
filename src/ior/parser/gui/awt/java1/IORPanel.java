
package ior.parser.gui.awt.java1;

import java.awt.Event;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import ior.parser.corba.iop.IORModel;

public class IORPanel extends Panel
{
	private GridBagLayout m_layoutMgr;

	private static final Label ms_endianLabel = new Label( "Encoding: " );
	private EndianChoice m_endian = null;

	private static final Label ms_typeIDLabel =
		new Label( "IDL Interface type: " );
	private TextField m_typeID;

	private static final Label ms_profilesLabel = new Label( "Profiles: " );
	private ProfileChoice m_profileChoice;

	private TaggedProfileSequencePanel m_profiles;
	
	public IORPanel()
	{
		m_layoutMgr = new GridBagLayout();
		this.setLayout( m_layoutMgr );

		m_typeID = new TextField();

		// m_profileChoice needs to be tracked for event handling purposes 
		// but there is no point in instantiating one now
		m_profileChoice = null;

		m_profiles = null;
	}

	public void update( IORModel iorm )
	{
		m_endian = new EndianChoice( iorm.getEndian() );
		m_typeID.setText( iorm.getIOR().type_id );
		m_profileChoice = 
			new ProfileChoice( iorm.getIOR().profiles);
		m_profiles = new TaggedProfileSequencePanel( iorm.getIOR().profiles );
		this.populate();
	}

	public void clear()
	{
		this.removeAll();
		m_profileChoice = null;
		m_profiles = null;
	}

	private void populate()
	{
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.BOTH;

		
		////////////////////////////////////////////////////////
		constraints.gridy = 0; // row 1 
		constraints.gridx = 0; // far left
		
		m_layoutMgr.setConstraints( ms_endianLabel, constraints );
		this.add( ms_endianLabel );

		constraints.gridx = 1; // to the right
		m_layoutMgr.setConstraints( m_endian, constraints );
		this.add( m_endian );

		////////////////////////////////////////////////////////
		constraints.gridy = 1; // row 2
		constraints.gridx = 0; // far left
		
		m_layoutMgr.setConstraints( ms_typeIDLabel, constraints );
		this.add( ms_typeIDLabel );

		constraints.gridx = 1; // to the right
		m_layoutMgr.setConstraints( m_typeID, constraints );
		this.add( m_typeID );

		////////////////////////////////////////////////////////
		constraints.gridy = 2; // row 3
		constraints.gridx = 0; // far left

		m_layoutMgr.setConstraints( ms_profilesLabel, constraints );
		this.add( ms_profilesLabel );

		constraints.gridx = 1; // to the right
		m_layoutMgr.setConstraints( m_profileChoice, constraints );
		this.add( m_profileChoice );

		////////////////////////////////////////////////////////
		constraints.gridy = 3; // row 4
		constraints.gridx = 0; // far left
		constraints.gridwidth = 2; // 
		
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;
		m_layoutMgr.setConstraints( m_profiles, constraints);
		this.add( m_profiles );

		this.validate();

	}

	public boolean action(Event evt, Object arg) 
	{
		if (evt.target == m_profileChoice ) 
		{
			m_profiles.update( m_profileChoice.getSelectedIndex() );
			return true;
		}
		return false;
	}

}

