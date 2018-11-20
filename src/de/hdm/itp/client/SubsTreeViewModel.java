package de.hdm.itp.client;

import java.util.Vector;

import com.google.gwt.view.client.TreeViewModel;

import de.hdm.itp.shared.bo.User;

public class SubsTreeViewModel implements TreeViewModel {
	private Vector<User> valueResults = null;

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		return (value instanceof User);
	}

}
