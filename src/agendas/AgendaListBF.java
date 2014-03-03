package agendas;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rp13.search.interfaces.Agenda;
import rp13.search.interfaces.Puzzle;

public class AgendaListBF<ItemT extends Puzzle> implements Agenda<ItemT> {


	private List<ItemT> aList;
	private Iterator<ItemT> list;
	
	public AgendaListBF() {

		this.aList= new ArrayList<ItemT>();
		this.list =aList.iterator();
	}
	
	public int getSize(){
		return aList.size();
	}
	public List<ItemT> getList(){
		return aList;
	}
	@Override
	public void push(ItemT _item) {
		// TODO Auto-generated method stub
		aList.add(_item);
	}

	@Override
	public ItemT pop() {
		// TODO Auto-generated method stub
		ItemT temp = aList.get(0);
		aList.remove(0);
		return temp;
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return !(aList.size()>0);
	}

	@Override
	public boolean contains(ItemT _item) {
		// TODO Auto-generated method stub
		for (ItemT item : aList) {
			if(item.equals(_item))
				return true;
		}
		return false;
	}
	@Override
	public Iterator<ItemT> iterator() {
		// TODO Auto-generated method stub
		return this.list;
	}

	public void sort(){}


}
