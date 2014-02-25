package search;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.Agenda;
import rp13.search.interfaces.Puzzle;
import rp13.search.interfaces.SuccessorFunction;
import rp13.search.util.ActionStatePair;
import SearchTree.SearchTree;
import SearchTree.SearchTreeList;

public class SearchingFramework<ActionT,StateT extends Puzzle,Function extends SuccessorFunction<ActionT, StateT>> {
	
		
	private Function function;
	private final StateT puzzle,goal;
	private List<ActionStatePair<ActionT,StateT>> successors;
	private Agenda<StateT> agenda;
	private SearchTreeList<ActionT,StateT> trees ;
	private SearchTree<ActionT, StateT> original ;
	
	public SearchingFramework(Function function,
			StateT puzzle,
			StateT goal,
			ArrayList<StateT> aList,
			Agenda<StateT> agenda)
	{
		this.function=function;
		this.goal = goal;
		this.agenda = agenda;
		this.original = new SearchTree<ActionT, StateT>(puzzle);
		this.trees = new SearchTreeList<ActionT,StateT>();
		this.trees.add(original); //just add the puzzle to the trees for reference
		this.puzzle = puzzle;
		
	}
	

	public void Search(){
		
		//push original puzzle to the open list(agenda)
		//generate successors through agenda.pop
		//push each successors to the agenda
		//get the path from the SEARCHTREELIST with agenda.pop
		//copy the path to that agenda.pop and create a new search tree with new successor and the path
		//add the path generated
		//add the search tree to the SEARCHTREELIST
		//loop until one successor generate is goal
		
		agenda.push(puzzle); //push puzzle to agenda
		while(!trees.contain(goal)){//if the trees do not have the goal state
			StateT before = agenda.pop(); //create var for agenda.pop
			successors = new ArrayList<ActionStatePair<ActionT, StateT>>(); // successors be come new list every loop
			function.getSuccessors(before, successors);//get successors through "before"
			for (ActionStatePair<ActionT, StateT> successor : successors) { //for each successor generated
				if(!trees.contain(successor.getState())){ // if tree do not have the the same state
					List<ActionT> addmove = new ArrayList<ActionT>();
					addmove.addAll(trees.getPathTo(before));
					addmove.add(successor.getAction());
					trees.add(new SearchTree<ActionT, StateT>(successor.getState(),	addmove)); //add temp search tree to temp list
					agenda.push(successor.getState()); //push the state to agenda
					}
				}
			}					
		}
	public List<ActionT> getResult(){
		return trees.getPathTo(goal);
	}

}
