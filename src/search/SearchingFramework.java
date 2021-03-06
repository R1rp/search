package search;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.Agenda;
import rp13.search.interfaces.Puzzle;
import rp13.search.interfaces.SuccessorFunction;
import rp13.search.util.ActionStatePair;
import SearchTree.SearchTreeNode;
import SearchTree.SearchTree;

public class SearchingFramework<ActionT,StateT extends Puzzle,Function extends SuccessorFunction<ActionT, StateT>> {
	
		
	private Function function;
	private final StateT puzzle;
	private List<ActionStatePair<ActionT,StateT>> successors;
	private Agenda<StateT> agenda;
	private SearchTree<ActionT,StateT> trees ;
	private SearchTreeNode<ActionT, StateT> original ;
	
	public SearchingFramework(Function function,
			StateT puzzle,
			Agenda<StateT> agenda)
	{
		this.function=function;
		this.agenda = agenda;
		this.original = new SearchTreeNode<ActionT, StateT>(puzzle);
		this.trees = new SearchTree<ActionT,StateT>();
		this.trees.add(original); //just add the puzzle to the trees for reference
		this.puzzle = puzzle;
		
	}
	
	/**
		push original puzzle to the open list(agenda)
			generate successors through agenda.pop
			push each successors to the agenda
			get the path from the SEARCHTREELIST with agenda.pop
			copy the pathToagenda.pop and create a new search tree with new successor and the path
			add the path generated
			add the search tree to the SEARCHTREELIST
			loop until one successor generate is goal
			
			 * return true if there s a solution
			 * false if no solution / all blocked
			 * 
			 * this is the search algorithm that can apply to all three kind of search
			 * @return if there is an solution
			 */
	public boolean Search(){
		try{
		agenda.push(puzzle); //push puzzle to agenda
		while(!trees.isGoal()&&!agenda.isEmpty()){//if the trees do not have the goal state
			StateT before = agenda.pop(); //create var for agenda.pop
			successors = new ArrayList<ActionStatePair<ActionT, StateT>>(); // successors be come new list every loop
			function.getSuccessors(before, successors);//get successors through "before"
			for (ActionStatePair<ActionT, StateT> successor : successors) { //for each successor generated
				if(!trees.contain(successor.getState())){ // if tree do not have the the same state
					List<ActionT> addmove = new ArrayList<ActionT>();//create new list to store move
					addmove.addAll(trees.getPathTo(before)); //add the path to "before State"
					addmove.add(successor.getAction());//add new move
					trees.add(new SearchTreeNode<ActionT, StateT>(successor.getState(),	addmove)); 
					//add a new search tree with new state a the path to it
					agenda.push(successor.getState()); //push the state to agenda
					 
					}
				}
				agenda.sort();//only sort the list for a* search
				
			}
			if(agenda.isEmpty()){
				return false;
			}
			return true;
			
		}
		catch(OutOfMemoryError e){//using Depth-first or search by the robot sometimes out of memory. return no solution then
			return false;
		}
		
			
	}
	/** 
	 * get the state by moves 
	 * @param moves input
	 * @return state
	 */
	public StateT getState(List<ActionT> moves){
		return trees.getState(moves);
	}

	/**
	 * get the solution
	 * @return the path to Goal
	 */
	public List<ActionT> getResult(){
		return trees.getPathToGoal();
	}

}
